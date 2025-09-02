package com.proxiserve.proximobilite.core.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by dloriot on 05/07/2024.
 */

@Composable
fun VerticalSpace(size: Dp) {
    Spacer(Modifier.height(size))
}

@Composable
fun HorizontalSpace(size: Dp) {
    Spacer(Modifier.width(size))
}

@Composable
fun DefaultSpace() {
    Spacer(Modifier.height(10.dp))
}

@Composable
fun DefaultFormSpace() {
    Spacer(Modifier.height(30.dp))
}

@Composable
fun Label(modifier: Modifier, text: String) {

}

@Composable
fun SimpleText(text: String, textAlign: TextAlign) {

}

