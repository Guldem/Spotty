package nl.shriekingprophets.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant

class InstantAdapter {
    @ToJson
    fun toJson(value: Instant) = value.toString()

    @FromJson
    fun fromJson(value: String): Instant {
//        val lastDot = value.indexOfLast { it == '.' }
//        val newValue = if (lastDot != -1) {
//            value.removeRange(lastDot, value.length)
//        } else value

        //chopping of milliseconds and timezones
        return Instant.parse(value.substring(0, 19) + ".000Z")
    }
}
