package com.proxiserve.proximobilite.modules.connexion.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.TextContour
import com.proxiserve.proximobilite.modules.connexion.presentation.ConnexionViewModel
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by dloriot on 10/07/2024.
 */
private val TAG = "[LoginScreen]"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: ConnexionViewModel = hiltViewModel()
) {
    Timber.i("Entering $TAG")
    val context = LocalContext.current
    val userState by rememberUpdatedState(newValue = viewModel.userState.collectAsState())
    val scope = rememberCoroutineScope()


    BackgroundImage()

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {

            //            Timber.d("ConnexionState: %s", )
            // Logo Proxiserve
            Image(
                painter = painterResource(R.drawable.logo_pxs_big),
                contentDescription = "logo",
                alpha = 0.8f,
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Bouton Connexion
                OutlinedButton(
                    modifier = Modifier.padding(30.dp),
                    onClick = {
                        scope.launch {
                            viewModel.login(context)
                        }

//                        viewModel.onEvent(ConnexionEvent.Login)
//                        navController.navigate(Routes.Accueil.route)
//                        navController.navigate(Routes.ModeAdmin.route)
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            Routes.Login.label.uppercase(),
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.padding(end = 15.dp)
                        )

                        //                        if (isProgressBarVisible)
                        //                            CircularProgressBar(modifier = Modifier.width(30.dp))
                        //                        else
                        Image(
                            painter = painterResource(Routes.Login.logo),
                            contentDescription = "logo",
                            alpha = 0.8f,
                            modifier = Modifier
                                .width(30.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )


                    }

                }
                if (userState.value.errorMessage != null) {
                    Card(Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = userState.value.errorMessage!!,
//                            text = "Erreur: identifiant introuvable ! Vous allez être redirigé vers Okta",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    // Bouton Suppression token
                    OutlinedButton(
                        modifier = Modifier.padding(30.dp),
                        onClick = {
                            viewModel.logout(context, navController)
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Supprimer mon token",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.padding(end = 15.dp)
                            )

                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "logo",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .width(30.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )


                        }

                    }

                    if (AppConstant.ERREUR_SERVER == userState.value.errorMessage){
                        scope.launch {
                            delay(6000)
                            viewModel.logout(context = context, navController = navController)
                        }

                    }
                }
            }


            // Indication version app
            val versionApp =
                "Proximobilite." + BuildConfig.VERSION_NAME + "." + BuildConfig.BUILD_TYPE

            Row(modifier = Modifier.background(Color.Yellow)) {
                TextContour(
                    text = versionApp,
                    fontSize = 40.dp,
                    contourColor = Color.Black.toArgb(),
                    innerColor = Color.White.toArgb()
                )
            }

            //                Text(
            //                    text = "Proximobilite." + BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE + "." + BuildConfig.BUILD_TYPE,
            //                    fontSize = 22.sp,
            //                    fontWeight = FontWeight.Bold,
            //                    color = MaterialTheme.colorScheme.onBackground,
            //                    textDecoration = TextDecoration.Underline.
            //                )
        }
    }

}

