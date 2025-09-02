package com.proxiserve.proximobilite.modules.detail_intervention.domain.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */

interface InterventionRepository {

    suspend fun getInterventions(): Flow<List<Intervention>>
    suspend fun getInterventionById(id: String): Intervention?
    suspend fun insertIntervention(intervention: Intervention)
    suspend fun insertAllInterventions(list: List<Intervention>)
    suspend fun deleteIntervention(intervention: Intervention)
    suspend fun deleteAllIntervention()

    suspend fun sendCiAbsent(intervention: Intervention)
    suspend fun sendCiRefus(intervention: Intervention)
    suspend fun sendCiInjustifie(intervention: Intervention)
}