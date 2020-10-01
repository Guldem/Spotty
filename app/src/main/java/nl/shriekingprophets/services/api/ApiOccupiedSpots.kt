package nl.shriekingprophets.services.api

import com.squareup.moshi.JsonClass
import java.time.Instant

@JsonClass(generateAdapter = true)
data class ApiOccupiedSpots(
    val uiccode: Int,
    val timestamp: Instant,
    val time_start: Instant,
    val time_end: Instant,
    val occupied_spots: Int
)