package com.study.blesample

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun Home() {
    var scanList = remember { mutableStateListOf<DeviceData>() }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScanButton(scanList)
        ScanList(scanList)
    }
}

@Composable
fun ScanButton(scanList: SnapshotStateList<DeviceData>) {
    val context = LocalContext.current
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter = bluetoothManager.adapter

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        // todo : 권한 결과 처리
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 5.dp),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF1D8821)),
        onClick = { checkPermission(context, bluetoothAdapter, launcher, scanList) }
    ) {
        Text(text = "Scan")
    }
}

@Composable
fun ScanList(scanList: SnapshotStateList<DeviceData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
//        items(scanList.size) {
//            val task = scanList.getOrNull(it)
//            if(task != null) {
//                key(keys = task) {
//                    Text(text = task.name)
//                    Text(text = task.uuid)
//
//                }
//            }
//        }
        items(scanList) { topic->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = topic.name)
                Text(text = topic.uuid1)
                Text(text = topic.uuid2)
                Text(text = topic.address)
            }
        }
    }
}

fun checkPermission(
    context: Context,
    bluetoothAdapter: BluetoothAdapter,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    scanList: SnapshotStateList<DeviceData>
) {
    val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    if(Build.VERSION.SDK_INT >= 31){
        // 블루투스와 카메라 권한이 허용되었는지 체크
        if(permissionArray.all{ ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED}){
            startBleScan(bluetoothAdapter, scanList)
        }
        // 권한 요청
        else{
            launcher.launch(permissionArray)
        }
    }
}

@SuppressLint("MissingPermission")
fun startBleScan(bluetoothAdapter: BluetoothAdapter, scanList: SnapshotStateList<DeviceData>) {
    val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
    val scanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            if(result.device != null) {
                var uuid1 = "null"
                var uuid2 = "null"
                println(result.device.uuids)
                if(result.device.uuids != null) {
                    uuid1 = result.device.uuids[0].toString()
                    uuid2 = result.device.uuids[1].toString()

                }
                scanList.add(
                    DeviceData(
                        result.device.name?: "null",
                        uuid1,
                        uuid2,
                        result.device.address?: "null"
                    )
                )
                scanList.distinct()
            }

//            if (result.device.name != null && result.device.uuids != null) {
//                println(scanList)
//                scanList.add(
//                    DeviceData(
//                        result.device.name,
//                        result.device.uuids
//                    )
//                )
//            }

            val deviceName = result.device.name
//            println("deviceName  $deviceName")
            if (deviceName != null && deviceName == "YourDeviceName") {
                // 원하는 디바이스를 찾았을 때 필요한 동작 수행
            }
        }

        override fun onScanFailed(errorCode: Int) {
            println("onScanFailed  $errorCode")
        }

        // 스캔 시작
    }

    val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
        .build()

    bluetoothLeScanner.startScan(null, scanSettings, scanCallback)
}