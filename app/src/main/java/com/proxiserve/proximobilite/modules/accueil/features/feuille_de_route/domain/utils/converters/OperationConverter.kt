package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation

/**
 * Created by dloriot on 26/08/2024.
 */
class OperationConverter {
    @TypeConverter
    fun fromString(value: String): List<Operation> {
        val listType = object: TypeToken<List<Operation>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Operation>): String {
        return Gson().toJson(list)
    }
}