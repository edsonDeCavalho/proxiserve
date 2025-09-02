package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import retrofit2.Response
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository

/**
 * Created by dloriot on 13/10/2024.
 *
 */
data class GetRemoteUserAlias(
    private val repository: UserAliasRepository
) {

    suspend operator fun invoke(login: String, token: String): GetUserAliasResponse {
        return repository.getRemoteUserAlias(login = login, token = token)
    }
}
