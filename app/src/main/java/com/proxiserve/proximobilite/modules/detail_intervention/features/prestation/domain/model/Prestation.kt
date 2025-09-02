package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class Prestation(
    @PrimaryKey
    @Nonnull
    @SerializedName("prestation_code")
    var prestationCode : String,
    @SerializedName("propagation")
    var propagation: String = "",
    @SerializedName("prestation_type")
    var prestationType : String = "",
    @SerializedName("prestation_libelle" )
    var prestationLibelle: String = "",
    @SerializedName("prestation_active")
    var prestationActive : Int= 0,
    @SerializedName("nom_client" )
    var nomClient: String = "",
    @SerializedName("id_couverture")
    var idCouverture : String = "",
    @SerializedName("dateDerniereVisite" )
    var dateDerniereVisite : Int= 0
)
