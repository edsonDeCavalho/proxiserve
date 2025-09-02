package com.proxiserve.proximobilite.modules.connexion.presentation.event

import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 11/07/2024.
 */
sealed class UserEvent {

//    object Login: UserEvent()
//    object Logout: UserEvent()
    data object GetUser: UserEvent()
    data class SaveUser(val user: User) : UserEvent()

}