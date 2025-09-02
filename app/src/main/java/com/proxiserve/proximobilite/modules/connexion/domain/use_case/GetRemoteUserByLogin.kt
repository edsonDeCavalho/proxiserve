package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import retrofit2.Response
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class GetRemoteUserByLogin(
    private val repository: UserRepository
) {

    suspend operator fun invoke(login: String, token: String): GetUserResponse {
        return repository.getRemoteUserByLogin(login = login, token = token)
    }
}
