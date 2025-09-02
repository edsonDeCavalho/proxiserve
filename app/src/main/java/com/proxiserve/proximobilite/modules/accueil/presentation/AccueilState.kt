package com.proxiserve.proximobilite.modules.accueil.presentation

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias

/**
 * Created by dloriot on 15/10/2024.
 */
data class AccueilState(
    val userId: String = "",
    val userAlias: UserAlias? = null,
    val nextIntervention: Intervention? = null,
    val isLoading: Boolean = true,
    val isAdmin: Boolean = false,
)