package com.proxiserve.proximobilite.modules.intervention.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * MesureInput
 * @author Edson De Carvalho
 */
@Composable
fun MesureInput(
    question: String,
    unite_mesure: String,
    onValueChange: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = "$question: ",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        )
        BasicTextField(
            value = inputValue,
            onValueChange = { newValue ->
                inputValue = newValue
                onValueChange(newValue)
            },
            modifier = Modifier
                .width(200.dp)
                .background(Color.White, RoundedCornerShape(4.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                Color.Black,
                                RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                            )
                            .padding(4.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = unite_mesure,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }
            }
        )
    }
}