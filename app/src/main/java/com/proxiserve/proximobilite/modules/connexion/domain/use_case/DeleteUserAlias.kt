package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository

/**
 * Created by dloriot on 13/08/2024.
 */
data class DeleteUserAlias(
    private val repository: UserAliasRepository
) {
    suspend operator fun invoke() {
        return repository.deleteUserAlias()
    }
}
