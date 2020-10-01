package nl.shriekingprophets.services.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.time.Instant

@Parcelize
@JsonClass(generateAdapter = true)
data class ApiJourney(
    val direction: String,
    val name: String,
    val plannedDateTime: Instant,
    val trainCategory: String,
    val departureStatus: String,
    val plannedTrack: String? = null
):Parcelable