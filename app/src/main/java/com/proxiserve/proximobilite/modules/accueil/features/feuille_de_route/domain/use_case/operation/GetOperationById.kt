package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.OperationRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class GetOperationById(
    private val repository: OperationRepository
) {
    suspend operator fun invoke(id: String): Operation? {
        return repository.getOperationById(id = id)
    }
}
