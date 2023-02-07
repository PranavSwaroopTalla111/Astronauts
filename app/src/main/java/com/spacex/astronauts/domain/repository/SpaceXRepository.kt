package com.spacex.astronauts.domain.repository

import com.spacex.astronauts.data.remote.dto.AstronautDto

interface SpaceXRepository {
    suspend fun getAstronauts(): List<AstronautDto>
}