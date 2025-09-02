package com.proxiserve.proximobilite.core.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by dloriot on 04/07/2024.
 */


@Composable
fun CircularProgressBar(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
        strokeWidth = 2.dp,
        trackColor = MaterialTheme.colorScheme.onSecondary,
        strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
    )
}


