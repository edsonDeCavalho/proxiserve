package com.proxiserve.proximobilite.modules.connexion.presentation.state

/**
 * Created by dloriot on 13/08/2024.
 */
data class UserAliasState(
    val userAliasname: String = "",
    val userAliasId: String = "",
    val userAliasGroup: String = "",
    var aliasIsLoggedIn: Boolean = false,
    val aliasErrorMessage: String? = null
)