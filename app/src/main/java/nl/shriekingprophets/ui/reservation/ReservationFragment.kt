package nl.shriekingprophets.ui.reservation

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import nl.shriekingprophets.databinding.FragmentReservationBinding
import nl.shriekingprophets.extensions.onClick
import nl.shriekingprophets.util.ShriekingProphetsFragment

@AndroidEntryPoint
class ReservationFragment : ShriekingProphetsFragment<FragmentReservationBinding>() {

    private val resevationViewModel by viewModels<ReservationViewModel>()

    private val args by navArgs<ReservationFragmentArgs>()

    override fun bindLayout(layoutInflater: LayoutInflater) =
        FragmentReservationBinding.inflate(layoutInflater)

    override fun initialized(binding: FragmentReservationBinding) {
        resevationViewModel.init(args.reservation)

        binding.goButton.onClick { findNavController().navigate(ReservationFragmentDirections.actionNavigationReservationToTicketFragment(args.reservation)) }
        resevationViewModel.viewModel.observe(viewLifecycleOwner, Observer { result ->
            binding.apply {
                amountTillMinutes.text = result.timeLeft
                reservationId.text = "Bicycle reservation id: ${result.id}"
            }
        })
    }
}