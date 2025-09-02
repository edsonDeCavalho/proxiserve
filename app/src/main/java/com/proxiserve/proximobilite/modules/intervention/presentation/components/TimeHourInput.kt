package com.proxiserve.proximobilite.modules.intervention.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Calendar

/**
 * TimeInput
 * @author Edson De CARVALHO
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeHourInput(
    text: String,
    onTimeSelected: (Int, Int) -> Unit
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    LaunchedEffect(timePickerState.hour, timePickerState.minute) {
        onTimeSelected(timePickerState.hour, timePickerState.minute)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically)
        )
        androidx.compose.material3.TimeInput(
            modifier = Modifier
                .height(60.dp)
                .align(Alignment.CenterVertically),
            state = timePickerState
        )
    }
}