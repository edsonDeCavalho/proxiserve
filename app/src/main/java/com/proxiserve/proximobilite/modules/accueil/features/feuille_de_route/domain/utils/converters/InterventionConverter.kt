package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention

/**
 * Created by dloriot on 26/08/2024.
 */


class InterventionConverter {

    @TypeConverter
    fun fromString(value: String): List<Intervention> {
        val listType = object: TypeToken<List<Intervention>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Intervention>): String {
        return Gson().toJson(list)
    }

}

