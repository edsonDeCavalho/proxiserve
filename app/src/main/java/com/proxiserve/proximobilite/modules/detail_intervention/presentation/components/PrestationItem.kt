package com.proxiserve.proximobilite.modules.detail_intervention.presentation.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionEvent
import com.proxiserve.proximobilite.ui.theme.Gris_100

/**
 * Created by dloriot on 18/12/2024.
 */


private val TAG: String = "[PrestationItem]"

@Composable
fun PrestationItem(prestation: Prestation) {

    var date = remember { TimeUtils.dateMilisToString(prestation.dateDerniereVisite) }
    if (date.isBlank()) date = "01/01/1970"
    val prestationActive = prestation.prestationActive.let { if (it == 1) "Oui" else "Non" }


    Surface(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 5.dp)
            .height(150.dp),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomEnd = 20.dp,
            bottomStart = 20.dp
        ),
        color = MaterialTheme.colorScheme.primary.copy(
            alpha = 0.40f
        ),
        onClick = {}
    ) {
        Row() {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.30f
                    ),
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = "DATE",
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = date,
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = "CODE",
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = prestation.prestationCode,
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = "Libellé".uppercase(),
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = prestation.prestationLibelle,
                            lineHeight = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = "ACTIVE",
                            lineHeight = 15.sp,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            text = prestationActive,
                            lineHeight = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }


                HorizontalSpace(size = 5.dp)
            }
        }
    }
}