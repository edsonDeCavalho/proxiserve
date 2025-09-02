package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
/**
 * UpdateCetificatIntervention
 * @author Edson De Carvalho
 */
data class UpdateCetificatIntervention(
    private val repository: CertificatInterventionRepository
) {
    suspend operator fun invoke(certificatIntervention: CertificatIntervention) {
        repository.updateCetificatIntervention(certificatIntervention = certificatIntervention)
    }
}