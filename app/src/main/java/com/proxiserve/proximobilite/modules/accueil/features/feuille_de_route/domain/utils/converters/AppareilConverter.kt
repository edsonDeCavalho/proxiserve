package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil

/**
 * Created by dloriot on 26/08/2024.
 */
class AppareilConverter {
    @TypeConverter
    fun fromString(value: String): List<Appareil> {
        val listType = object: TypeToken<List<Appareil>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Appareil>): String {
        return Gson().toJson(list)
    }
}