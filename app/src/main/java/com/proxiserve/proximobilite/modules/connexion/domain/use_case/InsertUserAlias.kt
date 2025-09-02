package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.InvalidUserException
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import timber.log.Timber

/**
 * Created by dloriot on 11/07/2024.
 */
class InsertUserAlias(
    private val repository: UserAliasRepository
) {

    private val TAG: String = "[InsertUserAlias]"

    suspend operator fun invoke(userAlias: UserAlias) {
        Timber.i("$TAG invoke for user alias %s", userAlias.id)

        if (userAlias.id.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ALIAS_ID_EMPTY)
        }
        if (userAlias.login.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ALIAS_LOGIN_EMPTY)
        }
        if (userAlias.nom.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ALIAS_NAME_EMPTY)
        }
        if (userAlias.prenom.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ALIAS_FIRSTNAME_EMPTY)
        }
//        if (userAlias.idAdonix.isBlank()) {
//            throw InvalidUserException("L'idAdonix alias ne peut pas être vide.")
//        }
//        if (userAlias.mailAgence.isBlank()) {
//            throw InvalidUserException("Le mailAgence alias ne peut pas être vide.")
//        }
//        if (userAlias.mailDGI.isBlank()) {
//            throw InvalidUserException("Le mailDGI alias ne peut pas être vide.")
//        }
//        if (userAlias.mailMagasinier.isBlank()) {
//            throw InvalidUserException("Le mailMagasinier alias ne peut pas être vide.")
//        }
        if (userAlias.groupe.isBlank()) {
            throw InvalidUserException(ConnexionConst.USER_ALIAS_GROUP_EMPTY)
        }


        repository.insertUserAlias(userAlias)

    }

}