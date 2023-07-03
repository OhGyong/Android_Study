package com.study.blesample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.study.blesample.ui.ConnectScreen
import com.study.blesample.ui.ScanScreen
import com.study.blesample.ui.theme.BleSampleTheme

class MainActivity : ComponentActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BleSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "ScanScreen") {
                        composable(route = "ScanScreen") { ScanScreen(navController) }
                        composable(route = "ConnectScreen") { ConnectScreen() }
                    }
                }
            }
        }

        if(Build.VERSION.SDK_INT >= 31){
            if(permissionArray.all{ ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
                Toast.makeText(this, "권한 확인", Toast.LENGTH_SHORT).show()
            }
            else{
                requestPermissionLauncher.launch(permissionArray)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            Log.d("DEBUG", "${it.key} = ${it.value}")
        }
    }
}