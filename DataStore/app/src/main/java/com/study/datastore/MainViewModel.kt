package com.study.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.datastore.data.SettingRepository
import com.study.datastore.ui.setting.NameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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

    private var _nameUiState = MutableStateFlow(NameUiState())
    val nameUiState = _nameUiState.asStateFlow()

    private val _weightObserve: MutableLiveData<String> = MutableLiveData()
    val weightObserve = _weightObserve

    /**
     * 이름
     */
    fun getName() {
        viewModelScope.launch {

        }
    }

    fun setName(name: String) {
        viewModelScope.launch {
            prefNameResult = settingRepository.setName(name)
        }
    }

    /**
     * 몸무게
     */
    fun getWeight() {
        viewModelScope.launch {
            _weightObserve.value = settingRepository.getWeight().first()
        }
    }

    fun setWeight(weight: String) {
        viewModelScope.launch {
            prefWeightResult = settingRepository.setWeight(weight)
        }
    }
}