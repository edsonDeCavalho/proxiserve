package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import timber.log.Timber

/**
 * Created by dloriot on 13/08/2024.
 */
class LoginAliasUseCase(
    private val repository: UserAliasRepository
) {
    private val TAG: String = "[LoginAliasUseCase]"

    suspend operator fun invoke(login: String): UserAlias? {
        Timber.w("$TAG invoke id %s", login)
        return repository.getUserAliasByLogin(login = login)
    }
}