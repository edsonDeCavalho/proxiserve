package com.proxiserve.proximobilite.modules.intervention.data.repository

import com.proxiserve.proximobilite.modules.intervention.data.db.CertificatInterventionDao
import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import timber.log.Timber
/**
 * CertificationInterventionImplementation
 * @Edson De Carvalho
 */
class CertificatInterventionRepositoryImpl(
    private val dao: CertificatInterventionDao,
) : CertificatInterventionRepository {
    private val TAG = "[CertificatInterventionRepositoryImpl]"
    override suspend fun getAllCertificationIntervention(): List<CertificatIntervention>? {
        Timber.i("$TAG getAllCertificationIntervention")
        val result = dao.getAllCertificatIntervention()
        if(result.isNotEmpty()) Timber.e("$TAG getAllCertificationIntervention pas de Certifications d'intervention")
        return result
    }

    override suspend fun getCertificatInterventionById(id: String): CertificatIntervention? {
        Timber.i("$TAG getCertificatInterventionById")
        return dao.getCertificatInterventionCiById(id = id)
    }

    override suspend fun updateCetificatIntervention(certificatIntervention: CertificatIntervention) {
        Timber.i("$TAG updateCetificatIntervention")
        dao.updateCertificatIntervention(certificatIntervention)
    }

    override suspend fun insertCertificatIntervention(certificatIntervention: CertificatIntervention) {
        Timber.i("$TAG insertCertificatIntervention")
        dao.insert(certificatIntervention)
    }

    override suspend fun deleteCertificationIntervention(certificatIntervention: CertificatIntervention){
        Timber.i("$TAG deleteCertificationInterventionByID")
        dao.deleteCertificatIntervention(certificatIntervention)
    }
}