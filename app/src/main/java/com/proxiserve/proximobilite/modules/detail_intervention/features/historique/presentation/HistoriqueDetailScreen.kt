package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.AppareilHistorique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.PrestationHistorique
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import timber.log.Timber

/**
 * Created by dloriot on 07/09/2024.
 */

private val TAG = "[HistoriqueDetailScreen]"

@Composable
fun HistoriqueDetailScreen(
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    interventionCode: String
) {
    Timber.i("Entering $TAG id %s", interventionCode)
    val context: Context = LocalContext.current
    var correctInterventionCode = interventionCode
    if (interventionCode.contains("{interventionCode}"))
        correctInterventionCode = interventionCode.replace("{interventionCode}", "")
    val historique by produceState<Historique?>(initialValue = null) {
        value = viewModel.getCurrentHistorique(correctInterventionCode)
    }
    Timber.w("$TAG selectedHistorique ${historique?.interventionCode}")

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
                                navController.navigate(Routes.Accueil.route)
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(60.dp),
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
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

            historique?.let { historique ->
                val nomContact = remember { "${historique.contactNom.uppercase()} ${historique.contactPrenom}" }
                val adresse = remember { "${historique.adresse} ${historique.cp} ${historique.ville}" }
                val etage = remember { "Etage: ${historique.etage}" }
                val numero = remember { "N°: ${historique.numLogement}" }
                val interventionType = remember { historique.interventionTypeLibelle }
                val prestation: PrestationHistorique = remember { historique.prestations[0] }
                val technicien = remember { historique.loginTechnicien }
                val appareil: AppareilHistorique = remember { historique.appareils[0] }
                val dateRdv = remember { TimeUtils.dateMilisToString(historique.dateRDV)}
                val heureRdv = remember { "${historique.heureDebut} - ${historique.heureFin}" }
                val suite = remember { historique.suiteADonner }
                val observation = remember { historique.observation }
                val observationTech = remember { historique.observationTechnicien }

                Timber.i("$TAG ${viewModel.gson.toJson(appareil)} ${viewModel.gson.toJson(prestation)}" )
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .verticalScroll(rememberScrollState()),
                ) {

                    Surface(
                        shape = RoundedCornerShape(
                            topStart = 28.dp,
                            topEnd = 28.dp,
                            bottomEnd = 28.dp,
                            bottomStart = 28.dp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    ) {

                        Column(
                            Modifier
                                .padding(10.dp)
                        ) {
                            // Infos contact
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 400.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.80f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = nomContact,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = adresse,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    Row {

                                        Text(
                                            modifier = Modifier.weight(1f),
                                            text = etage,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )

                                        HorizontalSpace(size = 20.dp)

                                        Text(
                                            modifier = Modifier.weight(1f),
                                            text = numero,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )
                                    }

                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = interventionType,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                    )
                                }
                            }

                            VerticalSpace(size = 10.dp)

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 200.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.80f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Prestation: ${prestation.prestationLibelle}",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Technicien: $technicien",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Appareil: ${if (appareil.appareilModel.isNotBlank()) appareil.appareilModel else appareil.appareil}",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Date du rdv: $dateRdv",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Heure de RDV: $heureRdv",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp
                                    )
                                }
                            }

                            VerticalSpace(size = 10.dp)

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 400.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.80f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Suite à donner: $suite",
                                        color = Color.White,
                                        fontSize = 18.sp
                                    )

                                    VerticalSpace(size = 15.dp)

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Observations: $observationTech",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        lineHeight = 20.sp
                                    )
                                }
                            }
                        }
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