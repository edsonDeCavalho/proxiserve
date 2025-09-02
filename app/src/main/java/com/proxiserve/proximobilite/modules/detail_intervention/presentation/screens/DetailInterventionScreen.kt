package com.proxiserve.proximobilite.modules.detail_intervention.presentation.screens

import android.content.Context
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionEvent
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.components.PrestationItem
import com.proxiserve.proximobilite.ui.theme.Gris_100
import timber.log.Timber

/**
 * Created by dloriot on 05/09/2024.
 */
private val TAG = "[DetailInterventionScreen]"

@Composable
fun DetailInterventionScreen(
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    id: String
) {
    Timber.i("Entering $TAG id %s", id)
    val context: Context = LocalContext.current
    var correctId = id
    if (id.contains("{id}"))
        correctId = id.replace("{id}", "")
    val intervention by produceState<Intervention?>(initialValue = null) {
        value = viewModel.getCurrentIntervention(id)
    }
    val showDialog = remember { mutableStateOf(false) }
    Timber.w("$TAG selectedIntervention ${intervention?.interventionCode}")


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
                        text = Routes.DetailIntervention.label,
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
                                navController.navigate(Routes.Accueil.route + correctId)
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


            intervention?.let { intervention ->
                Timber.i("$TAG id %s", intervention.interventionCode)

                val nomContact =
                    remember { "${intervention.contactNom?.uppercase()} ${intervention.contactPrenom}" }
                val adresse =
                    remember { "${intervention.adresse}\n${intervention.cp} ${intervention.ville}" }
                val interventionType = remember { "${intervention.interventionTypeLibelle}" }
                val groupe = remember { "Groupe: ${intervention.groupe}" }
                val entree = remember { "Entrée: ${intervention.entree}" }
                val bat = remember { "Bât: ${intervention.bat}" }
                val etage = remember { "Etage: ${intervention.etage}" }
                val numero = remember { "N°: ${intervention.numLogement}" }
                val telephone =
                    remember { "Tel: ${intervention.telephone1 ?: intervention.telephone2 ?: intervention.telephone3}" }
                val client = remember { "Client: ${intervention.nomClient}" }
                val idIntervention =
                    remember { "N° Intervention: ${intervention.interventionCode}" }
                val observations = remember { "Observations: ${intervention.observation}" }
                val prestations = remember {
                    intervention.prestations
                }
                val visiteConfirmee = remember { listOf("OUI", "NON").random() }
                val lieuCode = remember { intervention.lieuCode }

                if (showDialog.value) {
                    PopupPrestation(showDialog = showDialog, prestations = prestations)
                }

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
                                    .heightIn(max = 200.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.65f
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
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .testTag("detail_test"),
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
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )

                                    VerticalSpace(size = 5.dp)

                                    Text(
                                        text = groupe,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                    )

                                    Row {

                                        Text(
                                            text = bat,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )

                                        HorizontalSpace(size = 20.dp)

                                        Text(
                                            text = entree,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )

                                    }
                                    Row {

                                        Text(
                                            text = etage,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )

                                        HorizontalSpace(size = 20.dp)

                                        Text(
                                            text = numero,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                        )
                                    }

                                    Text(
                                        text = telephone,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                    )

                                }


                            }

                            VerticalSpace(size = 5.dp)

                            // Infos Visite
