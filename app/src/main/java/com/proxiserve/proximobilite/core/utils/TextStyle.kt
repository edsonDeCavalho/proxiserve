package com.proxiserve.proximobilite.core.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Created by dloriot on 06/07/2024.
 */



@Composable
fun TextContour(text: String, fontSize: Dp, contourColor: Int, innerColor: Int) {

    val textPaintStroke = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.STROKE
        textSize = fontSize.value
        color = contourColor
        strokeWidth = 6f
        strokeMiter= 10f
        strokeJoin = android.graphics.Paint.Join.ROUND
    }

    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.FILL
        textSize = fontSize.value
        color = innerColor
    }

    Row(horizontalArrangement = Arrangement.Center) {
        Canvas(
            modifier = Modifier.fillMaxWidth(),
            onDraw = {
                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        text,
                        22.dp.toPx(),
                        22.dp.toPx(),
                        textPaintStroke
                    )

                    it.nativeCanvas.drawText(
                        text,
                        22.dp.toPx(),
                        22.dp.toPx(),
                        textPaint
                    )
                }
            }
        )
    }

}