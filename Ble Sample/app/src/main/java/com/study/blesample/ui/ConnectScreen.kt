package com.study.blesample.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.blesample.BleInterface
import com.study.blesample.DeviceData
import com.study.blesample.ble.BleManager

@SuppressLint("MissingPermission")
@Composable
fun ConnectScreen(navController: NavHostController, bleManager: BleManager) {
    val deviceData = navController.previousBackStackEntry?.savedStateHandle?.get<DeviceData>("deviceData")
    val isConnecting = remember { mutableStateOf(false) }
    val connectedData = remember { mutableStateOf("") }

    bleManager.onConnectedStateObserve(object : BleInterface{
        override fun onConnectedStateObserve(isConnected: Boolean, data: String) {
            isConnecting.value = isConnected
            connectedData.value = connectedData.value + "\n" + data
        }
    })

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = deviceData?.name ?: "Null",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(
                onClick = { /*TODO*/ },
            ){
                Icon(
                    imageVector = Icons.TwoTone.Info,
                    tint = Color(0xFF1D8821),
                    contentDescription = "inform"
                )
            }
        }

        ConnectButton(bleManager, isConnecting, deviceData)

        val scroll = rememberScrollState(0)
        Text(
            modifier = Modifier
                .padding(top = 5.dp)
                .verticalScroll(scroll),
            text = connectedData.value,
            style = TextStyle(
                fontSize = 14.sp,
            )
        )
    }
}
@SuppressLint("MissingPermission")
@Composable
fun ConnectButton(
    bleManager: BleManager,
    isConnecting: MutableState<Boolean>,
    deviceData: DeviceData?
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(end = 2.dp),
            shape = RoundedCornerShape(2.dp),
            enabled = !isConnecting.value,
            onClick = {
                bleManager.startBleConnectGatt(deviceData?: DeviceData("", "", ""))
            }
        ) {
            Text(text = "Connect")
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 2.dp),
            shape = RoundedCornerShape(2.dp),
            enabled = isConnecting.value,
            onClick = { bleManager.bleGatt!!.disconnect() }
        ) {
            Text(text = "Disconnect")
        }
    }
}