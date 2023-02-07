package com.spacex.astronauts.data.repository

import com.spacex.astronauts.data.remote.SpaceXApi
import com.spacex.astronauts.data.remote.dto.AstronautDto
import com.spacex.astronauts.domain.repository.SpaceXRepository
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi) :
    SpaceXRepository {

    override suspend fun getAstronauts(): List<AstronautDto> {
        return spaceXApi.getAstronauts()
    }
}