package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases

import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.repository.AppareilRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class InsertAppareil(
    private val repository: AppareilRepository
) {
    suspend operator fun invoke(appareil: Appareil) {
        return repository.insertAppareil(appareil = appareil)
    }
}
