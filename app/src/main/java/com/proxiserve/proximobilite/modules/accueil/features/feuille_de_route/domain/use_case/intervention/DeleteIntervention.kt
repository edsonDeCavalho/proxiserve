package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class DeleteIntervention(
    private val repository: InterventionRepository
) {
    suspend operator fun invoke(intervention: Intervention) {
        return repository.deleteIntervention(intervention = intervention)
    }
}
