package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
/**
 * GetAllCertificationIntervention
 * @author Edson De Carvalho
 */
data class GetAllCertificationIntervention(
    private val repository: CertificatInterventionRepository
) {
    suspend operator fun invoke(certificatIntervention: CertificatIntervention) : List<CertificatIntervention>? {
        return repository.getAllCertificationIntervention()
    }
}