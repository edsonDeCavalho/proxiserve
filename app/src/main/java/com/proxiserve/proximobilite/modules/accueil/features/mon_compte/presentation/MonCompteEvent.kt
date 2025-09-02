package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation

import androidx.navigation.NavController
import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 08/08/2024.
 */
sealed class  MonCompteEvent {
    data class GoToModeAdmin(val navController: NavController, val userId: String): MonCompteEvent()
    data class GoToAccueil(val navController: NavController, val userId: String): MonCompteEvent()
    data class SynchroProfil(val id: String): MonCompteEvent()
}