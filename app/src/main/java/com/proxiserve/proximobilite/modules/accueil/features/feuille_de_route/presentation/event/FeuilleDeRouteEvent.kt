package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.event

import androidx.navigation.NavController
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention

/**
 * Created by dloriot on 26/08/2024.
 */
sealed class FeuilleDeRouteEvent {
    data object GetFeuilleDeRoute: FeuilleDeRouteEvent()
    class GoToDetail(val navController: NavController, val id: String) : FeuilleDeRouteEvent()
    class SelectIntervention(val intervention: Intervention) : FeuilleDeRouteEvent()
}