package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import timber.log.Timber

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class DeleteUser(
    private val repository: UserRepository
) {
    private val TAG: String = "[DeleteUser]"

    suspend operator fun invoke() {
        Timber.i("$TAG invoke")
        repository.deleteUser()
    }
}
