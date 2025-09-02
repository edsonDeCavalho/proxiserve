package com.proxiserve.proximobilite.modules.connexion.domain.utils

/**
 * Created by dloriot on 14/07/2024.
 */
object UserUtils {
    const val CONNECT_FAIL = "Echec de connexion"
    const val DISCONNECTED = "Vous êtes déconnecté"
    const val GROUP_FAIL = "Echec groupe inconnu"

    fun isAdmin(group: String): Boolean {
        return group == UserGroup.GROUP_ADMIN.type
    }

    fun isCi(group: String): Boolean {
        return group == UserGroup.GROUP_CI.type
    }

    fun isTechnicien(group: String): Boolean {
        return group == UserGroup.GROUP_TECHNICIEN.type
    }

}
