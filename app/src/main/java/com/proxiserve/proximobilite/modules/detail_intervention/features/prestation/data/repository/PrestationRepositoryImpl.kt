package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository

import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.data.data_source.db.dao.PrestationDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PrestationRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by dloriot on 26/08/2024.
 */

class PrestationRepositoryImpl(
    private val dao: PrestationDao
): PrestationRepository {

    override suspend fun getPrestations(): Flow<List<Prestation>> {
        return dao.getPrestations()
    }

    override suspend fun getPrestationById(id: String): Prestation? {
        return dao.getPrestationById(id = id)
    }

    override suspend fun insertPrestation(prestation: Prestation) {
        return dao.insertPrestation(prestation = prestation)
    }

    override suspend fun deletePrestation() {
        return dao.deletePrestation()
    }

}