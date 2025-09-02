package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.OperationRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */
data class GetOperations(
    private val repository: OperationRepository
) {
    suspend operator fun invoke(): Flow<List<Operation>> {
        return repository.getOperations()
    }
}