//                            Row(Modifier.weight(5f)) {

                            Card(
                                modifier = Modifier.heightIn(max = 200.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.70f
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
                                        text = interventionType,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 18.sp
                                    )

                                    VerticalSpace(size = 5.dp)
                                    HorizontalDivider()

                                    prestations.forEach {
                                        Row {
                                            Text(
                                                modifier = Modifier.weight(1f),
                                                text = it.prestationCode,
                                                color = Color.White,
                                                fontSize = 16.sp,
                                                lineHeight = 18.sp
                                            )
                                            Text(
                                                modifier = Modifier.weight(2f),
                                                text = "Dernière visite: ${
                                                    TimeUtils.dateMilisToString(
                                                        it.dateDerniereVisite
                                                    )
                                                }",
                                                color = Color.White,
                                                fontSize = 16.sp,
                                                lineHeight = 18.sp
                                            )
                                        }
                                    }

                                    HorizontalDivider()
                                    VerticalSpace(size = 10.dp)

                                    Text(
                                        modifier = Modifier,
                                        text = client,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp
                                    )
                                    VerticalSpace(size = 10.dp)
                                    Text(
                                        modifier = Modifier,
                                        text = idIntervention,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp
                                    )
                                    VerticalSpace(size = 10.dp)
                                    Text(
                                        modifier = Modifier,
                                        text = "Visite Confirmée: $visiteConfirmee",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp
                                    )
                                    VerticalSpace(size = 10.dp)
                                    Text(
                                        modifier = Modifier,
                                        text = observations,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                            }


                            VerticalSpace(size = 5.dp)

                            // Infos Appareil
                            Card(
                                modifier = Modifier.height(50.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.70f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                )
                            ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = "Chaudière mur gaz:",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp
                                    )
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = "SAUNIER DUVAL",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 18.sp
                                    )
                                }

                            }

                            VerticalSpace(size = 5.dp)

                            // Lancement SMS et Historique
                            Row(Modifier.height(70.dp)) {

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.65f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
                                    Row {
                                        Icon(
                                            modifier = Modifier
                                                .weight(1f)
                                                .scale(1.5f)
                                                .fillMaxHeight()
                                                .wrapContentHeight(align = Alignment.CenterVertically),
                                            imageVector = Icons.Filled.Textsms,
                                            contentDescription = "SMS"
                                        )

                                        Text(
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxSize()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                                .padding(10.dp),
                                            text = "Prévenir par SMS",
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp
                                        )
                                    }
                                }

                                HorizontalSpace(size = 5.dp)

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.65f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),
                                    onClick = {
                                        lieuCode?.let {
                                            viewModel.onEvent(
                                                DetailInterventionEvent.GoToHistorique(
                                                    navController = navController,
                                                    lieuCode = lieuCode
                                                )
                                            )
                                        }

                                    }
                                ) {
                                    Row {
                                        Icon(
                                            modifier = Modifier
                                                .weight(1f)
                                                .scale(1.5f)
                                                .fillMaxHeight()
                                                .wrapContentHeight(align = Alignment.CenterVertically),
                                            imageVector = Icons.Filled.WorkHistory,
                                            contentDescription = "Historique"
                                        )

                                        Text(
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxSize()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                                .padding(10.dp),
                                            text = "Historique",
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp
                                        )
                                    }

                                }

                            }

                            VerticalSpace(size = 5.dp)
                            // Lancement CI
                            Card(
                                modifier = Modifier.height(70.dp),
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.80f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                ),
                                onClick = {
                                    // navController.navigate(Routes.AccueilCertificatIntervention.route)
                                    viewModel.onEvent(
                                        DetailInterventionEvent.GoToIntervantion(
                                            navController = navController,
                                        )
                                    )
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 10.dp, vertical = 3.dp)
                                        .wrapContentHeight(align = Alignment.CenterVertically),
                                    text = "Certificat d'Intervention",
                                    fontSize = 25.sp,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    textAlign = TextAlign.Center
                                )
                            }

                            VerticalSpace(size = 5.dp)

                            // Appareil et Prestation
                            Row(Modifier.height(70.dp)) {

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.65f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ), onClick = {
                                        viewModel.onEvent(
                                            DetailInterventionEvent.GoToAppareil(
                                                navController = navController, id = correctId
                                            )
                                        )
                                    }
                                ) {
                                    Row {
                                        Icon(
                                            modifier = Modifier
                                                .weight(1f)
                                                .scale(1.5f)
                                                .fillMaxHeight()
                                                .wrapContentHeight(align = Alignment.CenterVertically),
                                            imageVector = Routes.Appareil.icon,
                                            contentDescription = Routes.Appareil.label
                                        )

                                        Text(
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxSize()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                                .padding(10.dp),
                                            text = Routes.Appareil.label,
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp
                                        )
                                    }
                                }

                                HorizontalSpace(size = 5.dp)

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.65f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),
                                    onClick = {
                                        showDialog.value = true
                                    }
                                ) {
                                    Row {
                                        Icon(
                                            modifier = Modifier
                                                .weight(1f)
                                                .scale(1.5f)
                                                .fillMaxHeight()
                                                .wrapContentHeight(align = Alignment.CenterVertically),
                                            imageVector = Icons.Filled.Checklist,
                                            contentDescription = "Prestation"
                                        )

                                        Text(
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxSize()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                                .padding(10.dp),
                                            text = "Prestation",
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp
                                        )
                                    }

                                }

                            }

                            VerticalSpace(size = 5.dp)

                            // Lancement Absent, Refus et Injustifié
//                            Row(Modifier.weight(1f)) {
                            Row(Modifier.height(80.dp)) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(0.8f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.80f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 3.dp),
                                        text = "CI",
                                        fontSize = 20.sp,
                                        lineHeight = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp),
                                        text = "Client Absent",
                                        lineHeight = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                HorizontalSpace(size = 5.dp)

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(0.8f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.80f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 3.dp),
                                        text = "CI",
                                        lineHeight = 20.sp,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp),
                                        text = "Refus Client",
                                        lineHeight = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                HorizontalSpace(size = 5.dp)

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.80f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 3.dp),
                                        text = "CI",
                                        lineHeight = 20.sp,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp),
                                        text = "Déplacement Injustifié",
                                        lineHeight = 20.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            } ?: run {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.90f),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                    )
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        text = "Pas d'intervention sélectionnée...",
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center,
                    )
                }

            }

//            } else
//                Timber.e("$TAG Error: intervention null")

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

@Composable
fun PopupPrestation(showDialog: MutableState<Boolean>, prestations: List<Prestation>) {
    Timber.i("Entering $TAG PopupPrestation ")
    if (showDialog.value) {
        Dialog(onDismissRequest = {
            showDialog.value = false
        }) {

            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardColors(
                    containerColor = Gris_100,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(Modifier.weight(0.5f)) {
                        Text(
                            fontSize = 23.sp,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Prestations sous Contrat",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = screenHeight * 0.8f)
                            .weight(weight = 5f, fill = false),
                        verticalArrangement = Arrangement.Center
                    ) {

                        items(prestations) { prestation ->
                            PrestationItem(prestation = prestation)
                        }
                    }

                    Row(Modifier.weight(weight = 1f)) {
                        OutlinedButton(
                            modifier = Modifier.padding(30.dp),
                            onClick = {
                                showDialog.value = false
                            },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary.copy(alpha = 0.80f)),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "FERMER",
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}