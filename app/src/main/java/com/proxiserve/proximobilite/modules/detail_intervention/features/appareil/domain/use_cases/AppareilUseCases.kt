package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases


/**
 * Created by dloriot on 26/08/2024.
 */
data class AppareilUseCases(
    val getAppareils: GetAppareils,
    val getAppareilById: GetAppareilById,
    val insertAppareil: InsertAppareil,
    val insertAppareils: InsertAppareils,
    val deleteAppareil: DeleteAppareil,
)
