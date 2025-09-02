package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases

import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository.HistoriqueRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class InsertHistorique(
    private val repository: HistoriqueRepository
) {
    suspend operator fun invoke(historique: Historique) {
        return repository.insertHistorique(historique = historique)
    }
}
