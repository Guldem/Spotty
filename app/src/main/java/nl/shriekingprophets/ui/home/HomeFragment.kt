package nl.shriekingprophets.ui.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import nl.shriekingprophets.databinding.FragmentHomeBinding
import nl.shriekingprophets.extensions.onClick
import nl.shriekingprophets.ui.spots.SpotsFragmentDirections
import nl.shriekingprophets.util.ShriekingProphetsFragment

class HomeFragment : ShriekingProphetsFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun bindLayout(layoutInflater: LayoutInflater) =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun initialized(binding: FragmentHomeBinding) {
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.apply{
                textHome.text = it
                goButton.onClick { findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationSpots()) }
            }
        })
    }
}