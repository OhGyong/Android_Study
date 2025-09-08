package com.study.architecturemultimodule.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val sampleData by viewModel.sampleData.collectAsState()

    Column {
        Text(
            text = "Home Screen",
            modifier = modifier
        )

        Text(
            text = sampleData,
            modifier = modifier
        )
    }
}