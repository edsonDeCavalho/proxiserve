package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proxiserve.proximobilite.ui.theme.Gris_100

/**
 * Created by dloriot on 07/08/2024.
 */

@Composable
fun AppTextField(label: String, text: String, enable: Boolean) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        enabled = enable,
        value = text,
        onValueChange = { },
        label = { Text(
            text = label,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.End
        ) },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Gris_100
        ),
        textStyle = TextStyle (
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.End
        ),
        shape = RoundedCornerShape(10.dp)

    )
}