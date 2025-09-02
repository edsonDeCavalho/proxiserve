package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation


/**
 * Created by dloriot on 26/08/2024.
 */
data class OperationUseCases(
    val getOperations: GetOperations,
    val getOperationById: GetOperationById,
    val insertOperation: InsertOperation,
    val deleteOperation: DeleteOperation
)
