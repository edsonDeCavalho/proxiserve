package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionEvent
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.ui.theme.Gris_100
import timber.log.Timber

/**
 * Created by dloriot on 08/09/2024.
 */


private val TAG: String = "[HistoriqueItem]"

@Composable
fun HistoriqueItem(
    historique: Historique,
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel()
) {
    Timber.w("$TAG Historique lieu n° ${historique.lieuCode}")
    var date = remember { TimeUtils.dateMilisToString(historique.dateRDV) }
    if (date.isBlank()) date = "01/01/1970"
    val interventionType = remember { historique.interventionTypeLibelle }
    val prestation = remember { historique.prestations[0].prestationLibelle }
    val nomContact = remember { historique.contactNom }
    val prenomContact = remember { historique.contactPrenom }

    Surface(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 5.dp)
            .height(100.dp),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomEnd = 20.dp,
            bottomStart = 20.dp
        ),
        color = Gris_100,
        onClick = {
            viewModel.onEvent(event = DetailInterventionEvent.GoToSelectedHistorique(navController = navController, interventionCode = historique.interventionCode))
        }
    ) {
        Row() {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(5.dp),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.50f
                    ),
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        text = date,
                        lineHeight = 20.sp,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

//            HorizontalSpace(size = 5.dp)

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .padding(5.dp),
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
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 3.dp),
                        text = interventionType,
                        lineHeight = 15.sp,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        text = prestation,
                        lineHeight = 15.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
            HorizontalSpace(size = 5.dp)

//            Card(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.8f)
//                    .padding(5.dp),
//                colors = CardColors(
//                    containerColor = MaterialTheme.colorScheme.secondary.copy(
//                        alpha = 0.80f
//                    ),
//                    contentColor = MaterialTheme.colorScheme.onSecondary,
//                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
//                    disabledContentColor = MaterialTheme.colorScheme.secondary,
//                )
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.SpaceEvenly,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 10.dp, vertical = 3.dp),
//                        text = prenomContact,
//                        lineHeight = 20.sp,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSecondary,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center
//                    )
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 10.dp),
//                        text = nomContact,
//                        lineHeight = 20.sp,
//                        color = MaterialTheme.colorScheme.onSecondary,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
        }
    }
}