package com.study.datastore.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.study.datastore.MainViewModel
import com.study.datastore.R
import com.study.datastore.ui.theme.HomeDataTypography

@Composable
fun HomeScreen(
    homeViewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        homeViewModel.getHomeData()
        val homeUiState by homeViewModel.homeUiState.collectAsState()

        Text(
            text = stringResource(id = R.string.name) + ": " + homeUiState.name,
            style = HomeDataTypography.bodyLarge
        )
        Text(
            modifier=Modifier.padding(top = 5.dp),
            text = stringResource(id = R.string.kr_empty_stomach_weight) + ": " + homeUiState.weight,
            style = HomeDataTypography.bodyLarge
        )
    }
}