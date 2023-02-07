package com.spacex.astronauts.domain.model

data class Astronaut(
    val agency: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val currentStatus: String,
    val wikipediaUrl: String
)
