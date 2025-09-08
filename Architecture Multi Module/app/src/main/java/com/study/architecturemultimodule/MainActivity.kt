package com.study.architecturemultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.study.architecturemultimodule.feature.home.HomeScreen
import com.study.architecturemultimodule.ui.theme.ArchitectureMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchitectureMultiModuleTheme {
                HomeScreen()
            }
        }
    }
}