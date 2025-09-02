package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class Piece(
    @PrimaryKey
    @Nonnull
    @SerializedName("piece_code" )
    var pieceCode : String,
    @SerializedName("quantite" )
    var quantite: Int?= null,
    @SerializedName("piece_libelle")
    var pieceLibelle: String? = null,
    @SerializedName("localisation_libelle" )
    var localisationLibelle : String? = null
)
