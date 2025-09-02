package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.data.repository

import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.data.data_source.AppareilDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.repository.AppareilRepository

/**
 * Created by dloriot on 26/08/2024.
 */

class AppareilRepositoryImpl(
    private val dao: AppareilDao
) : AppareilRepository {

    val TAG = "[AppareilRepositoryImpl]"
    override suspend fun getAppareils(intervention: Intervention): List<Appareil>? {

        intervention.let {
//            val appareilId: List<String> = it.appareils
//            val appareils: List<Appareil> = emptyList()
//
//            if (appareilId.isNotEmpty()) {
//                for (id: String in appareilId) {
//
//                    Timber.d("$TAG getAppareils id => $id")
//                    val app: Appareil? = dao.getAppareilById(id = id)
//                    app?.let {
//                        appareils.plus(element = app)
//                    }
//
//                }
//                return appareils
//            }
            return dao.getAppareils(idList = it.appareils)
        }

        return null

    }

    override suspend fun getAppareilById(id: String): Appareil? {
        return dao.getAppareilById(id = id)
    }

    override suspend fun insertAppareil(appareil: Appareil) {
        return dao.insertAppareil(appareil = appareil)
    }

    override suspend fun insertAppareils(list: List<Appareil>) {
        return dao.insertAppareils(list = list)
    }

    override suspend fun deleteAppareil() {
        return dao.deleteAppareil()
    }

}