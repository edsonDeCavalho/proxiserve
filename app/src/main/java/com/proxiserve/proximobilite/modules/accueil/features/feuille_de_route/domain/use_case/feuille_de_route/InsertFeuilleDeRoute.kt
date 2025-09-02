package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class InsertFeuilleDeRoute(
    private val repository: FeuilleDeRouteRepository
) {
    suspend operator fun invoke(feuilleDeRoute: FeuilleDeRoute) {
        return repository.insertFeuilleDeRoute(feuilleDeRoute = feuilleDeRoute)
    }
}
