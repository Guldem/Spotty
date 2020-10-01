package nl.shriekingprophets.services

import nl.shriekingprophets.services.api.*
import retrofit2.http.*
import java.time.Instant

interface ApiService {

    @GET("reservation/{id}/")
    suspend fun getReservation(@Path("id") id: Int): ApiReservation

    @POST("reservation")
    suspend fun createReservation(@Body reservation: ApiReservationRequest): ApiReservation

    @PUT("reservation/{id}/")
    suspend fun updateReservation(
        @Body reservation: ApiReservationRequest,
        @Path("id") id: Int
    ): ApiReservation

    @GET("station/")
    suspend fun getStationsInformation(@Query("take") take: Int): List<ApiStation>

    @GET("station/occupied/")
    suspend fun getOccupiedSpots(
        @Query("uiccode") code: Int,
        @Query("time_start" ) from: Instant,
        @Query("time_end") till: Instant,
    ): ApiOccupiedSpots

    @GET("ns_departures/")
    suspend fun getJourneys(
        @Query("uiccode") code: Int,
        @Query("date_time") time: Instant,
        @Query("max_journeys") max: Int,
    ): List<ApiJourney>
}