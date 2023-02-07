package com.spacex.astronauts.presentation.astronauts_crew_list

import com.spacex.astronauts.domain.model.Astronaut

data class AstronautsCrewListState(
    val isLoading: Boolean = false,
    val astronauts: List<Astronaut> = emptyList(),
    val error: String = ""
)
