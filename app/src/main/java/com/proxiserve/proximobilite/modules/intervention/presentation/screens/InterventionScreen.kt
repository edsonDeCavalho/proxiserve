package com.proxiserve.proximobilite.modules.intervention.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.presentation.IntervetionScreenEvent
import com.proxiserve.proximobilite.modules.intervention.presentation.IntervetionScreenViewModel
import com.proxiserve.proximobilite.modules.intervention.presentation.components.CheckdInput
import com.proxiserve.proximobilite.modules.intervention.presentation.components.DropdownInput
import com.proxiserve.proximobilite.modules.intervention.presentation.components.MesureInput
import com.proxiserve.proximobilite.modules.intervention.presentation.components.OuiNonChooserInput
import com.proxiserve.proximobilite.modules.intervention.presentation.components.TimeHourInput


/**
 * IntervetionScreen
 * @author Edson De Carvalho
 */
@Composable
fun IntervetionScreen(navController : NavController,
                      id_intervention : String = "",
                      viewModel: IntervetionScreenViewModel = hiltViewModel()
){
    var checked_chgaz_p2p3 by remember { mutableStateOf(false) }
    var checked_chvbo_p2p3 by remember { mutableStateOf(false) }
    var checked_aucun_pieces_p by remember { mutableStateOf(false) }
    var heureDeFin : String = "10:32"
    var duree : String =""
    //Heure debut
    var selectedHour_debut by remember { mutableStateOf(0) }
    var selectedMinute_debut by remember { mutableStateOf(0) }
    // Durée
    var selectedHour_duree by remember { mutableStateOf(0) }
    var selectedMinute_duree by remember { mutableStateOf(0) }

    /**
     * Infos supplementaires
     */
    var checked_certificat_ramonage by remember { mutableStateOf(false) }
    var checked_miseArret by remember { mutableStateOf(false) }
    var checked_test_vaculte by remember { mutableStateOf(false) }
    var checked_mesure_gaz_impossible by remember { mutableStateOf(false) }

    var mesureValue_coAmbiant by remember { mutableStateOf("") }
    var mesureValue_tirage by remember { mutableStateOf("") }
    var mesureValue_fumees by remember { mutableStateOf("") }
    var mesureValue_co2 by remember { mutableStateOf("") }
    var mesureValue_o2 by remember { mutableStateOf("") }

    var ouiNonSelection_testdsc by remember { mutableStateOf("Oui") }
    var ouiNonSelection_boucle_eau_chaude by remember { mutableStateOf("Oui") }
    var ouiNonSelection_circuit_hydraulic by remember { mutableStateOf("Oui") }
    var ouiNonSelection_pose_daaf by remember { mutableStateOf("Oui") }
    var ouiNonSelection_s_ambiante  by remember { mutableStateOf("Oui") }
    var ouiNonSelection_s_plomb by remember { mutableStateOf("Oui") }
    var ouiNonSelection_flocage by remember { mutableStateOf("Oui") }
    var ouiNonSelection_faux_plafond by remember { mutableStateOf("Oui") }

    var selectedDropdownOption_etatVentilation by remember { mutableStateOf("Option 1") }

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
                .padding(10.dp),
                //.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            VerticalSpace(size = 10.dp)
            Row(verticalAlignment = Alignment.CenterVertically) { // Add verticalAlignment to Row
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center // Center vertically within Column
                ) {
                    Text(
                        text = Routes.Intervention.label,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.comfortaa_bold))
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(5.dp),
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
                        modifier = Modifier.heightIn(5.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
                    ) {
                        IconButton(
                            modifier = Modifier.heightIn(16.dp),
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
            VerticalSpace(size = 10.dp)
            Surface(
                shape = RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp,
                    bottomEnd = 28.dp,
                    bottomStart = 28.dp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(600.dp)
            ) {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
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
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("detail_test"),
                                text = "(*champ obligatoire)      26/06/2023",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start
                            )
                            TimeHourInput(text = "Heure de début : ") { hour, minute ->
                                selectedHour_debut = hour
                                selectedMinute_debut = minute
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                                text = "Heure de fin :     ${heureDeFin}",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start
                            )

                            TimeHourInput(text ="Durée* :") { hour, minute ->
                                selectedHour_duree = hour
                                selectedMinute_duree = minute
                            }
                            Text(
                                modifier = Modifier
                                    .testTag("detail_test"),
                                text = "Visites réalisées * :",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start
                            )
                            CheckdInput("CHGAZ - P2P3")
                            { isChecked ->
                                checked_chgaz_p2p3 = isChecked
                            }
                            CheckdInput("CHVBO - P2P3")
                            { isChecked ->
                                checked_chvbo_p2p3 = isChecked
                            }
                            CheckdInput("Aucunes pièces posées")
                            { isChecked ->
                                checked_aucun_pieces_p = isChecked
                            }
                        }
                    }
                    VerticalSpace(size = 5.dp)
                    Row(Modifier.height(70.dp)) {
                        //Operations réalisées
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.65f
                                ),
                                contentColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                        ) {
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxSize()
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                        .padding(10.dp),
                                    text = "OPERATIONS REALISEES",
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp
                                )
                            }
                        }

                        HorizontalSpace(size = 5.dp)
                        //Pieces utilisées
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.65f
                                ),
                                contentColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                            onClick = {
                                viewModel.onEvent(event = IntervetionScreenEvent.InsertPhoto(
                                Photo()
                            ))  }
                        ) {
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxSize()
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                        .padding(10.dp),
                                    text = "PIECES UTLISEES",
                                    color =Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }


                    VerticalSpace(size = 5.dp)

                    //SCANNER QRCode OU AJOUTER PHOTOS
                    Card(
                        modifier = Modifier.height(70.dp),
                        colors = CardColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.30f
                            ),
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = { viewModel.onEvent(event = IntervetionScreenEvent.InsertPhoto(
                            Photo(id = "2")
                        )) }
                    ) {
                        //
                        Row(Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxSize()
                                        .wrapContentHeight(align = Alignment.CenterVertically),
                                    text = "SCANNER QRCode OU AJOUTER PHOTOS",
                                    color = MaterialTheme.colorScheme.secondary,
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    VerticalSpace(size = 5.dp)

                    VerticalSpace(size = 5.dp)
                    //Partie infos supplementaires
                    Card(
                        modifier = Modifier.fillMaxHeight(),
                        colors = CardColors(
                            containerColor = MaterialTheme.colorScheme.secondary.copy(
                                alpha = 0.65f
                            ),
                            contentColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = {  }
                    ) {
                        //
                        Row(Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxSize()
                                        .wrapContentHeight(align = Alignment.CenterVertically),
                                    text = "INFO SUPLEMENTAIRE",
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp
                                )
                                CheckdInput("Certificat de ramonage")
                                { isChecked ->
                                    checked_certificat_ramonage = isChecked
                                }
                                OuiNonChooserInput("Test DSC")
                                { selection ->
                                    ouiNonSelection_testdsc = selection
                                }

                                CheckdInput("Mise à l’arrêt de l’appareil :")
                                { isChecked ->
                                    checked_miseArret = isChecked
                                }
                                CheckdInput("Test de vacuité :")
                                { isChecked ->
                                    checked_test_vaculte = isChecked
                                }
                                OuiNonChooserInput("Appareils raccordé à une boucle d’eau chaude *:")
                                { selection ->
                                    ouiNonSelection_boucle_eau_chaude = selection
                                }
                                OuiNonChooserInput("-> (si oui : contrôle du circuit hydraulique *) :")
                                { selection ->
                                    ouiNonSelection_circuit_hydraulic = selection
                                }
                                OuiNonChooserInput("Pose DAAF :")
                                { selection ->
                                    ouiNonSelection_pose_daaf = selection
                                }
                                CheckdInput("Mesure gaz impossible :")
                                { isChecked ->
                                    checked_mesure_gaz_impossible = isChecked
                                }
                                //Mesures
                                MesureInput("Co ambiant","ppm")
                                { newValue ->
                                    mesureValue_coAmbiant = newValue
                                }
                                MesureInput("Etat ventilation","")
                                { newValue ->
                                    mesureValue_coAmbiant = newValue
                                }
                                DropdownInput(
                                    question = "Etat ventilation",
                                    options = listOf("Degages", "Obstrues degages par technicien", "Obstrues non degagees (refus du residant)"),
                                    selectedOption = selectedDropdownOption_etatVentilation,
                                    onOptionSelected = { selectedOption ->
                                        selectedDropdownOption_etatVentilation = selectedOption
                                    }
                                )
                                MesureInput("Tirage Mbar/hPa","Mbar/hPa")
                                { newValue ->
                                    mesureValue_tirage = newValue
                                }
                                MesureInput("T° des fumées (C°)","C°")
                                { newValue ->
                                    mesureValue_fumees = newValue
                                }
                                MesureInput("%CO2 (ppm)","ppm")
                                { newValue ->
                                    mesureValue_co2 = newValue
                                }
                                VerticalSpace(size = 5.dp)

                                MesureInput("%O2 (ppm)","ppm")
                                { newValue ->
                                    mesureValue_o2 = newValue
                                }
                                OuiNonChooserInput("suspicion amiante")
                                { selection ->
                                    ouiNonSelection_s_ambiante = selection
                                }
                                OuiNonChooserInput("suspicion plomb")
                                { selection ->
                                    ouiNonSelection_s_plomb = selection
                                }
                                OuiNonChooserInput("suspicion flocage")
                                { selection ->
                                    ouiNonSelection_flocage = selection
                                }
                                OuiNonChooserInput("suspicion faux plafond")
                                { selection ->
                                    ouiNonSelection_faux_plafond = selection
                                }

                            }
                        }
                    }
                    VerticalSpace(size = 5.dp)
                    //Réaliser un audit
                    Card(
                        modifier = Modifier.height(70.dp),
                        colors = CardColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.30f
                            ),
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = {  }
                    ) {
                        //
                        Row(Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxSize()
                                        .wrapContentHeight(align = Alignment.CenterVertically),
                                    text = "Réaliser un Audit",
                                    color = MaterialTheme.colorScheme.secondary,
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    VerticalSpace(size = 5.dp)
                    OutlinedButton(
                        onClick = { viewModel.onEvent(event = IntervetionScreenEvent.GoToInterventionValication(navController = navController)) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("SUIVANT", fontSize = 18.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }
                }
            }
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




