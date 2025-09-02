package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import kotlinx.coroutines.flow.Flow


/**
 * Created by dloriot on 26/08/2024.
 */

interface OperationRepository {

    suspend fun getOperations(): Flow<List<Operation>>
    suspend fun getOperationById(id: String): Operation?
    suspend fun insertOperation(operation: Operation)
    suspend fun deleteOperation()

}