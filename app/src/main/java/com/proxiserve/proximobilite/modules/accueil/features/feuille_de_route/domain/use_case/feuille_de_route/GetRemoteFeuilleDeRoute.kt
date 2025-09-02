package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import kotlinx.coroutines.delay
import timber.log.Timber


/**
 * Created by dloriot on 26/08/2024.
 */
data class GetRemoteFeuilleDeRoute(
    private val repository: FeuilleDeRouteRepository
) {
    val TAG = "[GetRemoteFeuilleDeRoute]"
    suspend operator fun invoke(id: String, token: String): GetFeuilleDeRouteResponse {
        return repository.getRemoteFeuilleDeRoute(id = id, token = token)
    }
}