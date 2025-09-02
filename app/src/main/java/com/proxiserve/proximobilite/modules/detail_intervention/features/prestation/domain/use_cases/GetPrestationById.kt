package com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PrestationRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class GetPrestationById(
    private val repository: PrestationRepository
) {
    suspend operator fun invoke(id: String): Prestation? {
        return repository.getPrestationById(id = id)
    }
}
