package com.study.flowsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.flowsample.data.ColdEntity
import com.study.flowsample.data.HotEntity
import com.study.flowsample.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

    /**
     * Cold Flow
     */
    fun selectCold() : Flow<List<String>> = sampleRepository.selectCold()

    fun insertCold(coldEntity: ColdEntity) {
        sampleRepository.insertCold(coldEntity)
    }

    fun deleteCold(data: String) {
        sampleRepository.deleteCold(data)
    }

    fun updateCold(originData: String, changeData: String) {
        viewModelScope.launch {
            val result = sampleRepository.updateCold(originData, changeData)
            if(result.failure != null) {
                println("에러 발생 : ${result.failure}")
            }
        }
    }

    /**
     * Hot Flow
     */
    private var _hotData = MutableStateFlow<List<String>>(emptyList())
    val hotData: StateFlow<List<String>> = _hotData

    fun selectHot() {
        viewModelScope.launch {
            sampleRepository.selectHot().collectLatest {
                _hotData.emit(it)
            }
        }
    }

    fun selectHot2(): StateFlow<List<String>> {
        // stateIn으로 StateFlow로 변환
        return sampleRepository.selectHot().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = emptyList()
        )
    }

    fun insertHot(hotEntity: HotEntity) {
        sampleRepository.insertHot(hotEntity)
    }

    fun deleteHot(data: String) {
        sampleRepository.deleteHot(data)
    }

    fun updateHot(originData: String, changeData: String) {
        viewModelScope.launch {
            val result = sampleRepository.updateHot(originData, changeData)
            if(result.failure != null) {
                println("에러 발생 : ${result.failure}")
            }
        }
    }
}