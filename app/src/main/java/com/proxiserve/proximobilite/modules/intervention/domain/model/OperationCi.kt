package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entité Operation
 * @author Edson De Carvalho
 */
@Entity
data class OperationCi(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String = "0",
    @ColumnInfo("categorie_op")
    val categorie_op: String,
    @ColumnInfo("condition_op")
    val condition_op: String,
    @ColumnInfo("metier_op")
    val metier_op	: String,
    @ColumnInfo("operation_code")
    val operation_code	: String = "",
    @ColumnInfo("remarque")
    var remarque: String = "")
