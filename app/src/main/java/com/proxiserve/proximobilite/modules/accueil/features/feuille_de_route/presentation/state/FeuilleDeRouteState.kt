package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state

import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation

/**
 * Created by dloriot on 27/08/2024.
 */

data class FeuilleDeRouteState(
    var aliasId: String? = null,
    var currentFeuilleDeRoute: FeuilleDeRoute? = null,
    var currentAppareils: List<Appareil> = emptyList(),
    var currentHistoriques: List<Historique> = emptyList(),
    var currentInterventions: List<Intervention> = emptyList(),
    var currentOperations: List<Operation> = emptyList(),
    var currentPieces: List<Piece> = emptyList(),
    var currentPrestations: List<Prestation> = emptyList(),
    var errorMessage: String? = null
)