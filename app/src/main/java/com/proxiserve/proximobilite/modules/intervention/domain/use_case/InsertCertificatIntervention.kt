package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository

data class InsertCertificatIntervention(
    private val repository: CertificatInterventionRepository
) {
    suspend operator fun invoke(certificatIntervention: CertificatIntervention) {
        repository.insertCertificatIntervention(certificatIntervention = certificatIntervention)
    }
}