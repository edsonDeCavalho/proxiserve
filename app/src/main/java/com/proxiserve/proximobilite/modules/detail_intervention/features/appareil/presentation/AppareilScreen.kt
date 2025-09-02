package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.LoadingScreen
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation.components.AppareilItem
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.ui.theme.Gris_100
import timber.log.Timber

/**
 * Created by dloriot on 07/09/2024.
 */

private val TAG = "[AppareilScreen]"

@Composable
fun AppareilScreen(
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    interventionCode: String
) {
    Timber.i("Entering $TAG with interventionCode $interventionCode")
    var correctInterventionCode = interventionCode
    if (interventionCode.contains("{interventionCode}"))
        correctInterventionCode = interventionCode.replace("{interventionCode}", "")
    val appareils by produceState<List<Appareil>?>(initialValue = null) {
        value = viewModel.getCurrentIntervention(id = correctInterventionCode)
            ?.let {
                viewModel.getInterventionAppareils(intervention = it)
            }
    }

    appareils?.let {
        Timber.d("$TAG appareils de l'intervention => %s", viewModel.gson.toJson(appareils))

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
                            text = Routes.Appareil.label,
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

                // Appareils

                Surface(
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 5.dp)
                        .weight(5f),
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    ),
                    color = Gris_100.copy(alpha = 0.9f),
                ) {

                    Column {

                        VerticalSpace(size = 30.dp)

                        Text(
                            text = "Veuillez sélectionner un appareil",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )


                        VerticalSpace(size = 30.dp)

                        appareils?.let { apps ->
                            LazyColumn(
                                modifier = Modifier
                                    .weight(5f)
                                    .padding(10.dp)
                                    .background(color = Color.Transparent)
                                    .clip(RoundedCornerShape(28.dp))
                            ) {
                                itemsIndexed(apps) { index, appareil ->

                                    val backgroundColor: Color =
                                        if (index % 2 == 0)
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.65f)
                                        else
                                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.80f)

                                    AppareilItem(
                                        appareil = appareil,
                                        navController = navController,
                                        color = backgroundColor
                                    )
                                }
                            }
                            VerticalSpace(size = 10.dp)
                        } ?: run {
                            LoadingScreen()
                        }
                    }

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
    }
}