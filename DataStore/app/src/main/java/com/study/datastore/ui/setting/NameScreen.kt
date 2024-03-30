package com.study.datastore.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.study.datastore.MainViewModel
import com.study.datastore.R
import com.study.datastore.data.SettingRepository.DataStoreResult.SET_NAME
import com.study.datastore.ui.theme.Black
import com.study.datastore.ui.theme.DataStoreTheme
import com.study.datastore.ui.theme.Gray
import com.study.datastore.ui.theme.SettingButtonTypography
import com.study.datastore.ui.theme.SettingTextFieldTypography
import com.study.datastore.ui.theme.SettingTitleTypography
import com.study.datastore.ui.theme.White
import com.study.datastore.ui.theme.Yellow40


@Composable
fun NameScreen(
    navController: NavHostController,
    nameViewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = SettingTitleTypography.bodyLarge
        )

        var name by remember {mutableStateOf("")}

        LaunchedEffect(nameViewModel.prefNameResult) {->
            if(nameViewModel.prefNameResult == SET_NAME) {
                navController.navigate("weight")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            SetNameView(
                name,
                onNameChange= {newName->
                    name=newName
                }
            )
        }

        ButtonView(
            textValue = name,
            saveValue = {nameViewModel.setName(name)}
        )
    }
}

@Composable
fun SetNameView(name: String, onNameChange:(String)->Unit) {
    TextField(
        modifier = Modifier
            .wrapContentWidth()
            .background(Black),
        value = name,
        textStyle = SettingTextFieldTypography.bodyLarge,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = White,
            backgroundColor = Color.Transparent, // 밑줄을 제거하려면 배경색을 투명으로 지정
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_name),
                color = Gray,
                style = SettingTextFieldTypography.labelLarge
            )
        },
        onValueChange = {
            onNameChange(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NamePreview() {
    DataStoreTheme {
        val navController = rememberNavController()
        NameScreen(navController = navController)
    }
}