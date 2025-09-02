package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository
import kotlinx.coroutines.delay
import timber.log.Timber


/**
 * Created by dloriot on 26/08/2024.
 */
data class GetFeuilleDeRoute(
    private val repository: FeuilleDeRouteRepository
) {
    val TAG = "[GetFeuilleDeRoute]"
    suspend operator fun invoke(): FeuilleDeRoute {
        Timber.d("$TAG invoke")
        delay(2000)
        return repository.getFeuilleDeRoute()
    }
}