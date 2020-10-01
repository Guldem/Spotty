package nl.shriekingprophets.ui.ticket

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import nl.shriekingprophets.services.ApiService
import nl.shriekingprophets.services.api.ApiJourney
import nl.shriekingprophets.services.api.ApiReservation
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


fun provideRandomParkingSpots() = listOf("6A", "666", "689", "668", "42A", "007", "42").random()

data class TicketState(
    val parkingSpot: String,
    val minutesTillDeparture: Int,
    val reservationHash: String,
    val journey: ApiJourney
)

class TicketViewModel @ViewModelInject constructor(private val apiService: ApiService) :
    ViewModel() {

    private val mutableReservation = MutableLiveData<ApiReservation>()
    val ticketState: LiveData<TicketState> = mutableReservation.switchMap { reservation ->
        val newValue = MutableLiveData<TicketState>()
        viewModelScope.launch {
            val results = apiService.getJourneys(reservation.uiccode, Instant.now(), 6)
            val state = TicketState(
                parkingSpot = provideRandomParkingSpots(),
                minutesTillDeparture = Duration.between(
                    LocalDateTime.now().toInstant(ZoneOffset.UTC),
                    results.last().plannedDateTime
                ).toMinutes().toInt(),
                reservationHash = "id:${reservation.reserve_id}",
                journey = results.last()
            )
            newValue.postValue(state)
        }
        newValue
    }

    fun init(reservation: ApiReservation){
        mutableReservation.value = reservation
    }
}