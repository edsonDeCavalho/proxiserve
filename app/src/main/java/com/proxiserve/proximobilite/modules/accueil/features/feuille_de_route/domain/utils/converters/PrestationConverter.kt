package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation

/**
 * Created by dloriot on 26/08/2024.
 */
class PrestationConverter {
    @TypeConverter
    fun fromString(value: String): List<Prestation> {
        val listType = object: TypeToken<List<Prestation>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Prestation>): String {
        return Gson().toJson(list)
    }
}