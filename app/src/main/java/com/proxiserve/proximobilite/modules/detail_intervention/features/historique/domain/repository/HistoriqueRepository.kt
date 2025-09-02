package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository

import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */

interface HistoriqueRepository {

    suspend fun getHistoriques(): Flow<List<Historique>>
    suspend fun getHistoriqueByLieuCode(lieuCode: String): List<Historique>?
    suspend fun getHistoriqueByInterventionCode(interventionCode: String): Historique?
    suspend fun insertHistorique(historique: Historique)
    suspend fun insertAllHistoriques(list: List<Historique>)
    suspend fun deleteHistorique()

}