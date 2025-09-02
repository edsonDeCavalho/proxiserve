package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class GetUserById(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: String): User? {
        return repository.getUserById(id = id)
    }
}
