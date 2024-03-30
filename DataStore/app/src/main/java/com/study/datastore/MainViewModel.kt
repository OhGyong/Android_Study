package com.study.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.datastore.data.SettingRepository
import com.study.datastore.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    var prefNameResult by mutableStateOf("")
        private set

    var prefWeightResult by mutableStateOf("")
        private set

    private var _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    /**
     * 이름
     */
    fun setName(name: String) {
        viewModelScope.launch {
            prefNameResult = settingRepository.setName(name)
        }
    }

    /**
     * 몸무게
     */
    fun setWeight(weight: String) {
        viewModelScope.launch {
            prefWeightResult = settingRepository.setWeight(weight)
        }
    }

    /**
     * Home Data
     */
    fun getHomeData() {
        viewModelScope.launch {
            _homeUiState.update {currentState->
                currentState.copy(
                    name = settingRepository.getName().first(),
                    weight = settingRepository.getWeight().first()
                )
            }
        }
    }
}