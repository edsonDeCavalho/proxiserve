package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository
import timber.log.Timber

/**
 * Created by dloriot on 26/08/2024.
 */
data class InsertAllInterventions(
    private val repository: InterventionRepository
) {
    val TAG = "[InsertAllInterventions]"
    suspend operator fun invoke(list: List<Intervention>) {
        Timber.w("$TAG invoke")
        return repository.insertAllInterventions(list = list)
    }
}
