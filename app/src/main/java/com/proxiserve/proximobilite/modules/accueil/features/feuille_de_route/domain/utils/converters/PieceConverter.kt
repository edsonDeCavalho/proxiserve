package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece

/**
 * Created by dloriot on 26/08/2024.
 */
class PieceConverter {
    @TypeConverter
    fun fromString(value: String): List<Piece> {
        val listType = object: TypeToken<List<Piece>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Piece>): String {
        return Gson().toJson(list)
    }
}