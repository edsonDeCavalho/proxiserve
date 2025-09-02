package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dloriot on 18/09/2024.
 */
class PrestationHistorique(
    @SerializedName("prestation_libelle")
    var prestationLibelle: String,
    @SerializedName("prestation_code")
    var prestationCode: String,
    @SerializedName("prestation_type")
    var prestationType: String,
)