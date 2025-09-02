package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.OperationDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.OperationRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by dloriot on 26/08/2024.
 */

class OperationRepositoryImpl(
    private val dao: OperationDao
): OperationRepository {

    override suspend fun getOperations(): Flow<List<Operation>> {
        return dao.getOperations()
    }

    override suspend fun getOperationById(id: String): Operation? {
        return dao.getOperationById(id = id)
    }

    override suspend fun insertOperation(operation: Operation) {
        return dao.insertOperation(operation = operation)
    }

    override suspend fun deleteOperation() {
        return dao.deleteOperation()
    }

}