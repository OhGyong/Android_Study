package com.study.blesample

interface BleInterface {
    fun onConnectedStateObserve(isConnected: Boolean) // 버튼 enable로 사용

    fun onConnectedDataObserve(data: String)
}