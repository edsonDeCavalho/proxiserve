package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.AppareilCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.OperationCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.PieceCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.PrestationCiConverter

/**
 * Entité CertificatIntervention
 * @author Edson De Carvalho
 */
@Entity(tableName = "certificatIntervention")
data class CertificatIntervention(
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: String = "0",
    @ColumnInfo("motif_appel")
    var motif_appel	 : String = "",
    @ColumnInfo("temp_fumees")
    var temp_fumees		 : String = "",
    @ColumnInfo("id_appareil")
    var id_appareil		 : String = "",
    @ColumnInfo("nom_client")
    var nom_client	 : String = "",
    @ColumnInfo("num_bon_com")
    var num_bon_com	 : String = "",
    @ColumnInfo("email_gardien")
    var email_gardien : String = "",
    @ColumnInfo("energie_code_appareil")
    var energie_code_appareil : String = "",
    @ColumnInfo("batiment")
    var batiment: String = "",
    @ColumnInfo("prenomTechnicien")
    var prenomTechnicien : String = "",
    @ColumnInfo("refus_optin")
    var refus_optin : Boolean = false,
    @ColumnInfo("modele_appareil")
    var modele_appareil	: String = "",
    @ColumnInfo("piecesLaissees")
    var piecesLaissees	: Boolean = false,
    @ColumnInfo("faire_devis")
    var faire_devis	: Boolean = false,

    //
    @SerializedName("appareils")
    @ColumnInfo("appareils")
    @TypeConverters(AppareilCiConverter::class)
    var appareils : List<AppareilCi>? = null,
    //

    @ColumnInfo("email_DGI_principal")
    var email_DGI_principal		: String = "",
    @ColumnInfo("emailAgence")
    var emailAgence	: String = "",
    @ColumnInfo("sms_statut")
    var sms_statut	: String = "",
    @ColumnInfo("version_apk")
    var version_apk	: String = "",
    @ColumnInfo("typologie")
    var typologie	: String = "",
    @ColumnInfo("optin_date")
    var optin_date : String = "",
    @ColumnInfo("ref_client_log")
    var ref_client_log	: String = "",
    @ColumnInfo("client_adresse_comp")
    var client_adresse_comp	: String = "",
    @ColumnInfo("devis_fait")
    var devis_fait	: String = "",
    @ColumnInfo("nom_agence")
    var nom_agence	: String = "",

    //
    @ColumnInfo("prestations")
    @SerializedName("prestations")
    @TypeConverters(PrestationCiConverter::class)
    var prestations : List<PrestationCi>? = null,
    //

    @ColumnInfo("dateheurescan1")
    var dateheurescan1	 : String = "",
    @ColumnInfo("etage")
    var etage: String = "",
    @ColumnInfo("reprendre_rdv")
    var reprendre_rdv: Boolean = false,
    @ColumnInfo("satisfaction_code")
    var satisfaction_code: String = "",
    @ColumnInfo("dateheurescan2")
    var dateheurescan2	: Int = 0,
    @ColumnInfo("reglementMontant")
    var reglementMontant: String = "",
    //
    @ColumnInfo("pieces")
    @SerializedName("pieces")
    @TypeConverters(PieceCiConverter::class)
    var pieces : List<PieceCi>? =null,
    //
    @ColumnInfo("signature")
    var signature: String = "",
    @ColumnInfo("co2")
    var co2		 : String = "",
    @ColumnInfo("ventilationsEtat_code")
    var ventilationsEtat_code: Boolean = false,
    @ColumnInfo("optin")
    var optin: Boolean = false,
    @ColumnInfo("adonix_emplacement_code")
    var adonix_emplacement_code	: String = "",
    @ColumnInfo("suspicionPlomb")
    var suspicionPlomb	: String = "",
    @ColumnInfo("ville_agence")
    var ville_agence: String = "",
    @ColumnInfo("appRaccordBoucleECS")
    var appRaccordBoucleECS	: Boolean = false,
    @ColumnInfo("pasConserverPieces")
    var pasConserverPieces : Boolean = false,
    @ColumnInfo("encaissement")
    var encaissement : Boolean = false,
    @ColumnInfo("interventionType_libelle")
    var interventionType_libelle : String = "",
    @ColumnInfo("entree")
    var entree : String = "",
    @ColumnInfo("contact_nom")
    var contact_nom : String = "",
    @ColumnInfo("heureDebut")
    var heureDebut : Int = 0,
    @ColumnInfo("urgent")
    var urgent : String = "",
    @ColumnInfo("panneTotale")
    var panneTotale : String = "",
    var observationTechnicien : String = "",
    @ColumnInfo("ref_client_groupe")
    var ref_client_groupe : String = "",
    @ColumnInfo("marque_code_appareil")
    var marque_code_appareil: String = "",
    @ColumnInfo("messageAgence")
    var messageAgence   : String = "",
    @ColumnInfo("miseArretAppareil")
    var miseArretAppareil : Boolean = false,
    @ColumnInfo("observation")
    var observation : String = "",
    @ColumnInfo("societe_agence")
    var societe_agence : String = "",
    @ColumnInfo("observationCmdPieces")
    var observationCmdPieces : String = "",
    @ColumnInfo("cp")
    var cp: String = "-",
    @ColumnInfo("adresse_agence")
    var adresse_agence : String = "-",
    @ColumnInfo("reference_client")
    var reference_client : String = "",
    @ColumnInfo("contact_titre")
    var contact_titre	 : String = "",
    @ColumnInfo("login_tech")
    var login_tech	 : String = "",
    @ColumnInfo("date")
    var date : Int = 0,
    @ColumnInfo("tel_agence")
    var tel_agence : String = "",
    @ColumnInfo("resultat_code")
    var resultat_code : String = "",
    @ColumnInfo("telephone3")
    var telephone3	 : String = "",
    @ColumnInfo("telephone2")
    var telephone2 : String = "",
    @ColumnInfo("telephone1")
    var telephone1	:  String = "",
    @ColumnInfo("client_type")
    var client_type	: String = "",
    @ColumnInfo("envoi_email_gardien")
    var envoi_email_gardien	 : Boolean = false,
    @ColumnInfo("adresseComplementaire")
    var adresseComplementaire : String = "",
    @ColumnInfo("majOccupant")
    var majOccupant : Boolean = false,
    @ColumnInfo("periode_rdv")
    var periode_rdv : String = "",
    @ColumnInfo("interventionType_code")
    var interventionType_code : String = "",
    @ColumnInfo("client_adresse")
    var client_adresse : String = "",
    @ColumnInfo("o2 ")
    var o2	 : String = "",
    @ColumnInfo("reglementType")
    var reglementType : String = "",
    @ColumnInfo("facture_pieces_hc_posees")
    var facture_pieces_hc_posees : Boolean = false,
    @ColumnInfo("facture_devis_pour_non_conformite")
    var facture_devis_pour_non_conformite : Boolean = false,
    @ColumnInfo("contact_prenom")
    var contact_prenom : String = "",
    @ColumnInfo("heureFin")
    var heureFin : Int = 0,
    @ColumnInfo("coAmbiant")
    var coAmbiant : String = "",
    @ColumnInfo("date_refus_optin")
    var date_refus_optin : String = "",
    @ColumnInfo("position")
    var position : String = "",
    @ColumnInfo("commander_pieces")
    var commander_pieces : Boolean = false,
    @ColumnInfo("email_magasinier")
    var email_magasinier : String = "",
    @ColumnInfo("suspicionAmiante")
    var suspicionAmiante : String = "",
    @ColumnInfo("num_logement")
    var num_logement : String = "",
    @ColumnInfo("num_contrat")
    var num_contrat	 : String = "",
    @ColumnInfo("cp_agence")
    var cp_agence	 : String = "",
    @ColumnInfo("ref_cli_lg")
    var ref_cli_lg	 : String = "",
    @ColumnInfo("visitesRealisees")
    var visitesRealisees : String = "",
    @ColumnInfo("adonix_technicien_code")
    var adonix_technicien_code : String = "",
    @ColumnInfo("intervention_code")
    var intervention_code : String = "",
    @ColumnInfo("contact_email")
    var contact_email : String = "",
    @ColumnInfo("controle_vacuite")
    var controle_vacuite : Boolean = false,
    //
    @ColumnInfo("operations")
    @SerializedName("operations")
    @TypeConverters(OperationCiConverter::class)
    var operations : List<OperationCi>? = null,
    //
    @ColumnInfo("signatureTech")
    var signatureTech  : String = "",
    @ColumnInfo("email_DGI_secondaire")
    var email_DGI_secondaire  : String = "",
    @ColumnInfo("version_tablette")
    var version_tablette  : String = "",
    @ColumnInfo("scan2")
    var scan2 : String = "",
    @ColumnInfo("scan1")
    var scan1 : String = "",
    @ColumnInfo("nomTechnicien")
    var nomTechnicien	  : String = "",
    @ColumnInfo("poseDaafDaaco")
    var poseDaafDaaco  : String = "",
    @ColumnInfo("duree")
    var duree  : Int = 0,
    @ColumnInfo("fax_agence")
    var fax_agence  : String = "",
    @ColumnInfo("certificatRamonage")
    var certificatRamonage  : Boolean = false,
    @ColumnInfo("lieu_code")
    var lieu_code : String = "",
    @ColumnInfo("mode_transmission")
    var mode_transmission	 : String = "",
    @ColumnInfo("client_cp")
    var client_cp	 : String = "",
    @ColumnInfo("ville")
    var ville	 : String = "",
    @ColumnInfo("adonix_site_code")
    var adonix_site_code : String = "",
    @ColumnInfo("pannePartielle")
    var pannePartielle : Boolean = false,
    @ColumnInfo("controleVacuite")
    var controleVacuite : Boolean =  false,
    @ColumnInfo("client_ville")
    var client_ville : String = "",
    @ColumnInfo("matriculeTechnicien")
    var matriculeTechnicien	 : String = "",
    /*
    @ColumnInfo("piecesComptoir")
    @SerializedName("piecesComptoir")
    @TypeConverters(PieceCiConverter::class)
    var piecesComptoir : List<PieceCi>? = null,
    */

    @ColumnInfo("suspicionFlocage")
    var suspicionFlocage : String = "",
    @ColumnInfo("appareil_mesure")
    var appareil_mesure		 : String = "",
    @ColumnInfo("testDsc")
    var testDsc		 : String = "",
    @ColumnInfo("adresse")
    var adresse : String = "",
    @ColumnInfo("ventilationsEtat")
    var ventilationsEtat : String = "",
    @ColumnInfo("obs_locat")
    var obs_locat : String = "",
    @ColumnInfo("depression")
    var depression : String = "",
    @ColumnInfo("tablette_id")
    var tablette_id		 : String = "",
    @ColumnInfo("suspicionFauxPlafond")
    var suspicionFauxPlafond : String = "",
    @ColumnInfo("controleCircuitHydrau")
    var controleCircuitHydrau : Boolean = false,
    @ColumnInfo("signatureClient")
    var signatureClient	 : Boolean = false,
    @ColumnInfo("type_logement")
    var type_logement : String = "",
    @ColumnInfo("num_sa")
    var num_sa : String = "",
)