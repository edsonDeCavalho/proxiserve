package com.proxiserve.proximobilite.modules.connexion.data.data_source.remote

import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by dloriot on 28/06/2024.
 */
interface UserApiService {


    @GET("getProfilById/{id}")
    suspend fun getUserProfile(
        @Path("id") userId: String,
        @Header("Authorization") token: String?
    ): Response<User>

    @GET("getProfilByLogin/{login}")
    suspend fun getUserProfileByLogin(
        @Path("login") userLogin: String,
        @Header("Authorization") token: String?
    ): Response<User>

    @GET("getProfilByLogin/{login}")
    suspend fun getUserAliasProfile(
        @Path("login") userLogin: String,
        @Header("Authorization") token: String?
    ): Response<UserAlias>

}