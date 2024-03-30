package com.study.datastore.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.study.datastore.MainViewModel
import com.study.datastore.R
import com.study.datastore.data.SettingRepository.DataStoreResult.SET_WEIGHT
import com.study.datastore.ui.theme.Black
import com.study.datastore.ui.theme.DataStoreTheme
import com.study.datastore.ui.theme.SettingTextFieldTypography
import com.study.datastore.ui.theme.SettingTitleTypography
import com.study.datastore.ui.theme.White
import com.study.datastore.ui.theme.Yellow40

@Composable
fun WeightScreen(
    navController: NavController,
    weightViewModel: MainViewModel = hiltViewModel()
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.kr_empty_stomach_weight),
            style = SettingTitleTypography.bodyLarge
        )

        var weight by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SetWeightView(
                weight,
                onWeightChange = { newWeight->
                    weight = newWeight
                }
            )
        }

        ButtonView(
            textValue = weight,
            saveValue = {weightViewModel.setWeight(weight)}
        )

        LaunchedEffect(weightViewModel.prefWeightResult) {
            if(weightViewModel.prefWeightResult == SET_WEIGHT) {
                navController.navigate("home")
            }
        }
    }
}

@Composable
fun SetWeightView(weight: String, onWeightChange:(String)->Unit) {
    TextField(
        modifier = Modifier
            .width(150.dp)
            .background(Black),
        value = weight,
        textStyle = SettingTextFieldTypography.bodyLarge.copy(
            textAlign = TextAlign.Center
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = White,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = {
            onWeightChange(it)
        }
    )

    Text(
        text = stringResource(id = R.string.kg),
        style = SettingTextFieldTypography.bodyLarge.copy(color = Yellow40)
    )
}

@Preview(showBackground = true)
@Composable
fun WeightPreview() {
    DataStoreTheme {
        val navController = rememberNavController()
        WeightScreen(navController = navController)
    }
}