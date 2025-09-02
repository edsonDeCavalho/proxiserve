package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.utils.FakeUtils
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.TextUtils
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.FdrUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.FeuilleDeRouteViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.event.FeuilleDeRouteEvent
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.ui.theme.Gris_100
import com.proxiserve.proximobilite.ui.theme.Orange_proxi
import com.proxiserve.proximobilite.ui.theme.Rouge_proxi_900
import com.proxiserve.proximobilite.ui.theme.Vert_valide_proxi
import timber.log.Timber
import java.lang.Math.random
import java.util.Locale

/**
 * Created by dloriot on 26/08/2024.
 */

private val TAG: String = "[FeuilleDeRouteItem]"

@Composable
fun FeuilleDeRouteItem(
    intervention: Intervention,
    navController: NavController,
    viewModel: FeuilleDeRouteViewModel = hiltViewModel()
) {
//    Timber.i("$TAG Item intervention n° ${intervention.interventionCode}")
    val context = LocalContext.current
    val nomContact = remember {"${intervention.contactNom?.uppercase()} ${intervention.contactPrenom}"}
    val adresse = remember {
        TextUtils.reduceTextLength(
            "${intervention.adresse}\n${intervention.cp} ${intervention.ville}",
            44
        )
    }
    val interventionType = remember {intervention.interventionTypeLibelle}
    val groupe = remember {"Groupe: ${intervention.groupe?.let { TextUtils.reduceTextLength(it, 14) }}"}
    val entree = remember {"Entrée: ${intervention.entree?.let { TextUtils.reduceTextLength(it, 14) }}"}
    val bat = remember {"Bât: ${intervention.bat?.let { TextUtils.reduceTextLength(it, 18) }}"}
    val etage = remember {"Etage: ${intervention.etage}"}
    val observation = remember {"Obs: ..."}
    val prestation = remember {
        listOf("CHGAZ", "MUMCO", "ERFOR", "MUDFU" ).random()
    } // = prestation active portant de l'interv
    val visiteConfirmee = remember { listOf("OUI", "NON").random() }
    val smsRandom = remember { listOf(0, 1, 2).random() }
    val smsStatusColors = remember { FakeUtils.getSmsStatusColor(smsRandom) }
    val smsStatus = remember { FakeUtils.getSmsStatus(smsRandom) }




    Surface(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 5.dp)
            .height(200.dp)
            .testTag(intervention.interventionCode),
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
            bottomEnd = 28.dp,
            bottomStart = 28.dp
        ),
        color = Gris_100,
        onClick = {
//            viewModel.onEvent(event = FeuilleDeRouteEvent.SelectIntervention(intervention = intervention))
            viewModel.onEvent(event = FeuilleDeRouteEvent.GoToDetail(navController = navController, id = intervention.interventionCode))
//            navController.navigate(Routes.DetailIntervention.route)
        }
    ) {

        Column(modifier = Modifier.padding(10.dp)
        ) {

            Row(
                modifier = Modifier.weight(6f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Infos Occupant
                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.70f),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                    ),

                    ) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = nomContact,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                lineHeight = 15.sp
                            )

                            Text(
                                text = adresse,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp
                            )
                            Text(
                                text = interventionType ?: "",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                lineHeight = 15.sp
                            )

                        }

                    }
                }

                HorizontalSpace(size = 5.dp)

                // Infos bâtiment
                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.70f),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                    ),

                    ) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp)
                                .fillMaxHeight(),
                        ) {
                            Text(
                                text = groupe,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp,
                            )
                            Text(
                                text = entree,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp
                            )
                            Text(
                                text = bat,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp
                            )
                            Text(
                                text = etage,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp
                            )
                            Text(
                                text = observation,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp
                            )
                        }

                    }
                }
            }

            VerticalSpace(size = 5.dp)

            // Infos Prestations
            Row(modifier = Modifier.weight(3f)) {
                Card(
                    modifier = Modifier.weight(5f),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.30f),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                    )
                ) {
                    Row(
                        Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            text = prestation,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )

                        HorizontalSpace(size = 10.dp)

                        VerticalDivider(color = Gris_100, thickness = 2.dp)

                        Column {

                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = "Visite confirmée: $visiteConfirmee",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            VerticalSpace(size = 5.dp)

                            BubbleMessage(
                                text = smsStatus,
                                color = smsStatusColors.copy(alpha = 0.70f)
                            )

                        }
                    }
                }

                HorizontalSpace(size = 5.dp)

                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                    )
                ) {
                    Row(
                        Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ){

                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                text = "Effectuée",
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 13.sp
                            )

                            HorizontalSpace(size = 5.dp)
                            HorizontalDivider(color = Gris_100, thickness = 2.dp)

                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                text = "Envoyée",
                                color = Vert_valide_proxi,
                                fontSize = 13.sp
                            )

                        }

                    }
                }
            }
        }
    }
}