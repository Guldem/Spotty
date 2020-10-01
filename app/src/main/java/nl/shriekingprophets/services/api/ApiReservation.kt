package nl.shriekingprophets.services.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


abstract class ApiReservationBase: Parcelable {
    abstract val timestamp: Instant
    abstract val user: String
    abstract val uiccode: Int
    abstract val reserve_start: Instant
    abstract val reserve_end: Instant

    val expiresInMinutes : Int    get() = Duration.between(LocalDateTime.now().toInstant(ZoneOffset.UTC), reserve_start).toMinutes().toInt()
}

@Parcelize
@JsonClass(generateAdapter = true)
data class ApiReservation(
    val reserve_id: Int,
    override val timestamp: Instant,
    override val user: String,
    override val uiccode: Int,
    override val reserve_start: Instant,
    override val reserve_end: Instant
) : ApiReservationBase()

@Parcelize
@JsonClass(generateAdapter = true)
data class ApiReservationRequest(
    override val timestamp: Instant,
    override val user: String,
    override val uiccode: Int,
    override val reserve_start: Instant,
    override val reserve_end: Instant
) : ApiReservationBase()