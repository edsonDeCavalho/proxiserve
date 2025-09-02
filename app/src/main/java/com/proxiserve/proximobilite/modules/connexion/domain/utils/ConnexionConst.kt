package com.proxiserve.proximobilite.modules.connexion.domain.utils

/**
 * Created by dloriot on 14/07/2024.
 */
object ConnexionConst {
    const val CONNECT_FAIL = "Echec de connexion"
    const val DISCONNECTED = "Vous êtes déconnecté"
    const val GROUP_FAIL = "Echec groupe inconnu"
    const val USER_ID_EMPTY = "L'id ne peut pas être vide."
    const val USER_NAME_EMPTY = "Le nom ne peut pas être vide."
    const val USER_FIRSTNAME_EMPTY = "Le prenom ne peut pas être vide."
    const val USER_GROUP_EMPTY = "Le groupe ne peut pas être vide."
    const val USER_ALIAS_ID_EMPTY = "L'id alias ne peut pas être vide."
    const val USER_ALIAS_LOGIN_EMPTY = "Le login alias ne peut pas être vide."
    const val USER_ALIAS_NAME_EMPTY = "Le nom alias ne peut pas être vide."
    const val USER_ALIAS_FIRSTNAME_EMPTY = "Le prenom alias ne peut pas être vide."
    const val USER_ALIAS_GROUP_EMPTY = "Le groupe alias ne peut pas être vide."
}

/**
 * Groupe d'utilisateur selon lesquels le user sera rediriger à la connexion.
 * */
enum class UserGroup(var type: String) {
    GROUP_TECHNICIEN("TEST_PROXIMOB_Technicien"),
    GROUP_ADMIN("TEST_PROXIMOB_Admin"),
    GROUP_CI("TEST_PROXIMOB_Ci")
}