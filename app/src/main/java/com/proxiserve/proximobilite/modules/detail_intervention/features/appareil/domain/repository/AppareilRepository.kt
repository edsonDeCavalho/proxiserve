package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.repository

import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention

/**
 * Created by dloriot on 26/08/2024.
 */

interface AppareilRepository {

    suspend fun getAppareils(intervention: Intervention): List<Appareil>?
    suspend fun getAppareilById(id: String): Appareil?
    suspend fun insertAppareil(appareil: Appareil)
    suspend fun insertAppareils(list: List<Appareil>)
    suspend fun deleteAppareil()

}