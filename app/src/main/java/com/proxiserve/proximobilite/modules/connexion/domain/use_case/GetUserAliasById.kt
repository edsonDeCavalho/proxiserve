package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class GetUserAliasById(
    private val repository: UserAliasRepository
) {

    suspend operator fun invoke(id: String): UserAlias? {
        return repository.getUserAliasById(id = id)
    }
}
