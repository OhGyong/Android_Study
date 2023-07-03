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
    private var scanResultListener: BleInterface? = null

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
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // 연결 성공
                gatt?.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // 연결 끊김
            }
        }

        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            MainScope().launch {
                Toast.makeText(context, " ${gatt?.device?.name} 연결 성공", Toast.LENGTH_SHORT).show()
                scanResultListener?.onServiceDiscovered()
            }.cancel()
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

    fun onServiceDiscovered(pScanResultListener: BleInterface) {
        scanResultListener = pScanResultListener
    }
}