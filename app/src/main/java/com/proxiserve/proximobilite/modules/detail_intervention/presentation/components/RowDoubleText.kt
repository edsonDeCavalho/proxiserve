package com.proxiserve.proximobilite.modules.detail_intervention.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.proxiserve.proximobilite.core.utils.HorizontalSpace

/**
 * Created by dloriot on 07/09/2024.
 */

@Composable
fun RowDoubleText(modifier: Modifier, text1: String, text2: String, textAlign: TextAlign, weight1: Float, weight2: Float) {

    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .weight(weight1)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp),
            text = text1,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        VerticalDivider()

        Text(
            modifier = Modifier
                .weight(weight2)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp),
            text = text2,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
    }

    HorizontalSpace(1.dp)

}