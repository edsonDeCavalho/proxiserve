package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository

/**
 * Created by dloriot on 13/08/2024.
 */
data class GetUserAlias(
    private val repository: UserAliasRepository
) {

    suspend operator fun invoke(login: String): UserAlias? {
        return repository.getUserAliasByLogin(login)
    }
}
