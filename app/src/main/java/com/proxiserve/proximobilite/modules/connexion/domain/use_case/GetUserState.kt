package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class GetUserState(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): UserState {
        return repository.getUserState()
    }
}