package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.InsertFeuilleDeRoute

/**
 * Created by dloriot on 26/08/2024.
 */

interface FeuilleDeRouteRepository {

    suspend fun getFeuilleDeRoute(): FeuilleDeRoute
    suspend fun getRemoteFeuilleDeRoute(id: String, token: String): GetFeuilleDeRouteResponse
    suspend fun insertFeuilleDeRoute(feuilleDeRoute: FeuilleDeRoute)
    suspend fun deleteFeuilleDeRoute()

}