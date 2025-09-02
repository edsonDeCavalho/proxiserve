package com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.intervention.domain.model.AppareilCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi

/**
 * TypeConverter ApparailCi
 * @author Edson De Carvalho
 */
class AppareilCiConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value : List<AppareilCi>): String{
        val gson = Gson()
        val type = object : TypeToken<List<AppareilCi>>() {}.type
        return gson.toJson(value,type)
    }
    @TypeConverter
    fun togroupTaskMemberList(value : String): List<AppareilCi>{
        val gson = Gson()
        val type = object : TypeToken<List<AppareilCi>>() {}.type
        return gson.fromJson(value,type)
    }
}