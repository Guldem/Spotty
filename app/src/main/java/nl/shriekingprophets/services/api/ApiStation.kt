package nl.shriekingprophets.services.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//"uiccode": 8400621,
//"stncode": "UT",
//"lat": 52.0888900756836,
//"lon": 5.11027765274048,
//"bike_capacity": 20,
//"stnname": "Utrecht Centraal"

@JsonClass(generateAdapter = true)
data class ApiStation(
    val uiccode : Int,
    val stncode: String,
    val lat: Float,
    val lon: Float,
    val bike_capacity: Int,
    val stnname: String
)