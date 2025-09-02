package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by dloriot on 03/09/2024.
 */

@Composable
fun BubbleMessage(text: String, color: Color) {
    val shape = RoundedCornerShape(
        topStart = 10.dp,
        topEnd = 10.dp,
        bottomEnd = 10.dp,
        bottomStart = 0.dp
    )

    Surface(
        shape = shape,
        color = color,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Light, fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }

}