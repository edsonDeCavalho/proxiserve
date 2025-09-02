package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.data.repository

import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.data.data_source.HistoriqueDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository.HistoriqueRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

/**
 * Created by dloriot on 26/08/2024.
 */

class HistoriqueRepositoryImpl(
    private val dao: HistoriqueDao
): HistoriqueRepository {

    override suspend fun getHistoriques(): Flow<List<Historique>> {
        return dao.getHistoriques()
    }

    override suspend fun getHistoriqueByLieuCode(lieuCode: String): List<Historique>? {
        Timber.w("[HistoriqueRepositoryImpl] getHistoriqueByLieuCodeinvoke $lieuCode")
        return dao.getHistoriqueByLieuCode(lieuCode = lieuCode)
    }

    override suspend fun getHistoriqueByInterventionCode(interventionCode: String): Historique? {
        Timber.w("[HistoriqueRepositoryImpl] getHistoriqueByInterventionCode $interventionCode")
        return dao.getHistoriqueByInterventionCode(interventionCode = interventionCode)
    }

    override suspend fun insertHistorique(historique: Historique) {
        return dao.insertHistorique(historique = historique)
    }

    override suspend fun insertAllHistoriques(list: List<Historique>) {
        return dao.insertAllHistoriques(list = list)
    }

    override suspend fun deleteHistorique() {
        return dao.deleteHistorique()
    }

}