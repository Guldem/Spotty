package nl.shriekingprophets.ui.spots

import android.os.Parcelable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch
import nl.shriekingprophets.extensions.combineLatest
import nl.shriekingprophets.services.ApiService
import nl.shriekingprophets.services.api.ApiOccupiedSpots
import nl.shriekingprophets.services.api.ApiReservation
import nl.shriekingprophets.services.api.ApiReservationRequest
import java.time.Instant

@Parcelize
enum class SpotTimeType : Parcelable {
    From,
    Till
}

@Parcelize
data class TargetTime(
    val type: SpotTimeType,
    val time: Instant,
    val label: String
) : Parcelable

data class SpotsState(
    val id: Int,
    val station: String,
    val from: TargetTime,
    val till: TargetTime,
    val spots: Int
)

const val UTRECHT_CENTRAL = 8400621

class SpotsViewModel @ViewModelInject constructor(private val apiService: ApiService) :
    ViewModel() {


    private val mutableReservationId = MutableLiveData<ApiReservation>()
    val reservationId: LiveData<ApiReservation> = mutableReservationId

    private val mutableFrom =
        MutableLiveData(TargetTime(SpotTimeType.From, Instant.now(), "08:00"))
    private val mutableTill =
        MutableLiveData(TargetTime(SpotTimeType.Till, Instant.now(), "16:00"))
    private val mutableStation = MutableLiveData("Station Utrecht Centraal")
    val text: LiveData<SpotsState> =
        combineLatest(mutableFrom, mutableTill, mutableStation).switchMap { (from, till, station) ->
            val newValue = MutableLiveData<SpotsState>()
            viewModelScope.launch {
                newValue.postValue(
                    mapResult(
                        from,
                        till,
                        station,
                        apiService.getOccupiedSpots(8400621, from.time, till = till.time)
                    )
                )
            }
            newValue
        }

    private fun mapResult(
        from: TargetTime,
        till: TargetTime,
        station: String,
        spots: ApiOccupiedSpots
    ): SpotsState {
        return SpotsState(
            id = 1,
            from = from,
            till = till, station = station, spots = spots.occupied_spots
        )
    }

    fun createReservation(from: TargetTime, till: TargetTime) {
        viewModelScope.launch {
            val result =  apiService.createReservation(
                ApiReservationRequest(
                    timestamp = Instant.now(),
                    user = "Job",
                    uiccode = 8400621,
                    reserve_start = from.time,
                    reserve_end = till.time
                )
            )
            mutableReservationId.postValue(result.copy(reserve_start = from.time))
        }
    }

    fun updateTime(targetTime: TargetTime) {
        when (targetTime.type) {
            SpotTimeType.From -> mutableFrom.value = targetTime
            SpotTimeType.Till -> mutableTill.value = targetTime
        }
    }
}