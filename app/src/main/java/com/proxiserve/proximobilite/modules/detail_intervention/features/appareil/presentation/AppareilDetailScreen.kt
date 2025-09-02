package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.proxiserve.proximobilite.core.utils.LoadingScreen
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.components.AppTextField
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.presentation.components.AppareilItem
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.ui.theme.Gris_100
import timber.log.Timber

/**
 * Created by dloriot on 07/09/2024.
 */

private val TAG = "[AppareilDetailScreen]"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppareilDetailScreen(
    navController: NavController,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    appareilCode: String
) {
    Timber.i("Entering $TAG id %s", appareilCode)
    val context: Context = LocalContext.current
    var correctAppareilCode = appareilCode
    if (appareilCode.contains("{appareilCode}"))
        correctAppareilCode = appareilCode.replace("{appareilCode}", "")
    val appareil by produceState<Appareil?>(initialValue = null) {
        value = viewModel.getCurrentAppareil(id = correctAppareilCode)
    }
    Timber.w("$TAG selected Appareil ${appareil?.appareilCode}")

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
                        text = Routes.AppareilDetail.label,
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

            // Appareil

            Row {
                HorizontalSpace(size = 30.dp)
            }

            appareil?.let { appareil1 ->
                val type = remember { appareil1.libelle ?: ""}
                val marque = remember { appareil1.marqueCode ?: "" }
                val modele = remember { appareil1.modele ?: ""}
                val numSerie = remember { appareil1.numeroSerie ?: ""}
                val anneeFabrication by remember { mutableStateOf("") }
                val quantite by remember { mutableStateOf(0) }
                val miseEnService = remember { TimeUtils.dateMilisToString(appareil1.dateMiseEnService) }
                val garantie = remember { appareil1.dureeGarantie ?: ""}

                // Chauffage
                val energie = remember { appareil1.energieCode ?: ""}
                val combustible by remember { mutableStateOf("") }
                val typeChaudiere = remember { appareil1.chauffageChaudiereTypeCode ?: ""}
                val puissance = remember { appareil1.chauffagePuissanceNominale ?: ""}
                val typeEvacuation = remember { appareil1.chauffageEvacuationTypeCode ?: ""}
                val rendement = remember { appareil1.chauffageChaudiererendementCode ?: ""}
                val rendementRef by remember { mutableStateOf("") }
                val emissionOxydeAzote = remember { appareil1.chauffageEmissionOxydeAzoteCode ?: ""}
                val emissionOxydeAzoteRef by remember { mutableStateOf("") }
                val classeEnergetique by remember { mutableStateOf("") }
                val typeReseau = remember { appareil1.typeReseau ?: ""}
                val matiereCanalisation = remember { appareil1.materCanalisation ?: ""}

                // Bouche VMC
                var expanded by remember { mutableStateOf(false) }
                val uniteMesureOption = remember { listOf("", "Débit (m3/h)", "Dépression (Pa)") }
                var uniteMesure by remember { mutableStateOf(uniteMesureOption[0]) }
                val sdb = remember { appareil1.boucheVmcUniteSalleDeBain ?: ""}
                val cuisine = remember { appareil1.boucheVmcUniteCuisine ?: ""}
                val wc = remember { appareil1.boucheVmcUniteWc ?: ""}
                val autres = remember { appareil1.boucheVmcUniteAutre ?: ""}
                val dsc = remember { appareil1.boucheVmcUniteInterrupteurDsc }
                val nbEntreeAir = remember { appareil1.boucheVmcUniteNbEntreeAir ?: ""}


                Surface(
                    modifier = Modifier
                        .weight(5f),
                    shape = RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp,
                        bottomEnd = 28.dp,
                        bottomStart = 28.dp
                    ),
                    color = Gris_100
                ) {

                    Column(
                        Modifier
                            .fillMaxSize()
                    ) {


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppareilItem(
                                appareil = appareil1,
                                navController = navController,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f)

                            )

                            AppTextField(label = "Marque", text = marque, enable = true)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "Modèle", text = modele, enable = true)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "Numéro de série", text = numSerie, enable = true)

                            VerticalSpace(size = 20.dp)

                            if (type.contains("chau", true)) {

                                AppTextField(label = "Energie", text = energie, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Combustible", text = combustible, enable = true)

                                VerticalSpace(size = 20.dp)
                            }

                            AppTextField(label = "Année fabrication", text = anneeFabrication, enable = true)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "Quantité", text = quantite.toString(), enable = true)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "Fin garantie", text = garantie, enable = true)

                            // Infos bouche vmc
                            if (type.contains("bouche", true)) {

                                VerticalSpace(size = 20.dp)

                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = it },
                                ) {

                                    OutlinedTextField(
                                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                                        value = uniteMesure,
                                        onValueChange = {},
                                        readOnly = true,
                                        singleLine = true,
                                        label = {
                                            Text(
                                                "Unité de messure",
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedTextColor = MaterialTheme.colorScheme.primary,
                                            unfocusedTextColor = MaterialTheme.colorScheme.primary,
                                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                                            focusedBorderColor = MaterialTheme.colorScheme.secondary
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    ExposedDropdownMenu(
                                        modifier = Modifier
                                            .background(Gris_100)
                                            .padding(10.dp),
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false },
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        uniteMesureOption.forEach { option ->
                                            DropdownMenuItem(
                                                modifier = Modifier.background(Gris_100),
                                                text = {
                                                    Text(
                                                        text = option,
                                                        style = MaterialTheme.typography.bodyLarge,
                                                        color = MaterialTheme.colorScheme.primary,
                                                    )
                                                },
                                                onClick = {
                                                    uniteMesure = option.toString()
                                                    expanded = false
                                                },
                                                contentPadding = PaddingValues(vertical = 0.dp),
                                            )
                                        }
                                    }
                                }

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Salle de bain", text = sdb, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Cuisine", text = cuisine, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "WC", text = wc, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Autres", text = autres, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Interrupteur DSC", text = if (dsc) "Oui" else "Non", enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Entrée d'air", text = nbEntreeAir, enable = true)

                                VerticalSpace(size = 20.dp)
                            }

                            // Infos Chau
                            if (type.contains("chau", true)) {
                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Type de chaudière", text = typeChaudiere, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Puissance nominale (kWh)", text = puissance, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Type d'évacuation", text = typeEvacuation, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Rendement", text = rendement, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Rendement de référence", text = rendementRef, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Emission d'Oxyde d'azote", text = emissionOxydeAzote, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Valeur de référence", text = emissionOxydeAzoteRef, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Classe énergétique", text = classeEnergetique, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Type de réseau", text = typeReseau, enable = true)

                                VerticalSpace(size = 20.dp)

                                AppTextField(label = "Matériaux canalisation", text = matiereCanalisation, enable = true)

                                VerticalSpace(size = 20.dp)
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedButton(
                                    colors = ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f),
                                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                                    onClick = { }
                                ) {

                                    Row {
                                        Text(text = "VALIDER", color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)
                                        HorizontalSpace(size = 40.dp)
                                        Icon(
                                            modifier = Modifier.size(20.dp),
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = "Next",
                                            tint = MaterialTheme.colorScheme.onSecondary
                                        )
                                    }

                                }
                            }
                        }


                        VerticalSpace(size = 10.dp)

                    }
                }

            }?: run {
                LoadingScreen()
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
