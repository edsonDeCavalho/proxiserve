package com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi

/**
 * TypeConverter OperationCi
 * @author Edson De Carvalho
 */
class OperationCiConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value : List<OperationCi>): String{
        val gson = Gson()
        val type = object : TypeToken<List<OperationCi>>() {}.type
        return gson.toJson(value,type)
    }
    @TypeConverter
    fun togroupTaskMemberList(value : String): List<OperationCi>{
        val gson = Gson()
        val type = object : TypeToken<List<OperationCi>>() {}.type
        return gson.fromJson(value,type)
    }
}