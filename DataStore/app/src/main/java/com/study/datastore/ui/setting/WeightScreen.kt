package com.study.datastore.ui.setting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.study.datastore.R
import com.study.datastore.ui.theme.DataStoreTheme
import com.study.datastore.ui.theme.SettingTitleTypography

@Composable
fun WeightScreen(navController: NavController) {
    Text(
        text = "WeightScreen",
        style = SettingTitleTypography.bodyLarge
    )


}

@Preview(showBackground = true)
@Composable
fun WeightPreview() {
    DataStoreTheme {
    }
}