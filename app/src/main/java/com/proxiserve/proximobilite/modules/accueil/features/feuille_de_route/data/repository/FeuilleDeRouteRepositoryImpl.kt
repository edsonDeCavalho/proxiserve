package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository

import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.FeuilleDeRouteDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.FeuilleDeRouteRemoteDataSource
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository
import timber.log.Timber


/**
 * Created by dloriot on 26/08/2024.
 */

class FeuilleDeRouteRepositoryImpl(
    private val dao: FeuilleDeRouteDao,
    private val remote: FeuilleDeRouteRemoteDataSource
): FeuilleDeRouteRepository {

    val TAG = "[FeuilleDeRouteRepositoryImpl]"
    override suspend fun getFeuilleDeRoute(): FeuilleDeRoute {
        return dao.getFeuilleDeRoute()
    }

    override suspend fun getRemoteFeuilleDeRoute(id: String, token: String): GetFeuilleDeRouteResponse {
        Timber.i("$TAG getRemoteFeuilleDeRoute | id => $id | token => $token")
        return remote.getFeuilleDeRoute(id = id, token = "Bearer $token")
    }

    override suspend fun insertFeuilleDeRoute(feuilleDeRoute: FeuilleDeRoute) {
        return dao.insertFeuilleDeRoute(feuilleDeRoute = feuilleDeRoute)
    }

    override suspend fun deleteFeuilleDeRoute() {
        return dao.deleteFeuilleDeRoute()
    }

}