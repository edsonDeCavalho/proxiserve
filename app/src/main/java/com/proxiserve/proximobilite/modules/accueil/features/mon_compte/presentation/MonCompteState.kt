package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation

import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 06/08/2024.
 * Ce state ne contiendra que les éléments modifiable si le mode admin est actif (true)
 * Dans ce cas on pourra modifier la société et l'agence pour éventuellement tester les
 * paiements
 */

data class MonCompteState(
    val modeAdmin: Boolean = false,
    val socciete: String = "",
    val agence: String = "",
    val user: User? = null
)