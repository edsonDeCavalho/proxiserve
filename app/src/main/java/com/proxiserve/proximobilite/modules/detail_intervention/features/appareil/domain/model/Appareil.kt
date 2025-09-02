package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class Appareil(
    @PrimaryKey
    @Nonnull
    @SerializedName("appareil_code")
    var appareilCode: String,
    @SerializedName("wc" )
    var wc: String?= "",
    @SerializedName("type_reseau")
    var typeReseau: String?= "",
    @SerializedName("thermostatSondeExterieure")
    var thermostatSondeExterieure : Boolean = false,
    @SerializedName("thermostatRobinets" )
    var thermostatRobinets: Boolean = false,
    @SerializedName("thermostatNbRobinets" )
    var thermostatNbRobinets: String?= "",
    @SerializedName("thermostatHorloge")
    var thermostatHorloge : Boolean = false,
    @SerializedName("thermostatAmbiance" )
    var thermostatAmbiance: Boolean = false,
    @SerializedName("sous_contrat" )
    var sousContrat : Boolean = false,
    @SerializedName("raccord_gaziniere")
    var raccordGaziniere: String?= "",
    @SerializedName("quantite" )
    var quantite: Int = 0,
    @SerializedName("numeroSerie")
    var numeroSerie : String?= "",
    @SerializedName("newDateFinFlexible" )
    var newDateFinFlexible: String?= "",
    @SerializedName("modele" )
    var modele: String?= "",
    @SerializedName("mater_canalisation" )
    var materCanalisation : String?= "",
    @SerializedName("marque_code")
    var marqueCode: String?= "",
    @SerializedName("libelle")
    var libelle : String?= "",
    @SerializedName("installe_par" )
    var installePar : String?= "",
    @SerializedName("flexible_gaz" )
    var flexibleGaz : String?= "",
    @SerializedName("energie_code" )
    var energieCode : String?= "",
    @SerializedName("dureeGarantie")
    var dureeGarantie : String?= "",
    @SerializedName("duree_illimite" )
    var dureeIllimite : Boolean = false,
    @SerializedName("depose_par" )
    var deposePar : String?= "",
    @SerializedName("dateMiseEnService")
    var dateMiseEnService : Int = 0,
    @SerializedName("DateFinFlexible")
    var DateFinFlexible : Int = 0,
    @SerializedName("chauffagePuissanceNominale" )
    var chauffagePuissanceNominale: String?= "",
    @SerializedName("chauffagemissionPolluants_code" )
    var chauffageEmissionPolluantsCode : String?= "",
    @SerializedName("chauffagemissionOxydeAzote_code")
    var chauffageEmissionOxydeAzoteCode: String?= "",
    @SerializedName("chauffageEvacuationType_code" )
    var chauffageEvacuationTypeCode : String?= "",
    @SerializedName("chauffageChaudiereType_code")
    var chauffageChaudiereTypeCode: String?= "",
    @SerializedName("chauffageChaudiererendement_code" )
    var chauffageChaudiererendementCode : String?= "",
    @SerializedName("bruleurType_code" )
    var bruleurTypeCode : String?= "",
    @SerializedName("bruleurPuissance" )
    var bruleurPuissance: String?= "",
    @SerializedName("bruleurModele")
    var bruleurModele : String?= "",
    @SerializedName("bruleurMarque_code" )
    var bruleurMarqueCode : String?= "",
    @SerializedName("bruleurDateMiseEnService" )
    var bruleurDateMiseEnService: Int = 0,
    @SerializedName("boucheVmcUniteWc" )
    var boucheVmcUniteWc: String?= "",
    @SerializedName("boucheVmcUniteSalleDeBain")
    var boucheVmcUniteSalleDeBain : String?= "",
    @SerializedName("boucheVmcUniteNbEntreeAir")
    var boucheVmcUniteNbEntreeAir : String?= "",
    @SerializedName("boucheVmcUniteMesure_id")
    var boucheVmcUniteMesureId: String?= "",
    @SerializedName("boucheVmcUniteInterrupteurDsc")
    var boucheVmcUniteInterrupteurDsc : Boolean = false,
    @SerializedName("boucheVmcUniteCuisine")
    var boucheVmcUniteCuisine : String?= "",
    @SerializedName("boucheVmcUniteAutre")
    var boucheVmcUniteAutre : String?= "",
    @SerializedName("about_code" )
    var aboutCode: String?  = "")
