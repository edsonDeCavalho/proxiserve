package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dloriot on 18/09/2024.
 */
data class AppareilHistorique(
    @SerializedName("appareil_modele")
    var appareilModel: String,
    @SerializedName("appareil")
    var appareil: String,
)