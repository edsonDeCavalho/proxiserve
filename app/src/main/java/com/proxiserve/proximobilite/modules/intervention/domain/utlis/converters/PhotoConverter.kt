package com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.intervention.domain.model.AppareilCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo

/**
 * TypeConverter Photo
 * @author Edson De Carvalho
 */
class PhotoConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value : List<Photo>): String{
        val gson = Gson()
        val type = object : TypeToken<List<Photo>>() {}.type
        return gson.toJson(value,type)
    }
    @TypeConverter
    fun togroupTaskMemberList(value : String): List<Photo>{
        val gson = Gson()
        val type = object : TypeToken<List<Photo>>() {}.type
        return gson.fromJson(value,type)
    }
}