package com.proxiserve.proximobilite.modules.connexion.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.proxiserve.proximobilite.R

/**
 * Created by dloriot on 01/07/2024.
 */


@Composable
fun BackgroundImage() {
    val configuration = LocalConfiguration.current
    val cityScreens = arrayOf(
        R.drawable.screen_ville_marseille_2,
        R.drawable.screen_ville_lyon,
        R.drawable.screen_ville_bordeaux,
        R.drawable.screen_ville_paris,
        R.drawable.screen_ville_default
    )
//    val backgroundImage = painterResource(cityScreens.random())
    val backgroundImage = painterResource(R.drawable.screen_ville_marseille_2)

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = backgroundImage,
                contentDescription = "background_image",
                contentScale = ContentScale.FillWidth,
            )
        } else -> {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = backgroundImage,
                contentDescription = "background_image",
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}


@Composable
fun DarkBackgroundImage() {
    val configuration = LocalConfiguration.current
    val cityScreens = arrayOf(
        R.drawable.screen_ville_marseille_2,
        R.drawable.screen_ville_lyon,
        R.drawable.screen_ville_bordeaux,
        R.drawable.screen_ville_paris,
        R.drawable.screen_ville_default
    )
//    val backgroundImage = painterResource(cityScreens.random())
    val backgroundImage = painterResource(R.drawable.screen_ville_marseille_2)
    val color = Color.Black.copy(alpha = 0.7f)

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = backgroundImage,
                contentDescription = "background_image",
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(color = color, blendMode = BlendMode.Darken)
            )
        } else -> {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = backgroundImage,
            contentDescription = "background_image",
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(color = color, blendMode = BlendMode.Darken)
        )
    }
    }
}