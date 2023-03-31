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
     * 1-1.
     * - Dao에서 Flow로 반환
     * - ViewModel에서 StateFlow로 처리 (캡슐화)
     * 이전 데이터와 비교하여 같을 경우 collect 되지 않음. 새로운 데이터일 경우에만 collect
     */
    fun callSelect() {
        viewModelScope.launch {
            sampleRepository.callSelect().collectLatest {
                println("StateFlow 호출")
                _sampleData.emit(it)
            }
        }
    }

    /**
     * 1-2.
     * return으로 Select 결과 전달 받기
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
        viewModelScope.launch {
            val result = sampleRepository.callUpdate(originName, changeName)
            if(result.failure != null) {
                println("에러 발생 : ${result.failure}")
            }
        }
    }

    //////////////////

    /**
     * 2.
     * - Dao에서 List로 반환
     * - Flow로 받음
     * 첫 데이터에만 반응, 값의 변화에 대해 반응 없음
     */
    fun callSelect2(): Flow<List<String>> = flow {
        emit(sampleRepository.callSelect2())
    }

    /**
     * 3.
     * - Dao에서 List로 반환
     * - StateFlow로 받음
     *  첫 데이터에만 반응, 값의 변화에 대해 반응 없음
     */
    fun callSelect3(): StateFlow<List<String>> = flow {
        emit(sampleRepository.callSelect3())
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(1000), initialValue = emptyList()
    )

    /**
     * 4.
     * - Dao에서 Flow로 반환
     * - Flow로 받음
     * 값의 변화에 따라 호출됨
     * 이전 데이터에 상관없이 모든 값을 발행
     */
    fun callSelect4(): Flow<List<String>> {
        println("Flow 호출")
        return sampleRepository.callSelect4()
    }
}