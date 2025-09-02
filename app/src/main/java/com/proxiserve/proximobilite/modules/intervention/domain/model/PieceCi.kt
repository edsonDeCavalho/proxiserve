package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entité Piece CI
 * @author Edson De Carvalho
 *
 *
 */
@Entity
data class PieceCi(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String = "0",
    @ColumnInfo("adonix_piece_code")
    val adonix_piece_code: Int = 0,
    @ColumnInfo("localisation_code")
    val localisation_code: String = "",
    @ColumnInfo("adonix_quantite_initiale")
    val adonix_quantite_initiale: Int = 0,
    @ColumnInfo("piece")
    val piece: String = "",
    @ColumnInfo("num_contrat")
    val num_contrat: String = "",
    @ColumnInfo("localisation")
    val localisation: String = "",
    @ColumnInfo("adonix_quantite_consommee")
    val adonix_quantite_consommee: Int = 0,
    @ColumnInfo("piece_code")
    val piece_code: String = "",
    @ColumnInfo("adonix_libelle1")
    val adonix_libelle1: String = "",
    @ColumnInfo("quantite")
    val quantite: Int = 0,
    @ColumnInfo("adonix_libelle2")
    val adonix_libelle2: Int = 0,
)