package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import kotlinx.coroutines.flow.Flow


/**
 * Created by dloriot on 26/08/2024.
 */

interface PrestationRepository {

    suspend fun getPrestations(): Flow<List<Prestation>>
    suspend fun getPrestationById(id: String): Prestation?
    suspend fun insertPrestation(prestation: Prestation)
    suspend fun deletePrestation()

}