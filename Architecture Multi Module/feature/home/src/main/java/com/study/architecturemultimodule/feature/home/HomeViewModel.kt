package com.study.architecturemultimodule.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.architecturemultimodule.domain.GetSampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSampleUseCase: GetSampleUseCase
): ViewModel() {
    private val _sampleData = MutableStateFlow("")
    val sampleData: StateFlow<String> = _sampleData

    init {
        loadSampleData()
    }

    private fun loadSampleData() {
        viewModelScope.launch {
            _sampleData.value = getSampleUseCase()
        }
    }
}