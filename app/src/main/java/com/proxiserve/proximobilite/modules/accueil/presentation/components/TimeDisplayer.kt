package com.proxiserve.proximobilite.modules.accueil.presentation.components

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.TimeUtils
import kotlinx.coroutines.delay

/**
 * Created by dloriot on 07/07/2024.
 */


@Composable
fun HourTimeDisplay() {
    var hour by remember {
        mutableStateOf(TimeUtils.getNowHourTimeToString())
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            hour = TimeUtils.getNowHourTimeToString()
        }
    }
    Text(text = hour, fontSize = 35.sp, fontFamily = FontFamily(Font(R.font.bungee_shade_regular)), fontWeight = FontWeight.W900, color = MaterialTheme.colorScheme.secondary)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MinuteTimeDisplay() {
    var minute by remember {
        mutableStateOf(TimeUtils.getNowMinuteTimeToString())
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            minute = TimeUtils.getNowMinuteTimeToString()
        }
    }
    Text(text = minute, fontSize = 35.sp, fontFamily = FontFamily(Font(R.font.bungee_shade_regular)), fontWeight = FontWeight.W900, color = MaterialTheme.colorScheme.secondary)
}
