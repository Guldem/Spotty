package nl.shriekingprophets.ui.ticket

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import net.glxn.qrgen.android.QRCode
import nl.shriekingprophets.databinding.FragmentTicketBinding
import nl.shriekingprophets.extensions.onClick
import nl.shriekingprophets.util.ShriekingProphetsFragment

@AndroidEntryPoint
class TicketFragment : ShriekingProphetsFragment<FragmentTicketBinding>() {

    private val ticketViewModel by viewModels<TicketViewModel>()
    private val args by navArgs<TicketFragmentArgs>()

    override fun bindLayout(layoutInflater: LayoutInflater) =
        FragmentTicketBinding.inflate(layoutInflater)

    override fun initialized(binding: FragmentTicketBinding) {
        ticketViewModel.ticketState.observe(viewLifecycleOwner, Observer { result ->
            binding.apply {
                ticketPark.text = "Park your bike at: ${result.parkingSpot}"
                amountTillMinutes.text = "${result.minutesTillDeparture} mins"
                ticketCode.setImageBitmap(
                    QRCode.from(result.reservationHash + "Example").withSize(150, 150).bitmap()
                )
                goButton.onClick {
                    findNavController().navigate(TicketFragmentDirections.actionTicketFragmentToPlatformFragment(result.journey))
                }
            }
        })


        ticketViewModel.init(args.reservation)
    }
}