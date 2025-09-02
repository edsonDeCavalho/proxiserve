package com.proxiserve.proximobilite.modules.connexion.presentation.event

import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 08/07/2024.
 */
sealed class ConnexionEvent {
    object Login: ConnexionEvent()
    object Logout: ConnexionEvent()
    data class SaveUser(val user: User) : ConnexionEvent()
}