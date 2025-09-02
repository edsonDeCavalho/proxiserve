package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state.GetFakeFeuilleDeRoute

/**
 * Created by dloriot on 26/08/2024.
 */
data class FeuilleDeRouteUseCases(
    val getFakeFeuilleDeRoute: GetFakeFeuilleDeRoute,
    val getFeuilleDeRoute: GetFeuilleDeRoute,
    val getRemoteFeuilleDeRoute: GetRemoteFeuilleDeRoute,
    val deleteFeuilleDeRoute: DeleteFeuilleDeRoute,
)
