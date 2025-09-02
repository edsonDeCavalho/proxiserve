package com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.VerticalSpace

import com.proxiserve.proximobilite.modules.intervention.presentation.IntervetionScreenViewModel
import com.proxiserve.proximobilite.modules.intervention.presentation.components.TextFieldInput
import com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.components.SelectDropString
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.InterventionValidationViewModel

/**
 * InterventionValidationScreen
 * @author Edson De Carvalho
 */
@Composable
fun InterventionValidationScreen(navController : NavController,
                                 viewModel: InterventionValidationViewModel = hiltViewModel()){

    var text_observation by remember { mutableStateOf("") }
    var text_message by remember { mutableStateOf("") }

    var selectedOption_resultat by remember { mutableStateOf("Termine") }
    var selectedOption_suite_a_donner by remember { mutableStateOf("Option 1") }


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
            VerticalSpace(size = 10.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center
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
                    Modifier
                        .padding(10.dp).verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Validation",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 26.sp,
                                fontFamily = FontFamily(Font(R.font.comfortaa_bold))
                            )
                        }
                    }
                    VerticalSpace(size = 5.dp)
                    Card(
                        modifier = Modifier

                            .padding(10.dp),
                        colors = CardColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.65f
                            ),
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                        )
                    ) {
                        Column(modifier = Modifier
                            .padding(10.dp)
                            ) {
                            Text(text = "Résultat* :")
                            SelectDropString(
                                options = listOf("Termine", "Absent", "En cours","Deplacement injustifie","Refus locataire"),
                                initialSelectedOption = "Termine",
                            ) { selected ->
                                selectedOption_resultat = selected
                            }
                            VerticalSpace(size = 5.dp)
                            Text(text = "Suite à donner :")
                            SelectDropString(
                                options = listOf("Option A", "Option B", "Option C"),
                                initialSelectedOption = "Option B",
                            ) { selected ->
                                selectedOption_suite_a_donner = selected
                            }
                            VerticalSpace(size = 5.dp)
                            Text(text = "Observation :")
                            TextFieldInput(
                                initialValue = text_observation,
                                onValueChange = { text_observation = it }
                            )
                            VerticalSpace(size = 5.dp)
                            Text(text = "Message à l'agence concernant cette intervention :")

                            TextFieldInput(
                                initialValue = text_message,
                                onValueChange = { text_message = it }
                            )
                            VerticalSpace(size = 5.dp)
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.CenterVertically),
                                text = "RAPPEL TECHNICIEN",
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                            Text(text = "(Enquête locataire):")
                            VerticalSpace(size = 5.dp)
                            Text(text = "* Le client a-t-il encore besoin de vous ? \n" +
                                    "* Est-il satifait de votre intervention ?\n" +
                                    "* Le client a-t-il compris ce que vous lui avez expliqué ?\n" +
                                    "* Si problèmes, les avez-vous été résolus ?\n" +
                                    "* Le logement du client est-il propre ? ")
                        }


                    }
                    VerticalSpace(size = 5.dp)
                    //Button Suivant
                    OutlinedButton(
                        onClick = { /**/ },
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

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    val navController = rememberNavController()
    InterventionValidationScreen(navController)
}