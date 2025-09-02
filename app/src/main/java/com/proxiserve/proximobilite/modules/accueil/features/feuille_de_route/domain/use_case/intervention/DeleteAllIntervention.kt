package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention


import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository
import timber.log.Timber

/**
 * Created by dloriot on 15/10/2024.
 */
data class DeleteAllIntervention(
    private val repository: InterventionRepository
) {
    val TAG = "[DeleteAllIntervention]"
    suspend operator fun invoke() {
        Timber.i("$TAG invoke")
        return repository.deleteAllIntervention()
    }
}