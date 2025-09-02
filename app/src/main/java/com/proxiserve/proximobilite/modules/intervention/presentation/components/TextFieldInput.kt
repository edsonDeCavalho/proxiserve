package com.proxiserve.proximobilite.modules.intervention.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * TextFieldInput component
 * @author Edson De Carvalho
 */
@Composable
fun TextFieldInput(
    initialValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth().background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = initialValue,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)) // Add border radius here
                .border(3.dp, Color.Black, RoundedCornerShape(1.dp)) // Add border here
                .heightIn(min = 100.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

/**
 * Preview
 */
@Preview(showBackground = false)
@Composable
fun TextFieldInputPreview() {
    var text by remember { mutableStateOf("") }
    TextFieldInput(
        initialValue = text,
        onValueChange = { text = it }
    )
}
