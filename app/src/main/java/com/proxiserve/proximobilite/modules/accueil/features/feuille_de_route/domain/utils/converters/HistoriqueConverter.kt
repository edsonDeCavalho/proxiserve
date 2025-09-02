package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation

/**
 * Created by dloriot on 26/08/2024.
 */
class HistoriqueConverter {
    @TypeConverter
    fun fromString(value: String): List<Historique> {
        val listType = object: TypeToken<List<Historique>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Historique>): String {
        return Gson().toJson(list)
    }
}