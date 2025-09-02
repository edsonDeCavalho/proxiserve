package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionEvent
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import timber.log.Timber

/**
 * Created by dloriot on 08/09/2024.
 */


private val TAG: String = "[AppareilItem]"

@Composable
fun AppareilItem(
    appareil: Appareil,
    navController: NavController,
    color: Color,
    viewModel: DetailInterventionViewModel = hiltViewModel()
) {
    Timber.w("$TAG Appareil code n° ${appareil.appareilCode}")

    val appareilType = remember { appareil.libelle }
    val appareilMarque = remember { appareil.marqueCode }
    val appareilModel = remember { appareil.modele }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                modifier = Modifier
                    .height(130.dp)
                    .padding(10.dp),
                colors = CardColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                ),
                onClick = {
                    viewModel.onEvent(
                        DetailInterventionEvent.GoToSelectedAppareil(
                            navController = navController,
                            appareilCode = appareil.appareilCode
                        )
                    )
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        text = appareilType?: "Type",
                        lineHeight = 20.sp,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        text = appareilMarque?: "Marque",
                        lineHeight = 20.sp,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        text = appareilModel?: "Model",
                        lineHeight = 20.sp,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            HorizontalSpace(size = 5.dp)

        }

}