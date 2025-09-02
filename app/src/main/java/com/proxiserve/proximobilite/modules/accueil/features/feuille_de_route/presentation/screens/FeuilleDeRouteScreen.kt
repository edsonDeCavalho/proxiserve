package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.ErrorScreen
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.LoadingScreen
import com.proxiserve.proximobilite.core.utils.TestTag
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.CategorizedLazyColumn
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.CurrentFeuilleDeRouteException
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.FeuilleDeRouteViewModel
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.SortedMap

/**
 * Created by dloriot on 26/08/2024.
 */

private val TAG = "[FeuilleDeRouteScreen]"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FeuilleDeRouteScreen(
    navController: NavController,
    viewModel: FeuilleDeRouteViewModel = hiltViewModel(),
    id: String
) {
    Timber.i("Entering $TAG id $id")

    val state by rememberUpdatedState(newValue = viewModel.fdrState.collectAsState())

    state.value.errorMessage?.let {
        ErrorScreen(message = it)
    }?: run {
        state.value.currentFeuilleDeRoute?.let { feuilleDeRoute ->
            val sortedInterventionMap: SortedMap<Pair<String?, String?>, List<Intervention>> = remember {
                (
                    feuilleDeRoute.let {
                        viewModel.getIntervention(it.interventions)
                    }
                )
            }

            Box(Modifier.fillMaxSize()) {

                BackgroundImage()

                Image(
                    modifier = Modifier.width(500.dp),
                    painter = painterResource(id = R.drawable.symbole_pxs_white_trans_medium),
                    contentDescription = "Symblole PXS",
                    alpha = 0.5f
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Row:Message bienvenu Technicien / Icon Accueil

                    Row(Modifier.weight(0.8f)) {

                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .fillMaxSize()
                                .padding(start = 10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = Routes.FeuilleDeRoute.label,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 26.sp,
                                fontFamily = FontFamily(Font(R.font.comfortaa_bold))
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1.5f)
                                .fillMaxHeight()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            // Accès Accueil
                            Card(
                                colors = CardColors(
                                    containerColor = Color.Transparent,
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                ),
                                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
                            ) {
                                IconButton(
                                    modifier = Modifier.fillMaxSize(),
                                    onClick = {
                                        navController.navigate(Routes.Accueil.route)
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(60.dp),
                                        imageVector = Icons.Filled.Home,
                                        contentDescription = "Next",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }

                    // Interventions

                    Row {
                        HorizontalSpace(size = 30.dp)
                    }

                    CategorizedLazyColumn(
                        modifier = Modifier
                            .weight(5f)
                            .background(color = Color.Transparent)
                            .clip(RoundedCornerShape(28.dp))
                            .testTag(TestTag.FDR_LAZY_COLUMN),
                        sortedMap = sortedInterventionMap,
                        navController = navController,
                        viewModel = viewModel
                    )
                    VerticalSpace(size = 10.dp)
                    OutlinedButton(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Filtrer", fontSize = 18.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }

                    // Bottom
                    Row(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = AppConstant.APP_NAME)
                    }
                }
            }
        }?: run {

            LoadingScreen()

            Timber.e(CurrentFeuilleDeRouteException(message = "Erreur lors de la récupération de la feuille de route"))
        }
    }
}