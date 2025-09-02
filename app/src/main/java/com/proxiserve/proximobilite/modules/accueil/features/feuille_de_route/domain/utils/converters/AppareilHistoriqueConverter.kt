package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.AppareilHistorique

/**
 * Created by dloriot on 26/08/2024.
 */
class AppareilHistoriqueConverter {
    @TypeConverter
    fun fromString(value: String): List<AppareilHistorique> {
        val listType = object: TypeToken<List<AppareilHistorique>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<AppareilHistorique>): String {
        return Gson().toJson(list)
    }
}