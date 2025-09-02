package com.proxiserve.proximobilite.modules.connexion.presentation.state

import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 10/07/2024.
 *
 */
data class UserState(
    val username: String = "",
    val userId: String = "",
    val userGroup: String = "",
    var isLoggedIn: Boolean = false,
    var user: User? = null,
    val errorMessage: String? = null
)