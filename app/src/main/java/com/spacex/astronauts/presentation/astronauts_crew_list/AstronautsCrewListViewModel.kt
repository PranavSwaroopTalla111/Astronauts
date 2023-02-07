package com.spacex.astronauts.presentation.astronauts_crew_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacex.astronauts.common.SpaceXNetworkSource
import com.spacex.astronauts.domain.usecase.GetAstronautsCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AstronautsCrewListViewModel @Inject constructor(private val getAstronautsCrewUseCase: GetAstronautsCrewUseCase) :
    ViewModel() {

    private val getAstronautsCrewMutableState = mutableStateOf(AstronautsCrewListState())
    val astronautsCrewState: State<AstronautsCrewListState> = getAstronautsCrewMutableState

    init {
        getAstronauts()
    }

    private fun getAstronauts() {
        getAstronautsCrewUseCase().onEach {
            when (it) {
                is SpaceXNetworkSource.Error -> getAstronautsCrewMutableState.value =
                    AstronautsCrewListState(error = it.message ?: "An unexpected error encountered")
                is SpaceXNetworkSource.Loading -> getAstronautsCrewMutableState.value =
                    AstronautsCrewListState(isLoading = true)
                is SpaceXNetworkSource.Success -> getAstronautsCrewMutableState.value =
                    AstronautsCrewListState(astronauts = it.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}