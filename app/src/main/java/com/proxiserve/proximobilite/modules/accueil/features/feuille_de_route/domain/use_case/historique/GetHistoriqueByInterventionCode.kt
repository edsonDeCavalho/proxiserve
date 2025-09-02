//package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.historique
//
//import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Historique
//import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.HistoriqueRepository
//import timber.log.Timber
//
///**
// * Created by dloriot on 07/09/2024.
// */
//data class GetHistoriqueByInterventionCode(
//    private val repository: HistoriqueRepository
//) {
//    suspend operator fun invoke(interventionCode: String): Historique? {
//        Timber.w("[GetHistoriqueByInterventionCode] invoke $interventionCode")
//        return repository.getHistoriqueByInterventionCode(interventionCode = interventionCode)
//    }
//}