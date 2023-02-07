package com.spacex.astronauts.domain.usecase

import com.spacex.astronauts.common.SpaceXNetworkSource
import com.spacex.astronauts.data.remote.dto.toAstronaut
import com.spacex.astronauts.domain.model.Astronaut
import com.spacex.astronauts.domain.repository.SpaceXRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAstronautsCrewUseCase @Inject constructor(private val spaceXRepository: SpaceXRepository) {

    operator fun invoke(): Flow<SpaceXNetworkSource<List<Astronaut>>> = flow {
        try {
            emit(SpaceXNetworkSource.Loading())
            val astronauts = spaceXRepository.getAstronauts().map { it.toAstronaut() }
            emit(SpaceXNetworkSource.Success(astronauts))
        } catch (httpException: HttpException) {
            emit(
                SpaceXNetworkSource.Error(
                    httpException.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (ioException: IOException) {
            emit(SpaceXNetworkSource.Error("Couldn't reach out to SpaceX. Please check your internet connection"))
        }
    }
}