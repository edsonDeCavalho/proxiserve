package com.proxiserve.proximobilite.core.utils

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.components.DarkBackgroundImage

/**
 * Created by dloriot on 12/10/2024.
 */

@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize()) {
        DarkBackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.clip(CircleShape)
                    .size(250.dp),   //crops the image to circle shape
                painter = rememberDrawablePainter(
                    drawable = getDrawable(
                        LocalContext.current,
                        R.drawable.loading_gif
                    )
                ),
                contentDescription = "Loading animation",
                contentScale = ContentScale.FillWidth
            )
        }
    }

//    when(loader) {
//        Loader.BallPulseRiseIndicator -> TODO()
//        Loader.BallPulseSyncIndicator -> TODO()
//        Loader.CircleShapeIndicator -> TODO()
//        Loader.GridFadeDiagonal -> TODO()
//        Loader.GridPulsatingDot -> TODO()
//        Loader.LineScaleIndicator -> TODO()
//        Loader.LineSpinFadeLoaderIndicator -> TODO()
//        Loader.PacmanIndicator -> TODO()
//        Loader.PulsatingDot -> TODO()
//    }
}

sealed class Loader() {
    data object PulsatingDot: Loader()
    data object GridPulsatingDot: Loader()
    data object BallPulseRiseIndicator: Loader()
    data object BallPulseSyncIndicator: Loader()
    data object LineScaleIndicator: Loader()
    data object LineSpinFadeLoaderIndicator: Loader()
    data object PacmanIndicator: Loader()
    data object GridFadeDiagonal: Loader()
    data object CircleShapeIndicator: Loader()
}