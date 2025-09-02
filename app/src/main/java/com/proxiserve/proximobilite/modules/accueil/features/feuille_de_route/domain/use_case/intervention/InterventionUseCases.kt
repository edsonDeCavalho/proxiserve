package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention

/**
 * Created by dloriot on 26/08/2024.
 */
data class InterventionUseCases(
    val getInterventions: GetInterventions,
    val getInterventionById: GetInterventionById,
    val insertAllInterventions: InsertAllInterventions,
    val insertIntervention: InsertIntervention,
    val deleteIntervention: DeleteIntervention,
    val deleteAllIntervention: DeleteAllIntervention
)