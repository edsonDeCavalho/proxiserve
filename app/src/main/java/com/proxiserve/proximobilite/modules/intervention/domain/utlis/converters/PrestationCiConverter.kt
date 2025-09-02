package com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.PieceCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.PrestationCi

class PrestationCiConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value : List<PrestationCi>): String{
        val gson = Gson()
        val type = object : TypeToken<List<PrestationCi>>() {}.type
        return gson.toJson(value,type)
    }
    @TypeConverter
    fun togroupTaskMemberList(value : String): List<PrestationCi>{
        val gson = Gson()
        val type = object : TypeToken<List<PrestationCi>>() {}.type
        return gson.fromJson(value,type)
    }
}