package com.proxiserve.proximobilite.core.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.screens.FeuilleDeRouteScreen
import com.proxiserve.proximobilite.navigation.RouteObject
import com.proxiserve.proximobilite.modules.connexion.presentation.ConnexionViewModel
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.screens.MonCompteScreen
import com.proxiserve.proximobilite.modules.accueil.presentation.AccueilLoader
import com.proxiserve.proximobilite.modules.intervention.presentation.screens.IntervetionScreen
import com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.screen.InterventionValidationScreen
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation.AppareilDetailScreen
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation.AppareilScreen
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation.HistoriqueDetailScreen
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.screens.DetailInterventionScreen
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation.HistoriqueScreen
import com.proxiserve.proximobilite.ui.theme.Rouge_proxi_700
import timber.log.Timber

/**
 * Created by dloriot on 02/07/2024.
 */

@Composable
fun AppScaffold(navController: NavController, routeObject: RouteObject, id: String) {
    Timber.d("Navigation: AppScaffold id %s vers route %s", id, routeObject.route)

    Scaffold(
//        topBar = { AppTopBar(routeObject = routeObject) },
//        bottomBar = { AppBottomBar() },
        floatingActionButton = {
            AppExtendedFloatingActionButton(navController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // on set le content selon la route
            when (routeObject.route) {
                Routes.Accueil.route -> {
                    AccueilLoader(navController = navController)
//                    AccueilScreen(navController = navController, id = id)
                }
                Routes.ModeAdmin.route -> {
                    val count = remember {
                        mutableIntStateOf(0)
                    }
                    SideEffect {
                        count.intValue++
                        Timber.w("[AppScaffold] Routes.ModeAdmin appelée ${count.intValue} fois" )
                    }
//                    ModeAdminScreen(navController = navController, id = id)
                    AccueilLoader(navController = navController)
                }
                Routes.ModeCi.route -> {
//                    ModeAdminScreen(navController = navController, id = id)
                    AccueilLoader(navController = navController)
                }
                Routes.Compte.route -> {
                    MonCompteScreen(navController = navController, id = id)
                }
                Routes.FeuilleDeRoute.route -> {
                    FeuilleDeRouteScreen(navController = navController, id = id)
                }
                Routes.DetailIntervention.route -> {
                    DetailInterventionScreen(navController = navController, id = id)
                }
                Routes.Historique.route -> {
                    HistoriqueScreen(navController = navController, lieuCode = id)
                }
                Routes.HistoriqueDetail.route -> {
                    HistoriqueDetailScreen(navController = navController, interventionCode = id)
                }
                Routes.Appareil.route -> {
                    AppareilScreen(navController = navController, interventionCode = id)
                }
                Routes.AppareilDetail.route -> {
                    AppareilDetailScreen(navController = navController, appareilCode = id)
                }
                Routes.Intervention.route -> {
                    IntervetionScreen(navController = navController)
                }
                Routes.InterventionValidation.route -> {
                    InterventionValidationScreen(navController = navController)
                }
            }
        }
    }
}


//@Composable
//fun QuitterDrawerRow(navController: NavController, technicien: Technicien, drawable: Int, scaffoldState: ScaffoldState, scope: CoroutineScope) {
//
//    Card(
//        onClick = { Fonction.onQuitterClickAction(navController, scaffoldState, scope, scaffoldState.drawerState.isOpen) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        elevation = 1.dp,
//        shape = MaterialTheme.shapes.medium,
//        backgroundColor = MaterialTheme.colors.surface.compositeOver(Color.White)
//    ) {
//        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
//
//            RedIcon(size = 50.dp, logo = drawable)
//            Text("Déconnexion", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colors.onSurface)
//        }
//    }
//}
//
//
//fun onQuitterClickAction(navController: NavController, scaffoldState: ScaffoldState, scope: CoroutineScope, isFromDrawer: Boolean) {
//    scope.launch {
//
//        if (isFromDrawer) scaffoldState.drawerState.close()
//
//        val result = scaffoldState.snackbarHostState
//            .showSnackbar(
//                message = DialogConst.quitter,
//                actionLabel = DialogConst.deconnecter,
//                duration = SnackbarDuration.Long
//            )
//
//        when (result) {
//            SnackbarResult.ActionPerformed -> {
//                navController.navigate(Routes.Login.route)
//            }
//            SnackbarResult.Dismissed -> { }
//        }
//    }
//
//}

@Composable
fun AppTopBar(routeObject: RouteObject) {
    val radius: Dp = 28.dp

    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {

//        BarBackgroundr Image()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                text = routeObject.label,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )

            RedIcon(size = 40.dp, logo = routeObject.defaultLogo)

        }
    }

}

@Composable
fun AppBottomBar() {
    val radius: Dp = 28.dp
    BottomAppBar(
        modifier = Modifier
//            .background(MaterialTheme.colors.background)
            .background(Color.Transparent)
            .clip(
                RoundedCornerShape(
                    topStart = radius,
                    topEnd = radius,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ),
//        cutoutShape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
//        backgroundColor = MaterialTheme.colors.background
    ) {
        Box() {
            Row(Modifier.fillMaxWidth()) {

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = BuildConfig.VERSION_NAME + "." + BuildConfig.BUILD_TYPE
                )
            }
        }
    }
}

@Composable
fun RedIcon(size: Dp, logo: Int) {
    Icon(
        modifier = Modifier
            .height(size)
            .width(size)
            .padding(end = 10.dp),
        painter = painterResource(id = logo),
        contentDescription = null,
        tint = Rouge_proxi_700
    )
}

@Composable
fun WhiteIcon(size: Dp, logo: Int) {
    Icon(
        modifier = Modifier
            .height(size)
            .width(size)
            .padding(end = 10.dp),
        painter = painterResource(id = logo),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun AppExtendedFloatingActionButton(navController: NavController) {
    val context = LocalContext.current as ComponentActivity
    val viewModel: ConnexionViewModel = hiltViewModel()
    ExtendedFloatingActionButton(
        containerColor = Color.Transparent,
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp)
            ) {
//                WhiteIcon(size = 60.dp, logo = Routes.Logout.logo)
                Icon(
                    painter = painterResource(Routes.Logout.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White,
                )
                //Text(text = Routes.Logout.label)
            }
        },
        onClick = { viewModel.logout(context, navController) }
    )
}
