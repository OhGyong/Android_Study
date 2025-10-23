package com.study.workmanagerwithalarmmanager.feature

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.workmanagerwithalarmmanager.core.alarm.AlarmScheduler
import com.study.workmanagerwithalarmmanager.domain.ClearSampleCountUseCase
import com.study.workmanagerwithalarmmanager.domain.GetSampleCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val clearSampleCountUseCase: ClearSampleCountUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getSampleCountUseCase: GetSampleCountUseCase

) : ViewModel() {

    private val _count = MutableStateFlow(savedStateHandle.get<Int>("count") ?: 0)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _selectedHour = MutableStateFlow(savedStateHandle.get<Int>("hour") ?: 0)
    val selectedHour: StateFlow<Int> = _selectedHour.asStateFlow()

    private val _selectedMinute = MutableStateFlow(savedStateHandle.get<Int>("minute") ?: 0)
    val selectedMinute: StateFlow<Int> = _selectedMinute.asStateFlow()

    private val _selectedSecond = MutableStateFlow(0)
    val selectedSecond = _selectedSecond.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    init {
        _count.onEach { savedStateHandle["count"] = it }.launchIn(viewModelScope)
        _selectedHour.onEach { savedStateHandle["hour"] = it }.launchIn(viewModelScope)
        _selectedMinute.onEach { savedStateHandle["minute"] = it }.launchIn(viewModelScope)
        _selectedSecond.onEach { savedStateHandle["second"] = it }.launchIn(viewModelScope)

        getSampleCount()
    }

    private fun getSampleCount() {
        viewModelScope.launch {
            getSampleCountUseCase().collect { count ->
                _count.value = count
            }
        }
    }

    fun clearSampleCount() {
        viewModelScope.launch {
            clearSampleCountUseCase()
            _selectedHour.value = 0
            _selectedMinute.value = 0
            _selectedSecond.value = 0
        }
    }

    fun setAlarmManager() {
        val timer = 5 // timer(초) 이후 alarmManager 실행
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, timer)

        val newHour = calendar.get(Calendar.HOUR_OF_DAY)
        val newMinute = calendar.get(Calendar.MINUTE)
        val newSecond = calendar.get(Calendar.SECOND)

        // 값 업데이트 (ui 업데이트)
        _selectedHour.value = newHour
        _selectedMinute.value = newMinute
        _selectedSecond.value = newSecond

        alarmScheduler.cancel() // 기존 알람 제거
        alarmScheduler.schedule(triggerAtMillis = calendar.timeInMillis)

        viewModelScope.launch {
            _toastEvent.emit("${timer}초 뒤에 WorkManager가 실행됩니다.")
        }
    }
}
