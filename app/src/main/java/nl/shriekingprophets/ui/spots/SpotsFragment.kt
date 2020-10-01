package nl.shriekingprophets.ui.spots

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nl.shriekingprophets.databinding.FragmentSpotsBinding
import nl.shriekingprophets.extensions.onClick
import nl.shriekingprophets.extensions.readParcelable
import nl.shriekingprophets.extensions.toBundle
import nl.shriekingprophets.util.ShriekingProphetsFragment
import java.time.*
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class SpotsFragment : ShriekingProphetsFragment<FragmentSpotsBinding>() {

    private val spotsViewModel by viewModels<SpotsViewModel>()

    override fun bindLayout(layoutInflater: LayoutInflater) =
        FragmentSpotsBinding.inflate(layoutInflater)

    override fun initialized(binding: FragmentSpotsBinding) {
        spotsViewModel.text.observe(viewLifecycleOwner, Observer { result ->
            binding.apply {
                spotsFromTime.text = result.from.label
                spotsFromTime.onClick {
                    fragmentManager?.let { fm ->
                        val timePicker = TimePickerFragment.newInstance(result.from)
                        timePicker.setTargetFragment(this@SpotsFragment, 0)
                        timePicker.show(fm, "dialog")
                    }
                }
                spotsTillTime.text = result.till.label
                spotsTillTime.onClick {
                    fragmentManager?.let { fm ->
                        val timePicker = TimePickerFragment.newInstance(result.till)
                        timePicker.setTargetFragment(this@SpotsFragment, 0)
                        timePicker.show(fm, "dialog")
                    }
                }

                spotsAmount.text = result.spots.toString()

                binding.goButton.onClick {
                    spotsViewModel.createReservation(from = result.from, till = result.till)
                }
            }

        })

        spotsViewModel.reservationId.observe(viewLifecycleOwner, { reservation ->
            findNavController().navigate(
                SpotsFragmentDirections.actionNavigationSpotsToNavigationReservation(reservation)
            )
        })

        //default
//        binding.goButton.onClick {
//            spotsViewModel.createReservation()
//        }
//            findNavController().navigate(
//                SpotsFragmentDirections.actionNavigationSpotsToNavigationReservation(
//                    ApiReservation(
//                        2,
//                        Instant.now(),
//                        "Job",
//                        UTRECHT_CENTRAL,
//                        OffsetDateTime.now().withHour(8).withMinute(0).toInstant(),
//                        OffsetDateTime.now().withHour(20).withMinute(0).toInstant(),
//                    )
//                )
//            )
//        }
    }

    fun onTimeSelect(targetTime: TargetTime) {
        spotsViewModel.updateTime(targetTime)
    }
}

val timeFormatter
    get() = DateTimeFormatter.ofPattern("H:mm").withZone(ZoneId.of("Europe/Amsterdam"))

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private val targetTime: TargetTime by lazy(LazyThreadSafetyMode.NONE) {
        arguments.readParcelable() ?: TargetTime(SpotTimeType.From, Instant.now(), "08:00")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val now = Instant.now().atOffset(ZoneOffset.UTC)
        val hour = now.hour
        val minute = now.minute

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(
            requireActivity(),
            this,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val date = LocalDate.now()
        val time = LocalTime.of(hourOfDay, minute).withNano(0)
        val dateTime = date.atTime(time)
        val fragment = targetFragment as? SpotsFragment
        val newTargetTime =
            targetTime.copy(
                time = dateTime.toInstant(ZoneOffset.UTC),
                label = timeFormatter.format(dateTime)
            )
        fragment?.onTimeSelect(newTargetTime)
    }

    companion object {
        fun newInstance(targetTime: TargetTime): TimePickerFragment {
            return TimePickerFragment().apply {
                arguments = targetTime.toBundle()
            }
        }
    }
}