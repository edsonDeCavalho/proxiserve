package com.proxiserve.proximobilite.feature_modules.connexion.presentation.utils

/**
 * Created by dloriot on 01/07/2024.
 */
sealed class Screen(val route: String) {
    object ConnexionScreen: Screen("connexion_screen")
}