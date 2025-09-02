//package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.historique
//
//import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Historique
//import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.HistoriqueRepository
//import kotlinx.coroutines.flow.Flow
//
///**
// * Created by dloriot on 26/08/2024.
// */
//data class GetHistoriques(
//    private val repository: HistoriqueRepository
//) {
//    suspend operator fun invoke(): Flow<List<Historique>> {
//        return repository.getHistoriques()
//    }
//}
