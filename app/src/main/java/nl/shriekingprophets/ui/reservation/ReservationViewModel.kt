package nl.shriekingprophets.ui.reservation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import nl.shriekingprophets.services.ApiService
import nl.shriekingprophets.services.api.ApiReservation

data class ReservationState(
    val id: Int,
    val timeLeft: String,

    )

class ReservationViewModel @ViewModelInject constructor(private val apiService: ApiService) :
    ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reservation Fragment"
    }
    val text: LiveData<String> = _text


    private val reservationId = MutableLiveData<ApiReservation>()
    val viewModel: LiveData<ReservationState> = reservationId.map { id ->
        mapResult(id)
//        val newLiveData = MutableLiveData<ReservationState>()
//        viewModelScope.launch {
//            newLiveData.postValue(mapResult(apiService.getReservation(id)))
//        }
//        newLiveData
    }

    fun init(reservation: ApiReservation) {
        reservationId.value = reservation
    }

    private fun mapResult(reservation: ApiReservation): ReservationState {
        return ReservationState(reservation.reserve_id, "${reservation.expiresInMinutes} mins")
    }
}