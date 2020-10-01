package nl.shriekingprophets.ui.platform

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import nl.shriekingprophets.services.ApiService


class PlatformViewModel @ViewModelInject constructor(private val apiService: ApiService) :
    ViewModel(){

}