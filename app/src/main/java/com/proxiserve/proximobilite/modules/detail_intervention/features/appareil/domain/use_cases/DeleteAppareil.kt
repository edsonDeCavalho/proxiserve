package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases

import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.repository.AppareilRepository


/**
 * Created by dloriot on 26/08/2024.
 */
data class DeleteAppareil(
    private val repository: AppareilRepository
) {
    suspend operator fun invoke() {
        return repository.deleteAppareil()
    }
}
