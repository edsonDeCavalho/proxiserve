package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
/**
 * DeleteCertificationIntervention
 * @author Edson De Carvalho
 */
data class DeleteCertificationIntervention(
    private val repository: CertificatInterventionRepository
) {
    suspend operator fun invoke(certificatIntervention: CertificatIntervention) {
        return repository.deleteCertificationIntervention(certificatIntervention)
    }
}