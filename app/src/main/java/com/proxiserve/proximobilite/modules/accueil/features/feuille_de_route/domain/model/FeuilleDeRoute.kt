package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class FeuilleDeRoute(
    @PrimaryKey()
    @Nonnull
    @SerializedName("id_technicien")
    var idTechnicien : String,
    @SerializedName("historiques")
    var historiques: List<Historique> = listOf(),
    @SerializedName("feuille_de_route" )
    var interventions : List<Intervention> = listOf(),
    @SerializedName("appareils")
    var appareils: List<Appareil> = listOf()
)
