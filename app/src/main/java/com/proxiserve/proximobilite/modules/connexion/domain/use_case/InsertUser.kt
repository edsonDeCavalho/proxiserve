package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.google.gson.Gson
import com.proxiserve.proximobilite.modules.connexion.domain.model.InvalidUserException
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import timber.log.Timber
import java.util.Locale

/**
 * Created by dloriot on 11/07/2024.
 *
 */
class InsertUser(
    private val repository: UserRepository
) {

    private val TAG: String = "[InsertUser]"

    suspend operator fun invoke(user: User) {
        Timber.i("$TAG invoke for user %s", Gson().toJson(user))

        if (user.id.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ID_EMPTY)
        }
        if (user.nom.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_NAME_EMPTY)
        }
        if (user.prenom.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_FIRSTNAME_EMPTY)
        }
        if (user.login.isBlank()) {
            user.login = (user.prenom.first().toString() + user.nom).lowercase()
//            throw InvalidUserException("Le login ne peut pas être vide.")
        }
//        if (user.idAdonix.isBlank()) {
//            throw InvalidUserException("Le idAdonix ne peut pas être vide.")
//        }
//        if (user.mailAgence.isBlank()) {
//            throw InvalidUserException("Le mailAgence ne peut pas être vide.")
//        }
//        if (user.mailDGI.isBlank()) {
//            throw InvalidUserException("Le mailDGI ne peut pas être vide.")
//        }
//        if (user.mailMagasinier.isBlank()) {
//            throw InvalidUserException("Le mailMagasinier ne peut pas être vide.")
//        }
        if (user.groupe.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_GROUP_EMPTY)
        }


        repository.insertUser(user)

    }

}