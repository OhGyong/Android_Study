package com.example.splashscreensample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading

    init {
        viewModelScope.launch {
            delay(1500L)
            _isLoading.value = false
        }
    }
}