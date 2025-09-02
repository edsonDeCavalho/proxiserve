package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.screens

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.LoadingScreen
import com.proxiserve.proximobilite.core.utils.TextContour
import com.proxiserve.proximobilite.core.utils.VerticalSpace
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.screens.SelectTechnicienScreen
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.screens.SetParamScreen
import com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.MonCompteEvent
import com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.MonCompteViewModel
import com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation.components.AppTextField
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.ui.theme.Gris_100
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

/**
 * Created by dloriot on 15/07/2024.
 *
 */

private val TAG = "[MonCompteScreen]"

@Composable
fun MonCompteScreen(
    navController: NavController,
    viewModel: MonCompteViewModel = hiltViewModel(),
    id: String
) {
    Timber.i("Entering $TAG}")
    var correctId = id
    if (id.contains("{id}"))
        correctId = id.replace("{id}", "")
    val context = LocalContext.current
    val state by rememberUpdatedState(newValue = viewModel.mcState.collectAsState())
//    val user by produceState<User?>(initialValue = null) {
//        value = viewModel.mcState.value?.user
////        value = viewModel.getUser()
//    }


    state.value.user?.let {
        val nom = it.nom
        val prenom = it.prenom
        val userId = it.id
        val matricule = it.matricule
        val agence = it.mailAgence
        val groupe = it.groupe.replace("TEST_PROXIMOB_", "")
        val scope = rememberCoroutineScope()

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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Row:Message bienvenu Technicien / Icon Account

                Row(Modifier.weight(0.8f)) {

                    Column(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxSize()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Mon Compte",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 35.sp,
                            fontFamily = FontFamily(Font(R.font.comfortaa_bold)),
                            style = TextStyle(
                                shadow = Shadow(color = Color.White)
                            )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Card(
                            colors = CardColors(
                                containerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.onSecondary,
                                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                disabledContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
//                        elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.fillMaxSize(),
                                onClick = {
                                    scope.launch {
                                        viewModel.onEvent(
                                            event = MonCompteEvent.GoToAccueil(
                                                navController = navController,
                                                correctId
                                            )
                                        )
                                    }

                                navController.navigate(Routes.Accueil.route + correctId)
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.size(60.dp),
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Accueil",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }

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
//                color = MaterialTheme.colorScheme.onBackground
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "INFORMATIONS DU COMPTE", color = MaterialTheme.colorScheme.primary,
                                fontSize = 23.sp
                            )
                        }

                        VerticalSpace(size = 20.dp)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            AppTextField(label = "NOM", text = nom, enable = false)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "PRENOM", text = prenom, enable = false)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "ID", text = userId, enable = false)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "MATRICULE", text = matricule, enable = false)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "AGENCE", text = agence, enable = false)

                            VerticalSpace(size = 20.dp)

                            AppTextField(label = "GROUPE", text = groupe, enable = false)

                            VerticalSpace(size = 20.dp)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedButton(
                                    colors = ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                                    onClick = {
//
                                        scope.launch {
                                            viewModel.login(context = context)
                                            viewModel.onEvent(event = MonCompteEvent.SynchroProfil(id = it.id))

                                        }

//                                        navController.navigate(Routes.ModeAdmin.route + correctId)
                                    }
                                ) {

                                    Row {
                                        Text(text = "SYNCHRO PROFIL", color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)
                                        HorizontalSpace(size = 20.dp)
                                        Icon(
                                            imageVector = Icons.Filled.Downloading,
                                            contentDescription = "Next",
                                            tint = MaterialTheme.colorScheme.onSecondary
                                        )
                                    }

                                }
                            }

                            VerticalSpace(size = 10.dp)

                            if (it.groupe == UserGroup.GROUP_ADMIN.type || it.groupe == UserGroup.GROUP_CI.type ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    OutlinedButton(
                                        colors = ButtonColors(
                                            containerColor = Gris_100,
                                            disabledContainerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = Color.White,
                                            disabledContentColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                                        onClick = {
                                            scope.launch {
                                                viewModel.onEvent(
                                                    event = MonCompteEvent.GoToModeAdmin(
                                                        navController = navController,
                                                        correctId
                                                    )
                                                )
                                            }

//                                        navController.navigate(Routes.ModeAdmin.route + correctId)
                                        }
                                    ) {

                                        Row {
                                            Text(text = "MODE ADMIN", color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp)
                                            HorizontalSpace(size = 20.dp)
                                            Icon(
                                                imageVector = Icons.Filled.ChevronRight,
                                                contentDescription = "Next",
                                                tint = MaterialTheme.colorScheme.secondary
                                            )
                                        }

                                    }
                                }

                                VerticalSpace(size = 40.dp)
                            } //else
                                //Toast.makeText(context, "$TAG Groupe => " + it.groupe, Toast.LENGTH_LONG).show()


                        }


                    }

                }


                Row(
                    modifier = Modifier.weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = AppConstant.APP_NAME)
                }
            }
        }
    }?: run {
        LoadingScreen()
    }

}