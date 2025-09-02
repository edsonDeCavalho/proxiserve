package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Photo
 * @author Edson De CARVALHO
 */
@Entity
data class Photo(
    @PrimaryKey
    @ColumnInfo("id")
    var id : String = "",
    @ColumnInfo("id_certificat")
    var id_certificat : String = "",
    @ColumnInfo("photo")
    var photo : String =""
){

}