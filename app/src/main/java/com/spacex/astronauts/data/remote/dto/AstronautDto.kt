package com.spacex.astronauts.data.remote.dto

import com.spacex.astronauts.domain.model.Astronaut

data class AstronautDto(
    val agency: String,
    val id: String,
    val image: String,
    val launches: List<String>,
    val name: String,
    val status: String,
    val wikipedia: String
)

fun AstronautDto.toAstronaut(): Astronaut {
    return Astronaut(
        agency = agency,
        id = id,
        imageUrl = image,
        name = name,
        currentStatus = status,
        wikipediaUrl = wikipedia
    )
}