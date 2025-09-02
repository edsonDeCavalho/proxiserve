package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserAliasState

/**
 * Created by dloriot on 13/08/2024.
 */
data class GetUserAliasState(
    private val repository: UserAliasRepository
) {
    suspend operator fun invoke(): UserAliasState {
        return repository.getUserAliasState()
    }
}