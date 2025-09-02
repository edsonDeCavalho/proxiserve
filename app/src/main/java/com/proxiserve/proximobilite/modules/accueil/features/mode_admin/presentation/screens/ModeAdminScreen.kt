package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.screens//package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.screens
//
//import android.annotation.SuppressLint
//import android.content.Context
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ButtonColors
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.MenuAnchorType
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Tab
//import androidx.compose.material3.TabPosition
//import androidx.compose.material3.TabRow
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldColors
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.produceState
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.rememberUpdatedState
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.compositeOver
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.proxiserve.proximobilite.BuildConfig
//import com.proxiserve.proximobilite.R
//import com.proxiserve.proximobilite.core.components.CircularProgressBar
//import com.proxiserve.proximobilite.core.utils.AppConstant
//import com.proxiserve.proximobilite.core.utils.LoadingScreen
//import com.proxiserve.proximobilite.core.utils.VerticalSpace
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.ModeAdminEvent
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.ModeAdminState
//import com.proxiserve.proximobilite.modules.connexion.presentation.components.BackgroundImage
//import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.ModeAdminViewModel
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.components.AppRadioButtons
//import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.components.AppSpinner
//import com.proxiserve.proximobilite.modules.connexion.domain.model.User
//import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
//import com.proxiserve.proximobilite.ui.theme.Gris_100
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//import timber.log.Timber
//
///**
// * Created by dloriot on 14/07/2024.
// */
//
//private val TAG = "[ModeAdminScreen]"
//
//@Composable
//fun ModeAdminScreen(
//    navController: NavController,
//    viewModel: ModeAdminViewModel = hiltViewModel(),
//    id: String
//) {
//    Timber.i("Entering $TAG with id $id")
//
//    val context = LocalContext.current
//    val userState by rememberUpdatedState(newValue = viewModel.userState.collectAsState())
//    var selectedIndex by remember { mutableStateOf(0) }
//    val list = listOf("Technicien", "Paramètres")
//    val user by produceState<User?>(initialValue = null) {
//        value = viewModel.getUser()
//    }
//    Box(Modifier.fillMaxSize()) {
//
//        BackgroundImage()
//
//        Image(
//            modifier = Modifier.width(500.dp),
//            painter = painterResource(id = R.drawable.symbole_pxs_white_trans_medium),
//            contentDescription = "Symblole PXS",
//            alpha = 0.5f
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Row:Message bienvenu Technicien / Icon Account
//
//            Row(Modifier.weight(0.8f)) {
//
//                Column(
//                    modifier = Modifier
//                        .weight(5f)
//                        .fillMaxSize()
//                        .padding(start = 10.dp),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Bonjour " + user?.prenom,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.secondary,
//                        fontSize = 26.sp,
//                        fontFamily = FontFamily(Font(R.font.comfortaa_bold))
//                    )
//                }
//
////                Column(
////                    modifier = Modifier
////                        .weight(1.5f)
////                        .fillMaxHeight()
////                        .padding(10.dp),
////                    horizontalAlignment = Alignment.End
////                ) { }
//            }
//
//            Surface(
//                modifier = Modifier
//                    .weight(5f),
//                shape = RoundedCornerShape(
//                    topStart = 28.dp,
//                    topEnd = 28.dp,
//                    bottomEnd = 28.dp,
//                    bottomStart = 28.dp
//                ),
//                color = Gris_100,
//                border = null
////                color = MaterialTheme.colorScheme.onBackground
//            ) {
//                Column(
//                    Modifier
//                        .fillMaxSize()
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "MODE ADMIN", color = MaterialTheme.colorScheme.primary,
//                            fontSize = 25.sp
//                        )
//                    }
//
//                    Column {
//                        TabRow(
//                            selectedTabIndex = selectedIndex,
//                            containerColor = MaterialTheme.colorScheme.surface.compositeOver(Color.White),
//                            modifier = Modifier
//                                .padding(vertical = 4.dp, horizontal = 1.dp)
//                                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
//                            indicator = { tabPositions: List<TabPosition> ->
//                                Box {}
//                            }
//                        ) {
//                            list.forEachIndexed { index, text ->
//                                val selected = selectedIndex == index
//                                Tab(
//                                    modifier = if (selected) Modifier
//                                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
//                                        .background(MaterialTheme.colorScheme.secondary)
//                                    else Modifier
//                                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
//                                        .background(Color.Transparent),
//                                    selected = selected,
//                                    onClick = { selectedIndex = index },
//                                    text = {
//                                        Text(
//                                            text = text,
//                                            color = if (selected) MaterialTheme.colorScheme.onSecondary
//                                            else MaterialTheme.colorScheme.onSurface,
//                                            fontSize = 20.sp
//                                        )
//                                    }
//                                )
//                            }
//                        }
//
//                        when (selectedIndex) {
//                            0 -> SelectTechnicienScreen(navController, viewModel)
//                            1 -> SetParamScreen(userState.value.userGroup)
//                        }
//                    }
//                }
//
//            }
//
//
//            Row(
//                modifier = Modifier.weight(0.5f),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(text = AppConstant.APP_NAME)
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SelectTechnicienScreen(
//    navController: NavController,
//    viewModel: ModeAdminViewModel = hiltViewModel()
//) {
//    Timber.i("$TAG SelectTechnicienScreen")
////    val loginResult by viewModel.maState.collectAsState()
//    val userAlias by viewModel.userAlias.collectAsState()
//
//    val options = remember {listOf("", "B2B", "B2C", "Aléatoire")}
//    var login by remember { mutableStateOf("") }
//    var expanded by remember { mutableStateOf(false) }
//    var mode by remember { mutableStateOf(options[0]) }
//    val scope = rememberCoroutineScope()
//    userAlias?.let {
//        Timber.i("$TAG SelectTechnicienScreen userAlias OK ${it.id}")
////        viewModel.onEvent(ModeAdminEvent.EnteredLoginSuccess(userAlias!!.id))
////        navController.navigate(Routes.Accueil.route + it.id)
//        scope.launch {
//            viewModel.onEvent(ModeAdminEvent.EnteredLoginSuccess(aliasId = it.id, navController = navController))
//        }
//
//    }?: run {
//        Timber.e("$TAG SelectTechnicienScreen userAlias NULL")
//
////        val counter = remember { mutableStateOf(0) }
////        SideEffect {
////            counter.value++
////            Timber.i("$TAG Recomposé ${counter.value} fois")
////        }
//        VerticalSpace(size = 50.dp)
//
//
//        Column(
//            modifier = Modifier.padding(30.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            OutlinedTextField(
//                value = login,
//                onValueChange = { login = it.trim() },
//                label = {
//                    Text(
//                        text = "Login",
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
//                    focusedLabelColor = MaterialTheme.colorScheme.secondary,
//                    focusedBorderColor = MaterialTheme.colorScheme.secondary
//                ),
//                textStyle = TextStyle(fontSize = 23.sp, color = MaterialTheme.colorScheme.primary),
//                shape = RoundedCornerShape(10.dp),
//            )
//
//            VerticalSpace(size = 20.dp)
//
//
//
//            ExposedDropdownMenuBox(
//                expanded = expanded,
//                onExpandedChange = { expanded = it },
//            ) {
//
//                OutlinedTextField(
//                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
//                    value = mode,
//                    onValueChange = {},
//                    readOnly = true,
//                    singleLine = true,
//                    label = {
//                        Text(
//                            "Mode",
//                            fontSize = 20.sp,
//                            color = MaterialTheme.colorScheme.primary
//                        )
//                    },
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = MaterialTheme.colorScheme.primary,
//                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
//                        focusedLabelColor = MaterialTheme.colorScheme.primary,
//                        focusedBorderColor = MaterialTheme.colorScheme.secondary
//                    ),
//                    shape = RoundedCornerShape(10.dp)
//                )
//
//                ExposedDropdownMenu(
//                    modifier = Modifier
//                        .background(Gris_100)
//                        .padding(10.dp),
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false },
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    options.forEach { option ->
//                        DropdownMenuItem(
//                            modifier = Modifier.background(Gris_100),
//                            text = {
//                                Text(
//                                    text = option,
//                                    style = MaterialTheme.typography.bodyLarge,
//                                    color = MaterialTheme.colorScheme.primary,
//                                )
//                            },
//                            onClick = {
//                                mode = option
//                                expanded = false
//                            },
//                            contentPadding = PaddingValues(vertical = 0.dp),
//                        )
//
//                    }
//                }
//
//
//            }
////        AppSpinner(options = listOf("", "B2B", "B2C", "Aléatoire"), "Mode")
//
//            VerticalSpace(size = 20.dp)
//
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                OutlinedButton(
//                    colors = ButtonColors(
//                        containerColor = MaterialTheme.colorScheme.secondary,
//                        disabledContainerColor = MaterialTheme.colorScheme.primary,
//                        contentColor = Color.White,
//                        disabledContentColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    onClick = {
////                    navController.navigate(Routes.Accueil.route + id)
////                    viewModel.onEvent(ModeAdminEvent.EnteredLoginSuccess)
//                        scope.launch {
//                            viewModel.onEvent(ModeAdminEvent.EnteredLogin(login, mode))
//                        }
//
//                    }
//                ) {
//                    Text(text = "VALIDER", fontSize = 20.sp)
//                }
//            }
//
//            VerticalSpace(size = 20.dp)
//
//            // ZONE PROVOQUANT UNE BOUCLE DE RECOMPOSITION A REVOIR ////////////////////////////////////
//            /*
//    * Provoque une centaine de recomposition, lorsqu'on ne lance pas l'event
//    * ModeAdminEvent.EnteredLoginSuccess dans le onClick de validation le problème
//    * disparaît.
//    * UPDATE: pb disparaît en remplacant la route "accueil/$id" par
//    * Routes.Accueil.route + id dans le click de validation
//    * TODO() Vérifier pourquoi cette zone provoque autant de recompo avec l'adresse "accueil/$id"
//    *  Hint: le fait de corriger l'id avec correctId masque peut-être le problème
//    */
////            when (loginResult) {
////
////                is ModeAdminState.Loading -> {
////                    Timber.w("$TAG SelectTechnicienScreen Loading")
////                    CircularProgressBar(modifier = Modifier.size(50.dp))
////
////                }
////
////                is ModeAdminState.Success -> {
////                    Timber.i("$TAG SelectTechnicienScreen Success")
////                    ModeAdminEvent.EnteredLoginSuccess()
//////                    navController.navigate(Routes.Accueil.route + (userAlias?.id ?: ""))
//////                navController.navigate("accueil/$id")
////                }
////
////                is ModeAdminState.Error -> {
////                    Timber.e("$TAG SelectTechnicienScreen Error")
////                    TextField(
////                        value = "Une erreur est survenue lors de la récupération du profil !",
////                        onValueChange = {},
////                        colors = OutlinedTextFieldDefaults.colors(
////                            disabledBorderColor = MaterialTheme.colorScheme.secondary
////                        ),
////                    )
////                }
////
////                ModeAdminState.Initial -> {}
////            }
//            ////////////////////////////////////////////////////////////////////////////////////////////
//
//        }
//    }
//
//
//}
//
//
//@Composable
//fun SetParamScreen(group: String) {
//    Timber.w("$TAG SetParamScreen group %s", group)
//    var env by remember { mutableStateOf("") }
//    var syncFrequence by remember { mutableStateOf(0) }
//    var groupe by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.padding(30.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(Modifier.fillMaxWidth()) {
//            Text(
//                text = "Choix environnement",
//                color = MaterialTheme.colorScheme.primary,
//                fontSize = 20.sp
//            )
//        }
//
//
//        AppRadioButtons(radioOptions = listOf("Prod", "Preprod"))
//
//        VerticalSpace(size = 20.dp)
//
//        AppSpinner(options = listOf("", "1", "2", "5", "10", "20", "30"), "Fréquence de synchro / min")
//
//        VerticalSpace(size = 20.dp)
//
//        /**
//         * L'utilisateur ne peut pas voir des groupes de niveau supérieur au sien
//         * */
//
//        when (group) {
//
//            UserGroup.GROUP_ADMIN.type -> {
//                AppSpinner(
//                    options = listOf(
//                        "ADMIN",
//                        "CI",
//                        "TECHNICIEN"
//                    ),
//                    "Groupe"
//                )
//            }
//
//            UserGroup.GROUP_CI.type -> {
//                AppSpinner(
//                    options = listOf(
//                        "CI",
//                        "TECHNICIEN"
//                    ),
//                    "Groupe"
//                )
//            }
//        }
//    }
//}