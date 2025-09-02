package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases

import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository.HistoriqueRepository

/**
 * Created by dloriot on 07/09/2024.
 */
data class InsertAllHistoriques(
    private val repository: HistoriqueRepository
) {
    suspend operator fun invoke(list: List<Historique>) {
        return repository.insertAllHistoriques(list = list)
    }
}