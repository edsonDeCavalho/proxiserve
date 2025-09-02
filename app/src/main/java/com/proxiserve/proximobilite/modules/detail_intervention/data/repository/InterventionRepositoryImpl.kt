package com.proxiserve.proximobilite.modules.detail_intervention.data.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.InterventionDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

/**
 * Created by dloriot on 26/08/2024.
 */

class InterventionRepositoryImpl(
    private val dao: InterventionDao
): InterventionRepository {

    val TAG = "[IntervRepositoryImpl]"

    override suspend fun getInterventions(): Flow<List<Intervention>> {
        return dao.getInterventions()
    }

    override suspend fun getInterventionById(id: String): Intervention? {
        return dao.getInterventionById(interventionCode = id)
    }

    override suspend fun insertIntervention(intervention: Intervention) {
        return dao.insertIntervention(intervention = intervention)
    }

    override suspend fun insertAllInterventions(list: List<Intervention>) {
        return dao.insertAllInterventions(list = list)
    }


    override suspend fun deleteIntervention(intervention: Intervention) {
        return dao.deleteIntervention(intervention = intervention)
    }

    override suspend fun deleteAllIntervention() {
        return dao.deleteAllIntervention()
    }

    override suspend fun sendCiAbsent(intervention: Intervention) {
        Timber.i("$TAG sendCiAbsent n° %s", intervention.interventionCode)
    }

    override suspend fun sendCiRefus(intervention: Intervention) {
        Timber.i("$TAG sendCiRefus n° %s", intervention.interventionCode)
    }

    override suspend fun sendCiInjustifie(intervention: Intervention) {
        Timber.i("$TAG sendCiInjustifie n° %s", intervention.interventionCode)
    }

}