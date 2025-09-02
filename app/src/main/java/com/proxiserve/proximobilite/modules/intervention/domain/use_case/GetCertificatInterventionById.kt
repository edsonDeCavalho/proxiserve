package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
/**
 * GetCertificatInterventionById
 * @author Edson De Carvalho
 */
data class GetCertificatInterventionById(
    private val repository: CertificatInterventionRepository
) {
    suspend operator fun invoke(id : String): CertificatIntervention? {
        return repository.getCertificatInterventionById(id)
    }
}