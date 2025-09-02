package com.proxiserve.proximobilite.modules.intervention.domain.repository

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
/**
 * CertificatRepository
 * @author Edson De CARVALHO
 */
interface CertificatInterventionRepository {
    suspend fun getAllCertificationIntervention(): List<CertificatIntervention>?

    suspend fun getCertificatInterventionById(id: String): CertificatIntervention?

    suspend fun updateCetificatIntervention(certificatIntervention: CertificatIntervention)

    suspend fun insertCertificatIntervention(certificatIntervention: CertificatIntervention)

    suspend fun deleteCertificationIntervention(certificatIntervention:CertificatIntervention)
}