package com.proxiserve.proximobilite.modules.connexion.domain.repository

import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState

/**
 * Created by dloriot on 27/06/2024.
 * Définition des fonctions du repository. Ici on n'a pas besoin de savoir où sont récupérées les data.
 * Pour ça il y a UserRepositoryImpl.
 */
interface UserRepository {

    suspend fun getUserState(): UserState

    suspend fun getUser(): User?

    suspend fun getUserById(id: String): User?

    suspend fun getRemoteUser(id: String, token: String): GetUserResponse

    suspend fun getRemoteUserByLogin(login: String, token: String): GetUserResponse

    suspend fun insertUser(user: User)

    suspend fun deleteUser()

}