package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class Historique(
    @PrimaryKey
    @SerializedName("intervention_code")
    var interventionCode: String = "",
    @SerializedName("visiteRealisees")
    var visiteRealisees : String= "",
    @SerializedName("ville")
    var ville : String= "",
    @SerializedName("suiteADonner" )
    var suiteADonner: String= "",
    @SerializedName("satisfaction" )
    var satisfaction: String= "",
    @SerializedName("resultat" )
    var resultat: String= "",
    @SerializedName("prestations")
    var prestations : List<PrestationHistorique> = arrayListOf(),
    @SerializedName("prenomTechnicien" )
    var prenomTechnicien: String= "",
    @SerializedName("position" )
    var position: String= "",
    @SerializedName("pieces" )
    var pieces: List<Piece> = arrayListOf(),
    @SerializedName("operations" )
    var operations: List<Operation> = arrayListOf(),
    @SerializedName("observationTechnicien")
    var observationTechnicien : String= "",
    @SerializedName("observation")
    var observation : String= "",
    @SerializedName("num_logement" )
    var numLogement : String= "",
    @SerializedName("nomTechnicien")
    var nomTechnicien : String= "",
    @SerializedName("loginTechnicien")
    var loginTechnicien : String= "",
    @SerializedName("lieu_code")
    var lieuCode: String= "",
    @SerializedName("interventionType_libelle" )
    var interventionTypeLibelle : String= "",
    @SerializedName("interventionType_code")
    var interventionTypeCode: String= "",

    @SerializedName("heureFin" )
    var heureFin: String= "",
    @SerializedName("heureDebut" )
    var heureDebut: String= "",
    @SerializedName("etage")
    var etage : String= "",
    @SerializedName("entree" )
    var entree: String= "",
    @SerializedName("dateRDV")
    var dateRDV : Int = 0,
    @SerializedName("cp" )
    var cp: String= "",
    @SerializedName("controle_vacuite" )
    var controleVacuite : String= "",
    @SerializedName("contact_prenom" )
    var contactPrenom : String= "",
    @SerializedName("contact_nom")
    var contactNom: String= "",
    @SerializedName("contact_email")
    var contactEmail: String= "",
    @SerializedName("certificat_code")
    var certificatCode: String= "",
    @SerializedName("bat")
    var bat : String= "",
    @SerializedName("appareils")
    var appareils : List<AppareilHistorique> = arrayListOf(),
    @SerializedName("adresseComplementaire")
    var adresseComplementaire : String= "",
    @SerializedName("adresse")
    var adresse : String= ""
)
