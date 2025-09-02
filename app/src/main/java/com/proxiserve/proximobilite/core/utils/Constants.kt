package com.proxiserve.proximobilite.core.utils

import com.proxiserve.proximobilite.BuildConfig

/**
 * Created by dloriot on 14/07/2024.
 */

object AppConstant {
    const val APP_NAME = "Proximobilite." + BuildConfig.VERSION_NAME + "." + BuildConfig.BUILD_TYPE
    const val TOKEN_INVALIDE = "L'authentification a echoué: token invalide"
    const val ERREUR_SERVER = "Erreur serveur durant l'authentification: veuillez réessayer."
    const val CONNECTION_TIMEOUT= "Le délai d'attente est dépassé. veuillez réessayer."
    const val CONNECTION_ERROR= "Problème de connexion. Veuillez réessayer."

}

object AuthenticatorConst {
    const val DEFAULT_ACCOUNT = "compte_proxiserve"
    const val TOKEN_TYPE_FULL = "full_access"
}

object TestTag {
    const val FDR_LAZY_COLUMN = "FDR_LAZY_COLUMN"
}
