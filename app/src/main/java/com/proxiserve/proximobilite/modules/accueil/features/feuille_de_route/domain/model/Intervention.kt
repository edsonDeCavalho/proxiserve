package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.FdrUtils
import kotlinx.serialization.Serializable
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Serializable
@Entity
data class Intervention(
    @PrimaryKey
    @Nonnull
    @SerializedName("intervention_code")
    var interventionCode: String,
    @SerializedName("ville_agence" )
    var villeAgence : String?= "",
    @SerializedName("ville")
    var ville : String?= "",
    @SerializedName("urgent" )
    var urgent: String?= "",
    @SerializedName("typologie")
    var typologie : String?= "",
    @SerializedName("type_logement")
    var typeLogement: String?= "",
    @SerializedName("telephone3" )
    var telephone3: String?= "",
    @SerializedName("telephone2" )
    var telephone2: String?= "",
    @SerializedName("telephone1" )
    var telephone1: String?= "",
    @SerializedName("tel_agence" )
    var telAgence : String?= "",
    @SerializedName("societe_agence" )
    var societeAgence : String?= "",
    @SerializedName("refusOptin" )
    var refusOptin: Int? = 0,
    @SerializedName("ref_client_log" )
    var refClientLog: String?= "",
    @SerializedName("ref_client_groupe")
    var refClientGroupe : String?= "",
    @SerializedName("ref_cli_lg" )
    var refCliLg: String?= "",
    @SerializedName("Prestations")
    var prestations : List<Prestation> = arrayListOf(),
    @SerializedName("prenomTechnicien" )
    var prenomTechnicien: String?= "",
    @SerializedName("position" )
    var position: String?= "",
    @SerializedName("periode_rdv")
    var periodeRdv: String?= "",
    @SerializedName("optin")
    var optin : Int? = 0,
    @SerializedName("observation")
    var observation : String?= "",
    @SerializedName("numclient")
    var numclient : String?= "",
    @SerializedName("num_logement" )
    var numLogement : String?= "",
    @SerializedName("num_contrat")
    var numContrat: String?= "",
    @SerializedName("num_bon_com")
    var numBonCom : String?= "",
    @SerializedName("nomTechnicien")
    var nomTechnicien : String?= "",
    @SerializedName("nom_client" )
    var nomClient : String?= "",
    @SerializedName("nom_agence" )
    var nomAgence : String?= "",
    @SerializedName("motif_appel")
    var motifAppel: String?= "",
    @SerializedName("media_id" )
    var mediaId : String?= "",
    @SerializedName("matriculeTechnicien")
    var matriculeTechnicien : String?= "",
    @SerializedName("longitude")
    var longitude : String?= "",
    @SerializedName("loginTechnicien")
    var loginTechnicien : String?= "",
    @SerializedName("lieu_code")
    var lieuCode: String?= "",
    @SerializedName("latitude" )
    var latitude: String?= "",
    @SerializedName("km" )
    var km: String?= "",
    @SerializedName("interventionType_libelle" )
    var interventionTypeLibelle : String?= "",
    @SerializedName("interventionType_code")
    var interventionTypeCode: String?= "",
    @SerializedName("heure_rdv")
    var heureRdv: String?= "",
    @SerializedName("groupe" )
    var groupe: String?= "",
    @SerializedName("fax_agence" )
    var faxAgence : String?= "",
    @SerializedName("etage")
    var etage : String?= "",
    @SerializedName("envoiEmailGardien")
    var envoiEmailGardien : Int? = 0,
    @SerializedName("entree" )
    var entree: String?= "",
    @SerializedName("emailGardien" )
    var emailGardien: String?= "",
    @SerializedName("digicode" )
    var digicode: String?= "",
    @SerializedName("dateVisite" )
    var dateVisite: Int? = 0,
    @SerializedName("dateOptin")
    var dateOptin : String?= "",
    @SerializedName("cp_agence")
    var cpAgence: String?= "",
    @SerializedName("cp" )
    var cp: String?= "",
    @SerializedName("contact_titre")
    var contactTitre: String?= "",
    @SerializedName("contact_prenom" )
    var contactPrenom : String?= "",
    @SerializedName("contact_nom")
    var contactNom: String?= "",
    @SerializedName("contact_email")
    var contactEmail: String?= "",
    @SerializedName("client_ville" )
    var clientVille : String?= "",
    @SerializedName("client_type")
    var clientType: String?= "",
    @SerializedName("client_cp")
    var clientCp: String?= "",
    @SerializedName("client_adresse_comp")
    var clientAdresseComp : String?= "",
    @SerializedName("client_adresse" )
    var clientAdresse : String?= "",
    @SerializedName("bat")
    var bat : String?= "",
    @SerializedName("appareils")
    var appareils : List<String> = arrayListOf(),
    @SerializedName("appareil_mesure")
    var appareilMesure: String?= "",
    @SerializedName("appareil_code")
    var appareilCode: String?= "",
    @SerializedName("adresseComplementaire")
    var adresseComplementaire : String?= "",
    @SerializedName("adresse_agence" )
    var adresseAgence : String?= "",
    @SerializedName("adresse")
    var adresse : String?= ""
) {
    fun getDateVisiteToString(): String? {
        return this.dateVisite?.let { TimeUtils.dateMilisToString(it) }
    }

    fun getPeriode(): String? {
        return this.periodeRdv?.let { FdrUtils.getPeriodeRdv(it) }
    }
}
