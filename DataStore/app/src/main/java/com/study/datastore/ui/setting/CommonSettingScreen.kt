package com.study.datastore.ui.setting

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.study.datastore.R
import com.study.datastore.ui.theme.Black
import com.study.datastore.ui.theme.Gray
import com.study.datastore.ui.theme.SettingButtonTypography
import com.study.datastore.ui.theme.White
import com.study.datastore.ui.theme.Yellow40

@Composable
fun ButtonView(
    textValue: String,
    saveValue: ()->Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        colors = if(textValue == "") {
            ButtonDefaults.buttonColors(Black)
        } else {
            ButtonDefaults.buttonColors(Yellow40)
        },
        enabled = textValue != "",
        shape = RoundedCornerShape(0.dp),
        onClick = saveValue
    ) {
        Text(
            text = stringResource(id = R.string.next),
            style = SettingButtonTypography.bodyLarge,
            color = if(textValue == "") {
                Gray
            } else {
                White
            }
        )
    }
}