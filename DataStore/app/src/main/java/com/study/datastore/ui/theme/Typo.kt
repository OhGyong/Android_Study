package com.study.datastore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val SettingTextFieldTypography = Typography(
    bodyLarge = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = White
    ),

    labelLarge = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Gray
    )
)

val SettingTitleTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 35.sp,
        color = Yellow40,
        fontWeight = FontWeight.Bold
    )
)

val SettingButtonTypography = Typography(
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        color = Gray,
        fontWeight = FontWeight.Bold
    )
)