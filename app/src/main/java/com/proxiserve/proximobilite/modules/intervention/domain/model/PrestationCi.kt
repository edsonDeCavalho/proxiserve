package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entité Prestation
 * @author Edson De Carvalho
 */
@Entity
data class PrestationCi(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String ="0",
    @ColumnInfo("prestation_libelle")
    val prestation_libelle	: String ="",
    @ColumnInfo("id_couverture")
    val id_couverture: String ="",
    @ColumnInfo(name = "prestation_active")
    val prestation_active : Boolean = false,
    @ColumnInfo(name = "prestation_type")
    val prestation_type	: String = "",
    @ColumnInfo(name = "dateDernierevisite")
    val dateDernierevisite: Int? = 0,
    @ColumnInfo(name = "prestation_code")
    val prestation_code	: String = "")
{

}