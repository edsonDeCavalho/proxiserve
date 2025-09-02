package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by dloriot on 20/09/2024.
 */
interface FeuilleDeRouteApiService {

//    @GET("fdr/{id}")
    @GET("getFeuilleRoute/{id}")
    suspend fun getFeuilleDeRoute(
        @Path("id") userId: String,
        @Header("Authorization") token: String?
    ): Response<FeuilleDeRoute>

    @GET("testToken")
    suspend fun testToken(
        @Header("Authorization") token: String?
    ): Response<FeuilleDeRoute>
}