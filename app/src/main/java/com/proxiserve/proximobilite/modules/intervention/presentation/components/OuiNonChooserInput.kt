package com.proxiserve.proximobilite.modules.intervention.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * OuiNonChooserInput
 * @author Edson De Carvalho
 */
@Composable
fun OuiNonChooserInput(
    question: String,
    onSelectionChange: (String) -> Unit
) {
    var selected by remember { mutableStateOf("Oui") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = "$question:  ",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Oui",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
            RadioButton(
                selected = selected == "Oui",
                onClick = {
                    selected = "Oui"
                    onSelectionChange(selected)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Non",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
            RadioButton(
                selected = selected == "Non",
                onClick = {
                    selected = "Non"
                    onSelectionChange(selected)
                }
            )
        }
    }
}