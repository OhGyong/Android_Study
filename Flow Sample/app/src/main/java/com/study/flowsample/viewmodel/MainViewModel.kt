package com.study.flowsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.flowsample.data.FlowEntity
import com.study.flowsample.data.StateFlowEntity
import com.study.flowsample.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

    /**
     * Flow
     */
    fun selectFlow() : Flow<List<String>> = sampleRepository.selectFlow()

    fun insertFlow(flowEntity: FlowEntity) {
        sampleRepository.insertFlow(flowEntity)
    }

    fun deleteFlow(data: String) {
        sampleRepository.deleteFlow(data)
    }

    fun updateFlow(originData: String, changeData: String) {
        viewModelScope.launch {
            val result = sampleRepository.updateFlow(originData, changeData)
            if(result.failure != null) {
                println("에러 발생 : ${result.failure}")
            }
        }
    }

    /**
     * StateFlow
     */
    // Case 1
    private var _stateFlowData = MutableStateFlow<List<String>>(emptyList())
//    val stateFlowData: StateFlow<List<String>> = _stateFlowData

    fun selectStateFlow() {
        viewModelScope.launch {
            sampleRepository.selectStateFlow().collectLatest {
                _stateFlowData.value = it
                // _stateFlowData.emit(it)
            }
        }
    }

    // Case 2
    val stateFlowData: StateFlow<List<String>> = sampleRepository.selectStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun insertStateFlow(stateFlowEntity: StateFlowEntity) {
        sampleRepository.insertStateFlow(stateFlowEntity)
    }

    fun deleteStateFlow(data: String) {
        sampleRepository.deleteStateFlow(data)
    }

    fun updateStateFlow(originData: String, changeData: String) {
        viewModelScope.launch {
            val result = sampleRepository.updateStateFlow(originData, changeData)
            if(result.failure != null) {
                println("에러 발생 : ${result.failure}")
            }
        }
    }
}