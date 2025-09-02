package com.proxiserve.proximobilite.modules.connexion.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.HeatPump
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.ui.graphics.vector.ImageVector
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.navigation.RouteObject

/**
 * Created by dloriot on 02/07/2024.
 */

const val ROOT_GRAPH_ROUTE = "root_nav"
const val LOGIN_GRAPH_ROUTE = "login_nav"
const val ACCUEIL_GRAPH_ROUTE = "accueil_nav"
const val MODE_ADMIN_GRAPH_ROUTE = "mode_admin"

const val USER_ID_KEY = "id"
const val LIEU_CODE_KEY = "lieuCode"
const val INTERVENTION_CODE_KEY = "interventionCode"
const val APPAREIL_CODE_KEY = "appareilCode"
const val INTERVENTION_ID_KEY = "interventionId"

object Routes {

    object Login: RouteObject {
        override val route: String
            get() = "login"
        override val label: String
            get() = "Connexion"
        override val logo: Int
            get() = R.drawable.symbole_pxs_big
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.Login
    }

    object Logout: RouteObject {
        override val route: String
            get() = "logout"
        override val label: String
            get() = "Déconnexion"
        override val logo: Int
            get() = R.drawable.logout_96
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.Logout
    }

    object ModeAdmin: RouteObject {
        override val route: String
            get() = "mode_admin/{id}"
        override val label: String
            get() = "Mode Admin"
        override val logo: Int
            get() = R.drawable.mode_admin_96
        override val icon: ImageVector
            get() = Icons.Filled.AdminPanelSettings
    }

    object ModeCi: RouteObject {
        override val route: String
            get() = "mode_ci/{id}"
        override val label: String
            get() = "Mode CI"
        override val logo: Int
            get() = R.drawable.mode_admin_96
        override val icon: ImageVector
            get() = Icons.Filled.AdminPanelSettings
    }

    object Accueil: RouteObject {
        override val route: String
            get() = "accueil/{id}"
        override val label: String
            get() = "Accueil"
        override val logo: Int
            get() = R.drawable.home_96
        override val icon: ImageVector
            get() = Icons.Filled.Home
    }

    object Compte: RouteObject {
        override val route: String
            get() = "compte/{id}"
        override val label: String
            get() = "Mon Compte"
        override val logo: Int
            get() = R.drawable.account_96
        override val icon: ImageVector
            get() = Icons.Filled.AccountCircle
    }

    object FeuilleDeRoute: RouteObject {
        override val route: String
            get() = "fdr/{id}"
        override val label: String
            get() = "Feuille de Route"
        override val logo: Int
            get() = R.drawable.fdr_96
        override val icon: ImageVector
            get() = Icons.Filled.Route
    }

    object DetailIntervention: RouteObject {
        override val route: String
            get() = "detail_intervention/{id}"
        override val label: String
            get() = "Détail Intervention"
        override val logo: Int
            get() = R.drawable.cidetail_96
        override val icon: ImageVector
            get() = Icons.Filled.Details
    }

    object Historique: RouteObject {
        override val route: String
            get() = "historique/{$LIEU_CODE_KEY}"
        override val label: String
            get() = "Historique"
        override val logo: Int
            get() = R.drawable.symbole_pxs_big
        override val icon: ImageVector
            get() = Icons.Filled.WorkHistory
    }

    object HistoriqueDetail: RouteObject {
        override val route: String
            get() = "historique_detail/{$INTERVENTION_CODE_KEY}"
        override val label: String
            get() = "Historique Détail"
        override val logo: Int
            get() = R.drawable.symbole_pxs_big
        override val icon: ImageVector
            get() = Icons.Filled.WorkHistory
    }

    object Appareil: RouteObject {
        override val route: String
            get() = "appareil/{$INTERVENTION_CODE_KEY}"
        override val label: String
            get() = "Appareil"
        override val logo: Int
            get() = R.drawable.appareils_100
        override val icon: ImageVector
            get() = Icons.Filled.HeatPump
    }

    object AppareilDetail: RouteObject {
        override val route: String
            get() = "appareil_detail/{$APPAREIL_CODE_KEY}"
        override val label: String
            get() = "Appareil Détail"
        override val logo: Int
            get() = R.drawable.appareils_100
        override val icon: ImageVector
            get() = Icons.Filled.HeatPump
    }

    object AutresFonctions: RouteObject {
        override val route: String
            get() = "autres_fonctions/{id}"
        override val label: String
            get() = "Autres Fonctions"
        override val logo: Int
            get() = R.drawable.home_96
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.List
    }

    object CertificatIntervention: RouteObject {
        override val route: String
            get() = "certificat_intervention"
        override val label: String
            get() = "Certificat d'intervention"
        override val logo: Int
            get() = R.drawable.home_96
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.List
    }

    object Intervention: RouteObject {
        override val route: String
            get() = "intervention"
        override val label: String
            get() = "Intervention"
        override val logo: Int
            get() = R.drawable.home_96
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.List
    }
    object InterventionValidation: RouteObject {
        override val route: String
            get() = "intervention_validation"
        override val label: String
            get() = "Intervention validation"
        override val logo: Int
            get() = R.drawable.home_96
        override val icon: ImageVector
            get() = Icons.AutoMirrored.Filled.List
    }

}