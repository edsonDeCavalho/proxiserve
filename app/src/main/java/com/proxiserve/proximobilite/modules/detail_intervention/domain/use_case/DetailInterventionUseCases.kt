package com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case

/**
 * Created by dloriot on 05/09/2024.
 */
data class DetailInterventionUseCases(
    val sendCiAbsent: SendCiAbsent,
    val sendCiRefus: SendCiRefus,
    val sendCiInjustifie: SendCiInjustifie
)