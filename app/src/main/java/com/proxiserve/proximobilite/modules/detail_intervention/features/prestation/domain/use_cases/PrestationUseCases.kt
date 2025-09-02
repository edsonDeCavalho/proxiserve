package com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases

/**
 * Created by dloriot on 26/08/2024.
 */
data class PrestationUseCases(
    val getPrestations: GetPrestations,
    val getPrestationById: GetPrestationById,
    val insertPrestation: InsertPrestation,
    val deletePrestation: DeletePrestation
)
