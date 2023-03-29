package com.study.flowsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.flowsample.data.Sample
import com.study.flowsample.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

    private var _sampleData = MutableStateFlow<List<String>>(emptyList())
    val sampleData = _sampleData.asStateFlow()
//    val sampleData: StateFlow<List<String>> = _sampleData

    /**
     * select 호출, 캡슐화
     */
    fun callSelect() {
        viewModelScope.launch {
            sampleRepository.callSelect().collectLatest { it ->
                _sampleData.emit(it)
            }
        }
    }

    /**
     * select 호출, return으로 반환
     */
    fun returnCallSelect(): StateFlow<List<String>> {
        // stateIn으로 StateFlow로 변환
        return sampleRepository.callSelect().stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(1000), initialValue = emptyList()
        )
    }

    /**
     * insert 호출
     */
    fun callInsert(sample: Sample) {
        sampleRepository.callInsert(sample)
    }

    /**
     * delete 호출
     */
    fun callDelete(name: String) {
        sampleRepository.callDelete(name)
    }

    /**
     * update 호출
     */
    fun callUpdate(originName: String, changeName: String) {
        sampleRepository.callUpdate(originName, changeName)
    }
}