package com.spacex.astronauts.di

import com.spacex.astronauts.common.Constants
import com.spacex.astronauts.data.remote.SpaceXApi
import com.spacex.astronauts.data.repository.SpaceXRepositoryImpl
import com.spacex.astronauts.domain.repository.SpaceXRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSpaceXApi(): SpaceXApi {
        return Retrofit.Builder()
            .baseUrl(Constants.SPACE_X_DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceXApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpaceXRepository(spaceXApi: SpaceXApi): SpaceXRepository {
        return SpaceXRepositoryImpl(spaceXApi)
    }

}