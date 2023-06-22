package com.study.blesample

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.study.blesample.ui.theme.ScanItemTypography

@Composable
fun Home() {
    val scanList = remember { mutableStateListOf<DeviceData>() }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScanButton(scanList)
        ScanList(scanList)
    }
}

@Composable
@SuppressLint("MissingPermission")
fun ScanButton(scanList: SnapshotStateList<DeviceData>) {
    val context = LocalContext.current
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter = bluetoothManager.adapter
    val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        // todo : 권한 결과 처리
    }

    val scanCallback: ScanCallback = object : ScanCallback() {
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
                if(!scanList.contains(scanItem)) {
                    scanList.add(scanItem)
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            println("onScanFailed  $errorCode")
        }
    }

    var isScanning by remember{ mutableStateOf(false) }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 5.dp),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF1D8821)),
        onClick = {
            if(!isScanning) {
                if (checkPermission(context)) {
                    startBleScan(scanCallback, bluetoothLeScanner)
                } else {
                    launcher.launch(permissionArray)
                }
            } else {
                bluetoothLeScanner.stopScan(scanCallback)
            }
            isScanning =!isScanning
        }
    ) {
        Text(
            text = if (!isScanning) {
                stringResource(id = R.string.scan)
            } else {
                stringResource(id = R.string.stop)
            }
        )
    }
}

@Composable
fun ScanList(scanList: SnapshotStateList<DeviceData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(scanList) { topic->
            var expanded by remember { mutableStateOf(false) }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF569097)
                ),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .padding(start = 2.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = topic.name)

                        if(expanded) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "UUID\n>> ${topic.uuid}", style = ScanItemTypography.bodySmall)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Address\n>> ${topic.address}", style = ScanItemTypography.bodySmall)
                        }
                    }

                    IconButton(onClick = { expanded=!expanded }) {
                        Icon(
                            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = if(expanded){
                                stringResource(id = R.string.show_less)
                            } else {
                                stringResource(id = R.string.show_more)
                            })
                    }
                }
            }
        }
    }
}

fun checkPermission(context: Context): Boolean {
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
        return permissionArray.all{ ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED}
    }
    return true
}

@SuppressLint("MissingPermission")
fun startBleScan(scanCallback: ScanCallback, bluetoothLeScanner: BluetoothLeScanner) {
    val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
        .build()

    bluetoothLeScanner.startScan(null, scanSettings, scanCallback)
}

private val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
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