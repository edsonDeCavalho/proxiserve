package com.proxiserve.proximobilite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.proxiserve.proximobilite.core.components.AppScaffold
import com.proxiserve.proximobilite.modules.intervention.presentation.screens.IntervetionScreen
import com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.screen.InterventionValidationScreen
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.connexion.presentation.screens.ConnexionScreen
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.APPAREIL_CODE_KEY
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.INTERVENTION_CODE_KEY
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.LIEU_CODE_KEY
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.LOGIN_GRAPH_ROUTE
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.USER_ID_KEY

/**
 * Created by dloriot on 02/07/2024.
 */

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {

    navigation(
        startDestination = Routes.Login.route,
        route = LOGIN_GRAPH_ROUTE
    ) {
        // connexion
        composable(route = Routes.Login.route) {
            ConnexionScreen(navController = navController)
        }
        // accueil
        composable(route = Routes.Accueil.route) {
            it.arguments?.getString(USER_ID_KEY)
                ?.let { it1 -> AppScaffold(navController, Routes.Accueil, it1) }
        }
        // mode Admin
        composable(route = Routes.ModeAdmin.route) {
            it.arguments?.getString(USER_ID_KEY)
                ?.let { it1 -> AppScaffold(navController, Routes.ModeAdmin, it1) }
        }
        // mon compte
        composable(route = Routes.Compte.route) {
            it.arguments?.getString(USER_ID_KEY)
                ?.let { it1 -> AppScaffold(navController, Routes.Compte, it1) }
        }
        // fdr
        composable(route = Routes.FeuilleDeRoute.route) {
            it.arguments?.getString(USER_ID_KEY)
                ?.let { it1 -> AppScaffold(navController, Routes.FeuilleDeRoute, it1) }
        }
        // détail intervention
        composable(route = Routes.DetailIntervention.route) {
            it.arguments?.getString(USER_ID_KEY)
                ?.let { it1 -> AppScaffold(navController, Routes.DetailIntervention, it1) }
        }
        // historique
        composable(route = Routes.Historique.route) {
            it.arguments?.getString(LIEU_CODE_KEY)
                ?.let { lieuCode -> AppScaffold(navController, Routes.Historique, lieuCode) }
        }
        // historique détail
        composable(route = Routes.HistoriqueDetail.route) {
            it.arguments?.getString(INTERVENTION_CODE_KEY)
                ?.let { interventionCode -> AppScaffold(navController, Routes.HistoriqueDetail, interventionCode) }
        }
        // appareil
        composable(route = Routes.Appareil.route) {
            it.arguments?.getString(INTERVENTION_CODE_KEY)
                ?.let { interventionCode -> AppScaffold(navController, Routes.Appareil, interventionCode) }
        }
        // appareil détail
        composable(route = Routes.AppareilDetail.route) {
            it.arguments?.getString(APPAREIL_CODE_KEY)
                ?.let { appareilCode -> AppScaffold(navController, Routes.AppareilDetail, appareilCode) }
        }
        // intervention
        composable(route = Routes.Intervention.route) {
            IntervetionScreen(navController = navController)
        }
        // intervention validation
        composable(route = Routes.InterventionValidation.route) {
            InterventionValidationScreen(navController = navController)
        }
    }
}