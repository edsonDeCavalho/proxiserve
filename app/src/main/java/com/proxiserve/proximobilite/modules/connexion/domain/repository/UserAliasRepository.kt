package com.proxiserve.proximobilite.modules.connexion.domain.repository

import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserAliasState

/**
 * Created by dloriot on 13/08/2024.
 */
interface UserAliasRepository {

    suspend fun getUserAliasState(): UserAliasState

    suspend fun getUserAliasByLogin(login: String): UserAlias?

    suspend fun getUserAliasById(id: String): UserAlias?

    suspend fun getRemoteUserAlias(login: String, token: String): GetUserAliasResponse

    suspend fun insertUserAlias(userAlias: UserAlias)

    suspend fun deleteUserAlias()

}