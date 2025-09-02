package com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository

/**
 * Created by dloriot on 05/09/2024.
 */
data class SendCiAbsent(
    private val repository: InterventionRepository
) {
    suspend operator fun invoke(intervention: Intervention) {
        return repository.sendCiAbsent(intervention = intervention)
    }
}