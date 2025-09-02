package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation.components.HistoriqueItem
import timber.log.Timber

/**
 * Created by dloriot on 07/09/2024.
 */

private val TAG = "[HistoriqueScreen]"

@Composable
fun HistoriqueScreen(
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    lieuCode: String
) {
    Timber.i("Entering $TAG with lieuCode $lieuCode")
    var correctLieuCode = lieuCode
    if (lieuCode.contains("{lieuCode}"))
        correctLieuCode = lieuCode.replace("{lieuCode}", "")
    val historiques by produceState<List<Historique>?>(initialValue = null) {
        value = viewModel.getInterventionHistoriques(lieuCode = correctLieuCode)
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
                        text = Routes.Historique.label,
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
//                                navController.navigate(Routes.Accueil.route)
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

            historiques?.let { historiques ->
                LazyColumn(
                    modifier = Modifier
                        .weight(5f)
                        .background(color = Color.Transparent)
                        .clip(RoundedCornerShape(28.dp))
                ) {
                    items(historiques) { historique ->
                        HistoriqueItem(historique = historique, navController = navController)
                    }
                }
                VerticalSpace(size = 10.dp)
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
//    Box(Modifier.fillMaxSize()) {
//
//        Column(
//            modifier = Modifier.padding(10.dp)
//        ) {
//            BackgroundImage()
//
//            Image(
//                modifier = Modifier.width(500.dp),
//                painter = painterResource(id = R.drawable.symbole_pxs_white_trans_medium),
//                contentDescription = "Symblole PXS",
//                alpha = 0.5f
//            )
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp),
//                verticalArrangement = Arrangement.SpaceBetween,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                // Row:Message bienvenu Technicien / Icon Accueil
//
//                Row(Modifier.weight(0.8f)) {
//
//                    Column(
//                        modifier = Modifier
//                            .weight(5f)
//                            .fillMaxSize()
//                            .padding(start = 10.dp),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = Routes.Historique.label,
//                            fontWeight = FontWeight.Bold,
//                            color = MaterialTheme.colorScheme.secondary,
//                            fontSize = 26.sp,
//                            fontFamily = FontFamily(Font(R.font.comfortaa_bold))
//                        )
//                    }
//
//                    Column(
//                        modifier = Modifier
//                            .weight(1.5f)
//                            .fillMaxHeight()
//                            .padding(10.dp),
//                        horizontalAlignment = Alignment.End
//                    ) {
//                        // Accès Accueil
//                        Card(
//                            colors = CardColors(
//                                containerColor = Color.Transparent,
//                                contentColor = MaterialTheme.colorScheme.onSecondary,
//                                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
//                                disabledContentColor = MaterialTheme.colorScheme.secondary,
//                            ),
//                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
//                        ) {
//                            IconButton(
//                                modifier = Modifier.fillMaxSize(),
//                                onClick = { navController.navigate(Routes.Accueil.route) }
//                            ) {
//                                Icon(
//                                    modifier = Modifier.size(60.dp),
//                                    imageVector = Icons.Filled.Home,
//                                    contentDescription = "Accueil",
//                                    tint = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//            historiques?.let { historiques ->
//                Timber.i("$TAG historiques %s", Gson().toJson(historiques))
//                LazyColumn(
//                    modifier = Modifier
//                        .weight(5f)
//                        .background(color = Color.Transparent)
//                        .clip(RoundedCornerShape(28.dp)).background(Color.Cyan),
//                ) {
//                    items(historiques) { historique ->
//                        Text(text = historique.interventionCode)
////                        HistoriqueItem(historique = historique, navController = navController)
//                    }
//                }
//            } ?: run {
//                Timber.e(HistoriquesException(message = "Pas d'historique !"))
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardColors(
//                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.90f),
//                        contentColor = MaterialTheme.colorScheme.onSecondary,
//                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
//                        disabledContentColor = MaterialTheme.colorScheme.secondary,
//                    )
//                ) {
//
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(20.dp),
//                        text = "Pas d'historique pour cette intervention...",
//                        color = MaterialTheme.colorScheme.onError,
//                        textAlign = TextAlign.Center,
//                    )
//                }
//            }
//
//            // Bottom
//            Row(
//                modifier = Modifier
//                    .weight(0.5f)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(modifier = Modifier.weight(0.5f), text = AppConstant.APP_NAME)
//            }
//        }
//    }
}