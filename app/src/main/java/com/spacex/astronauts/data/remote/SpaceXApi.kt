package com.spacex.astronauts.data.remote

import com.spacex.astronauts.data.remote.dto.AstronautDto
import retrofit2.http.GET

interface SpaceXApi {

    @GET("/v4/crew")
    suspend fun getAstronauts(): List<AstronautDto>
}