package com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.proxiserve.proximobilite.modules.intervention.domain.model.AppareilCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.PieceCi

/**
 * TypeConverter PiecesCi
 * @author Edson De Carvalho
 */
class PieceCiConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value : List<PieceCi>): String{
        val gson = Gson()
        val type = object : TypeToken<List<PieceCi>>() {}.type
        return gson.toJson(value,type)
    }
    @TypeConverter
    fun togroupTaskMemberList(value : String): List<PieceCi>{
        val gson = Gson()
        val type = object : TypeToken<List<PieceCi>>() {}.type
        return gson.fromJson(value,type)
    }
}