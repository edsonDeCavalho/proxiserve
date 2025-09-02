package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.use_case.DeleteUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetRemoteUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAliasById
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAliasState
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.InsertUserAlias
/**
 * CertificatInterventionUseCases
 * @author Edson De CARVALHO
 */
data class CertificatInterventionUseCases (
    val getAllCertificationIntervention: GetAllCertificationIntervention,
    val getCertificatInterventionById: GetCertificatInterventionById,
    val updateCetificatIntervention: UpdateCetificatIntervention,
    val insertCertificatIntervention: InsertCertificatIntervention,
    val deleteCertificationIntervention: DeleteCertificationIntervention
)