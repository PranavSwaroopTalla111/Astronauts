package com.spacex.astronauts.common

sealed class SpaceXNetworkSource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : SpaceXNetworkSource<T>(data)

    class Error<T>(message: String, data: T? = null) : SpaceXNetworkSource<T>(data, message)

    class Loading<T>(data: T? = null) : SpaceXNetworkSource<T>(data)
}