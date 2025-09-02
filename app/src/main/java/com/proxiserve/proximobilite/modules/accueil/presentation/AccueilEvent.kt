package com.proxiserve.proximobilite.modules.accueil.presentation

import androidx.navigation.NavController
import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 12/07/2024.
 */
sealed class AccueilEvent {

    class MonCompteEvent(val user: User) : AccueilEvent()
    class GoToFeuilleDeRouteEvent(val navController: NavController, val route: String) : AccueilEvent()
    data object Logout: AccueilEvent()
    data class EnteredLogin(val login: String, val mode: String): AccueilEvent()
    data class EnteredLoginSuccess(val aliasId: String, val navController: NavController) : AccueilEvent()
    data object EnteredLoginFailure : AccueilEvent()
}