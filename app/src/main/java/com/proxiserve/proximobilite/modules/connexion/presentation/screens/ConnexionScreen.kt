package com.proxiserve.proximobilite.modules.connexion.presentation.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.components.AppScaffold
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import com.proxiserve.proximobilite.modules.connexion.presentation.ConnexionViewModel
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import timber.log.Timber

/**
 * Created by dloriot on 10/07/2024.
 */

private val TAG = "[ConnexionScreen]"

@Composable
fun ConnexionScreen(
    navController: NavController,
    viewModel: ConnexionViewModel = hiltViewModel()
) {
    val count = remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    val userState by rememberUpdatedState(newValue = viewModel.userState.collectAsState())

    SideEffect {
        count.intValue++
        Timber.w("[ConnexionScreen] recomposé ${count.intValue} fois. userstate => ${userState.value.userId}" )
    }
    if (userState.value.isLoggedIn) {
        Timber.i("$TAG Groupe %s", userState.value.userGroup)

        // redirection vers accueil dès que j'ai un user dans le state
        userState.value.user?.let {
            Timber.w("$TAG userState.value.user ${it.prenom}")
            Redirect(
                route = Routes.Accueil.route,
                navController = navController,
                viewModel = viewModel,
                userState = userState.value
            )
        }?: run {
            Timber.e("$TAG userState.value.user NULL")
        }



        /* Redirection en fonction du groupe:
        *      - le groupes ADMIN redirige vers le mode Admin complet
        *      - le groupe CI redirige vers le mode Admin dégradé
        *      - le groupe Technicien redirige directement vers l'accueil
        */
//        when (userState.value.userGroup) {
//
//            UserGroup.GROUP_ADMIN.type -> {
//                Redirect(
//                    route = Routes.ModeAdmin.route,
//                    navController = navController,
//                    viewModel = viewModel,
//                    userState = userState.value
//                )
//            }
//
//            UserGroup.GROUP_CI.type -> {
//                Redirect(
//                    route = Routes.ModeAdmin.route,
//                    navController = navController,
//                    viewModel = viewModel,
//                    userState = userState.value
//                )
//            }
//
//            UserGroup.GROUP_TECHNICIEN.type -> {
//                Redirect(
//                    route = Routes.Accueil.route,
//                    navController = navController,
//                    viewModel = viewModel,
//                    userState = userState.value
//                )
//            }
//
//            else -> {
//                Toast.makeText(context, ConnexionConst.GROUP_FAIL, Toast.LENGTH_LONG).show()
//            }
//        }

    } else
        Redirect(
            route = Routes.Login.route,
            navController = navController,
            viewModel = viewModel,
            null
        )

}

@Composable
fun Redirect(route: String, navController: NavController, viewModel: ConnexionViewModel, userState: UserState?) {
    Timber.i("$TAG Redirect() to %s", route)

    if (route == Routes.Login.route)
        LoginScreen(navController, viewModel)

    if (route == Routes.Accueil.route) {
        Timber.i("$TAG Redirect() user %s", userState?.userId)

        userState?.userId?.let {
//            navController.navigate(Routes.Accueil.route + it)
            AppScaffold(navController = navController, routeObject = Routes.Accueil,
                it
            )
        }
    }

    if (route == Routes.ModeAdmin.route) {
        Timber.i("$TAG Redirect() user %s", userState?.userId)

        userState?.userId?.let {
            AppScaffold(navController = navController, routeObject = Routes.ModeAdmin,
                it
            )
        }
    }

}
