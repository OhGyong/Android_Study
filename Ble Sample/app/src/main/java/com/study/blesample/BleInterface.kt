package com.study.blesample

interface BleInterface {
    fun onConnectedStateObserve(isConnected: Boolean, data: String)
}