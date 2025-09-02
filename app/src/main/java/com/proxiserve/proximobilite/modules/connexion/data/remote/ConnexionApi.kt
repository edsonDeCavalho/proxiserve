package com.proxiserve.proximobilite.feature_modules.connexion.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by dloriot on 28/06/2024.
 */
interface ConnexionApi {

    @POST("logIn")
    suspend fun logIn(
        @Body request: ConnexionRequest
    ): TokenResponse

    @POST("logOut")
    suspend fun logOut(
        @Body request: ConnexionRequest
    )

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )

}