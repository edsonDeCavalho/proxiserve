package com.proxiserve.proximobilite.modules.accueil.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.proxiserve.proximobilite.core.utils.LoadingScreen
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import timber.log.Timber
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proxiserve.proximobilite.R
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import com.google.gson.Gson
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.core.utils.HorizontalSpace
import com.proxiserve.proximobilite.core.utils.VerticalSpace
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.components.AppRadioButtons
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.components.AppSpinner
import com.proxiserve.proximobilite.modules.accueil.presentation.components.CalendarApp
import com.proxiserve.proximobilite.modules.accueil.presentation.components.HourTimeDisplay
import com.proxiserve.proximobilite.modules.accueil.presentation.components.MinuteTimeDisplay
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.ui.theme.Gris_100
import kotlinx.coroutines.launch

/**
 * Created by dloriot on 03/07/2024.
 */
private val TAG = "[AccueilScreen]"

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AccueilLoader(
    navController: NavController,
    viewModel: AccueilViewModel = hiltViewModel()
) {
    Timber.i("Entering [AccueilLoader]")
    val state by rememberUpdatedState(newValue = viewModel.state.collectAsState())
    val user by rememberUpdatedState(newValue = viewModel.user.collectAsState())

    Timber.w("$TAG state => %s", Gson().toJson(state))
    if (state.value.isLoading)
        LoadingScreen()
    else
        if (state.value.isAdmin)
            AccueilModeAdminScreen(navController = navController, viewModel = viewModel, user = user.value)
        else
            AccueilScreen(navController = navController, viewModel = viewModel, user = user.value, userAlias = state.value.userAlias)



}

