package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases

import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository.HistoriqueRepository
import timber.log.Timber

/**
 * Created by dloriot on 26/08/2024.
 */
data class GetHistoriqueByLieuCode(
    private val repository: HistoriqueRepository
) {
    suspend operator fun invoke(lieuCode: String): List<Historique>? {
        Timber.w("[GetHistoriqueByLieuCode] invoke $lieuCode")
        return repository.getHistoriqueByLieuCode(lieuCode = lieuCode)
    }
}
