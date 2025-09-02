package com.proxiserve.proximobilite.modules.connexion.data.data_source.remote

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import javax.inject.Inject

/**
 * Created by dloriot on 20/09/2024.
 */

class UserAliasProfileRemoteDataSource @Inject constructor(
    private val apiService: UserApiService
) {
    suspend fun getUserProfile(login: String, token: String): UserAlias? {
        val response = apiService.getUserAliasProfile(userLogin = login, token = token)
        return if (response.isSuccessful) response.body() else null
    }
}