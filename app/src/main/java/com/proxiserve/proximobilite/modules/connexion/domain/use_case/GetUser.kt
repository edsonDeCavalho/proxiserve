package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import timber.log.Timber

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class GetUser(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): User? {
        Timber.w("[GetUser] invoke")
        return repository.getUser()
    }
}
