package nl.shriekingprophets.ui.platform

import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import nl.shriekingprophets.databinding.FragmentPlatformBinding
import nl.shriekingprophets.ui.spots.timeFormatter
import nl.shriekingprophets.util.ShriekingProphetsFragment

class PlatformFragment : ShriekingProphetsFragment<FragmentPlatformBinding>() {

    private val args by navArgs<PlatformFragmentArgs>()

    override fun bindLayout(layoutInflater: LayoutInflater) =
        FragmentPlatformBinding.inflate(layoutInflater)

    override fun initialized(binding: FragmentPlatformBinding) {
        args.journey.let { journey ->
            binding.apply {
                platformDestination.text = journey.direction
                platformLenght.text = "6"
                platformNumber.text = journey.plannedTrack ?: "16"
                platformTime.text = timeFormatter.format(journey.plannedDateTime)
            }
        }
    }
}