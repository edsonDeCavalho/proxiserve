package com.proxiserve.proximobilite.modules.connexion.nav

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.proxiserve.proximobilite.core.components.AppScaffold
import com.proxiserve.proximobilite.modules.connexion.presentation.screens.ConnexionScreen
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.LOGIN_GRAPH_ROUTE
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.USER_ID_KEY
import timber.log.Timber

/**
 * Created by dloriot on 02/07/2024.
 */

fun NavGraphBuilder.connexionNavGraph(navController: NavHostController) {

    navigation(
        startDestination = Routes.Login.route,
        route = LOGIN_GRAPH_ROUTE
    ) {
        composable(route = Routes.Login.route) {
            Timber.w("[connexionNavGraph][DEBUG_RECOMPOSE] pour ConnexionScreen appelé")
//            val count = remember {
//                mutableIntStateOf(0)
//            }
//            SideEffect {
//                count.intValue++
//                Timber.w("[connexionNavGraph][DEBUG_RECOMPOSE] pour ConnexionScreen appelé ${count.intValue} fois" )
//            }
            ConnexionScreen(navController = navController)
        }

//        accueilNavGraph(navController = navController)
//        composable(route = Routes.Accueil.route) {
//            it.arguments?.getString(USER_ID_KEY)
//                ?.let { it1 -> AppScaffold(navController, Routes.Accueil, it1) }
//        }

        // mode Admin
//        composable(route = Routes.ModeAdmin.route) {
//            val count = remember {
//                mutableStateOf(0)
//            }
//            SideEffect {
//                count.value++
//                Timber.w("[connexionNavGraph] appelé ${count.value} fois" )
//            }
//            it.arguments?.getString(USER_ID_KEY)
//                ?.let { it1 -> AppScaffold(navController, Routes.ModeAdmin, it1) }
//        }

    }
}