@Composable
fun AccueilScreen(
    navController: NavController,
    viewModel: AccueilViewModel,
    user: User?,
    userAlias: UserAlias?
) {
    Timber.i("Entering $TAG ")
    val context = LocalContext.current

    user?.let {
//        Text(text = "USER ${it.prenom} !")
        val scope = rememberCoroutineScope()

        if (userAlias != null) {
            it.id = userAlias.id
        }

        val nextIntervention by produceState<Intervention?>(initialValue = null) {
            value = viewModel.getNextIntervention(it.id)
        }
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
                            text = "Bonjour " + it.prenom,
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
                        // Accès Mon Compte
                        Card(
                            colors = CardColors(
                                containerColor = Transparent,
                                contentColor = MaterialTheme.colorScheme.onSecondary,
                                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                disabledContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
//                        elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.fillMaxSize(),
                                onClick = { navController.navigate(Routes.Compte.route + it.id) }
                            ) {
                                Icon(
                                    modifier = Modifier.size(60.dp),
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Next",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }

                // Surface

                Surface(
                    modifier = Modifier
                        .weight(5f),
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
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {

                        // Prochaine intervention
                        Row(Modifier.weight(1f)) {

                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "Prochaine intervention",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    },
                                colors = CardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.80f
                                    ),
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                                ),
                                onClick = {
                                    nextIntervention?.let {
                                        navController.navigate("detail_intervention/${it.interventionCode}" )
                                    }?: run {
                                        Toast.makeText(context, "Intervention invalide", Toast.LENGTH_LONG).show()
                                    }

                                }
                            ) {
                                Row(
                                    Modifier
                                        .weight(1f)
                                        .padding(10.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(3f)
                                            .padding(10.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "Prochaine Intervention", color = White)
                                    }
                                    VerticalDivider(color = White, thickness = 2.dp)

                                    Column(
                                        modifier = Modifier
                                            .weight(5f)
                                            .padding(10.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = nextIntervention?.let { "${it.contactPrenom} ${it.contactNom}"}?: "",
                                            fontSize = 15.sp,
                                            color = White
                                        )
                                        Text(
                                            text = nextIntervention?.adresse ?: "",
                                            fontSize = 15.sp,
                                            color = White
                                        )
                                        Text(
                                            text = nextIntervention?.ville ?: "",
                                            fontSize = 15.sp,
                                            color = White
                                        )
                                    }
                                }

                            }

                        }

                        VerticalSpace(size = 10.dp)

                        // Accès fdr
                        Row(Modifier.weight(1f)) {
                            Column(Modifier.weight(6f)) {

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize(),
//                                    .clickable {
//                                        Toast
//                                            .makeText(
//                                                context,
//                                                "Accès à la Feuille de Route",
//                                                Toast.LENGTH_SHORT
//                                            )
//                                            .show()
//                                    },
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.90f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),
                                    onClick = {
                                        Timber.i("$TAG FDR click ")

                                        scope.launch {
                                            viewModel.onEvent(
                                                event = AccueilEvent.GoToFeuilleDeRouteEvent(
                                                    navController = navController,
                                                    Routes.FeuilleDeRoute.route + it.id
                                                )
                                            )
                                        }

                                    }

                                ) {
                                    Row(
                                        Modifier
                                            .weight(1f)
                                            .padding(10.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(3f)
                                                .padding(10.dp)
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = Routes.FeuilleDeRoute.label,
                                                color = White
                                            )
                                        }
                                        VerticalDivider(color = White, thickness = 2.dp)

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(10.dp)
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(
                                                    id = Routes.FeuilleDeRoute.logo
                                                ),
                                                contentDescription = Routes.FeuilleDeRoute.label,
                                                tint = White
                                            )
                                        }
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Card(
                                    colors = CardColors(
                                        containerColor = Transparent,
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),
                                    shape = RectangleShape
                                ) {
                                    HourTimeDisplay()
                                    MinuteTimeDisplay()

                                }
                            }
                        }

                        VerticalSpace(size = 10.dp)

                        // Calendrier
                        Row(Modifier.weight(1f)) {

                            Column(Modifier.weight(1f)) {
                                Card(
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.30f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
//
                                    Row {
                                        CalendarApp()
                                    }

                                }
                            }

                        }

                        VerticalSpace(size = 10.dp)

                        // SMS / Autres fonctionnalités / Flash-infos
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(Modifier.weight(1f)) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Prévenir le prochain client par SMS",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        },
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.80f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),

                                    ) {
                                    Row(
                                        Modifier
                                            .weight(1f)
                                            .padding(5.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(2f)
                                                .padding(10.dp)
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(text = "SMS", color = White)
                                        }
                                        VerticalDivider(color = White, thickness = 2.dp)

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(3.dp)
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Textsms,
                                                contentDescription = "Next"
                                            )
                                        }
                                    }
                                }
                            }

                            HorizontalSpace(size = 5.dp)

                            Column(Modifier.weight(1f)) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Accès aux autres fonctionnalités",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        },
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.90f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),

                                    ) {
                                    Column(
                                        Modifier
                                            .weight(1f)
                                            .padding(5.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .weight(0.8f)
                                                .padding(3.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Routes.AutresFonctions.icon,
                                                contentDescription = "Next"
                                            )
                                        }
                                        HorizontalDivider(color = White, thickness = 2.dp)

                                        Row(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(3.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(text = "Autres fonctions", color = White)
                                        }
                                    }
                                }
                            }

                            HorizontalSpace(size = 10.dp)

                            Column(Modifier.weight(1f)) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Accès au Flash-Infos",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        },
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.80f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    ),

                                    ) {
                                    Column(
                                        Modifier
                                            .weight(1f)
                                            .padding(5.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .weight(0.8f)
                                                .padding(3.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {

                                            Text(text = "Flash-Infos", color = White)
                                        }
                                        HorizontalDivider(color = White, thickness = 2.dp)

                                        Row(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(top = 5.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {

                                            OutlinedButton(
                                                onClick = {
//                                                Handler(Looper.getMainLooper()).postDelayed({
//                                                    Toast.makeText(context, "Flash-Infos", Toast.LENGTH_SHORT).show()
//                                                }, 3000)

                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.onPrimary
                                                ),
                                                border = BorderStroke(
                                                    3.dp,
                                                    MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                Text(
                                                    "Lire",
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        VerticalSpace(size = 10.dp)

                        Row(Modifier.weight(1f)) {

                            Column(Modifier.weight(1f)) {
                                Card(
                                    Modifier.fillMaxSize(),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.30f
                                        ),
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                                    )
                                ) {
                                    Row(
                                        Modifier
                                            .weight(1f)
                                            .padding(10.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(3f)
                                                .padding(10.dp),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Proxinews",
                                                color = MaterialTheme.colorScheme.primary,
                                                fontSize = 20.sp
                                            )
                                            OutlinedButton(
                                                onClick = {
                                                    Toast.makeText(
                                                        context,
                                                        "Accès à Proxinews",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.onPrimary
                                                ),
                                                border = BorderStroke(
                                                    3.dp,
                                                    MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                Text(
                                                    "Lire",
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }

                                        VerticalDivider(color = White, thickness = 2.dp)

                                        Row(
                                            modifier = Modifier
                                                .weight(5f)
                                                .padding(10.dp),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Row {

                                                Image(
                                                    modifier = Modifier.fillMaxHeight(),
                                                    painter = painterResource(id = R.drawable.chaudiere),
                                                    contentDescription = "Chaudière"
                                                )

                                                HorizontalSpace(size = 5.dp)
                                                Text(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    text = "Le 22 mai dernier, malgré des démentis récents, l'installat...",
                                                    fontSize = 13.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
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
    }

}

@Composable
fun AccueilModeAdminScreen(
    navController: NavController,
    viewModel: AccueilViewModel,
    user: User?
) {
    Timber.i("Entering [AccueilModeAdmin] ")

    val userAlias by rememberUpdatedState(newValue = viewModel.user.collectAsState())

    user?.let {
//        Text(text = "USER ${it.prenom} !")
        var selectedIndex by remember { mutableStateOf(0) }
        val list = listOf("Technicien", "Paramètres")


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
                            text = "Bonjour " + it.prenom,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.comfortaa_bold))
                        )
                    }

//                Column(
//                    modifier = Modifier
//                        .weight(1.5f)
//                        .fillMaxHeight()
//                        .padding(10.dp),
//                    horizontalAlignment = Alignment.End
//                ) { }
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
                    color = Gris_100,
                    border = null
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
                                text = "MODE ADMIN", color = MaterialTheme.colorScheme.primary,
                                fontSize = 25.sp
                            )
                        }

                        Column {
                            TabRow(
                                selectedTabIndex = selectedIndex,
                                containerColor = MaterialTheme.colorScheme.surface.compositeOver(
                                    White
                                ),
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 1.dp)
                                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                                indicator = { tabPositions: List<TabPosition> ->
                                    Box {}
                                }
                            ) {
                                list.forEachIndexed { index, text ->
                                    val selected = selectedIndex == index
                                    Tab(
                                        modifier = if (selected) Modifier
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 10.dp,
                                                    topEnd = 10.dp
                                                )
                                            )
                                            .background(MaterialTheme.colorScheme.secondary)
                                        else Modifier
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 10.dp,
                                                    topEnd = 10.dp
                                                )
                                            )
                                            .background(Transparent),
                                        selected = selected,
                                        onClick = { selectedIndex = index },
                                        text = {
                                            Text(
                                                text = text,
                                                color = if (selected) MaterialTheme.colorScheme.onSecondary
                                                else MaterialTheme.colorScheme.onSurface,
                                                fontSize = 20.sp
                                            )
                                        }
                                    )
                                }
                            }

                            when (selectedIndex) {
                                0 -> SelectTechnicienScreen(navController)
                                1 -> SetParamScreen(it.groupe)
                            }
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
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTechnicienScreen(
    navController: NavController,
    viewModel: AccueilViewModel = hiltViewModel()
) {
    Timber.i("$TAG SelectTechnicienScreen")

    val options = remember {listOf("", "B2B", "B2C", "Aléatoire")}
    var login by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var mode by remember { mutableStateOf(options[0]) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = login,
            onValueChange = { login = it.trim() },
            label = {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            textStyle = TextStyle(fontSize = 23.sp, color = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(10.dp),
        )

        VerticalSpace(size = 20.dp)



        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {

            OutlinedTextField(
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                value = mode,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                label = {
                    Text(
                        "Mode",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
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
                options.forEach { option ->
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
                            mode = option
                            expanded = false
                        },
                        contentPadding = PaddingValues(vertical = 0.dp),
                    )

                }
            }


        }
//        AppSpinner(options = listOf("", "B2B", "B2C", "Aléatoire"), "Mode")

        VerticalSpace(size = 20.dp)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            OutlinedButton(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                    contentColor = White,
                    disabledContentColor = White
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
//                    navController.navigate(Routes.Accueil.route + id)
//                    viewModel.onEvent(ModeAdminEvent.EnteredLoginSuccess)
                    scope.launch {
                        viewModel.onEvent(AccueilEvent.EnteredLogin(login, mode))
                    }

                }
            ) {
                Text(text = "VALIDER", fontSize = 20.sp)
            }
        }

        VerticalSpace(size = 20.dp)

    }

}

@Composable
fun SetParamScreen(
    group: String,
    viewModel: AccueilViewModel = hiltViewModel()
) {
    Timber.i("$TAG SetParamScreen")
    var env by remember { mutableStateOf("") }
    var syncFrequence by remember { mutableStateOf(0) }
    var groupe by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Choix environnement",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
        }

        AppRadioButtons(radioOptions = listOf("Prod", "Preprod"))

        VerticalSpace(size = 20.dp)

        AppSpinner(options = listOf("", "1", "2", "5", "10", "20", "30"), "Fréquence de synchro / min")

        VerticalSpace(size = 20.dp)

        /**
         * L'utilisateur ne peut pas voir des groupes de niveau supérieur au sien
         * */

        when (group) {

            UserGroup.GROUP_ADMIN.type -> {
                AppSpinner(
                    options = listOf(
                        "ADMIN",
                        "CI",
                        "TECHNICIEN"
                    ),
                    "Groupe"
                )
            }

            UserGroup.GROUP_CI.type -> {
                AppSpinner(
                    options = listOf(
                        "CI",
                        "TECHNICIEN"
                    ),
                    "Groupe"
                )
            }
        }
    }

}