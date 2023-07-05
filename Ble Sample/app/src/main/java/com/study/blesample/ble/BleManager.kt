package com.study.blesample.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.study.blesample.BleInterface
import com.study.blesample.DeviceData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class BleManager(private val context: Context) {
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
    private var scanList: SnapshotStateList<DeviceData>? = null
    private var connectedStateObserver: BleInterface? = null
    var bleGatt: BluetoothGatt? = null

    private val scanCallback: ScanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            Log.d("onScanResult", result.toString())
            if(result.device.name != null) {
                var uuid = "null"

                if(result.scanRecord?.serviceUuids != null) {
                    uuid = result.scanRecord!!.serviceUuids.toString()
                }

                val scanItem = DeviceData(
                    result.device.name?: "null",
                    uuid,
                    result.device.address?: "null"
                )

                if(!scanList!!.contains(scanItem)) {
                    scanList!!.add(scanItem)
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            println("onScanFailed  $errorCode")
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        // GATT의 연결 상태 변경을 감지하는 콜백
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)

            // 연결이 성공적으로 이루어진 경우
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // GATT 서버에서 사용 가능한 서비스들을 비동기적으로 탐색
                Log.d("BleManager", "연결 성공")
                gatt?.discoverServices()
                connectedStateObserver?.onConnectedDataObserve("onConnectionStateChange STATE_CONNECTED")
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // 연결 끊김
                Log.d("BleManager", "연결 해제")
                connectedStateObserver?.onConnectedStateObserve(false)
                connectedStateObserver?.onConnectedDataObserve("onConnectionStateChange STATE_DISCONNECTED")
            }
        }

        // 장치에 대한 새로운 서비스가 발견되었을 때 호출되는 콜백
        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)

            // 원격 장치가 성공적으로 탐색된 경우
            if(status == BluetoothGatt.GATT_SUCCESS) {
                MainScope().launch {
                    bleGatt = gatt
                    Toast.makeText(context, " ${gatt?.device?.name} 연결 성공", Toast.LENGTH_SHORT).show()
                    connectedStateObserver?.onConnectedStateObserve(true)
                    connectedStateObserver?.onConnectedDataObserve("onServicesDiscovered GATT_SUCCESS")
                }.cancel()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startBleScan() {
        scanList?.clear()

//        val scanFilter: ScanFilter = ScanFilter.Builder()
//            .setDeviceName("DeviceName")
//            .setDeviceAddress("DeviceAddress")
//            .setServiceUuid(ParcelUuid(serviceUuid))
//            .setManufacturerData(manufacturerId, manufacturerData, manufacturerDataMask)
//            .setServiceData(serviceUuid, serviceData)
//            .build()

        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
            .build()

        bluetoothLeScanner.startScan(null, scanSettings, scanCallback)
    }

    @SuppressLint("MissingPermission")
    fun stopBleScan() {
        bluetoothLeScanner.stopScan(scanCallback)
    }

    @SuppressLint("MissingPermission")
    fun startBleConnectGatt(deviceData: DeviceData) {
        bluetoothAdapter
            .getRemoteDevice(deviceData.address)
            .connectGatt(context, false, gattCallback)
    }

    fun setScanList(pScanList: SnapshotStateList<DeviceData>) {
        scanList = pScanList
    }

    fun onConnectedStateObserve(pConnectedStateObserver: BleInterface) {
        connectedStateObserver = pConnectedStateObserver
    }
}