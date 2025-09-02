package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by dloriot on 26/08/2024.
 */


class StringConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object: TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

}

