package com.proxiserve.proximobilite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.LOGIN_GRAPH_ROUTE
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.ROOT_GRAPH_ROUTE
import timber.log.Timber

/**
 * Created by dloriot on 02/07/2024.
 */


@Composable
fun RootNavGraph() {
    Timber.i("[RootNavGraph]")
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {

//        connexionNavGraph(navController)
        appNavGraph(navController = navController)

    }
}
