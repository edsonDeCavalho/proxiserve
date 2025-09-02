package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.PrestationHistorique

/**
 * Created by dloriot on 26/08/2024.
 */
class PrestationHistoriqueConverter {
    @TypeConverter
    fun fromString(value: String): List<PrestationHistorique> {
        val listType = object: TypeToken<List<PrestationHistorique>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<PrestationHistorique>): String {
        return Gson().toJson(list)
    }
}