package com.proxiserve.proximobilite.modules.intervention.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entité Appareil
 * @author Edson De Carvalho
 */
@Entity
data class AppareilCi(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String ="0",
    @ColumnInfo("bruleurPuissance")
    var bruleurPuissance: String ="",
    @ColumnInfo("typeReseau")
    var typeReseau: String="",
    @ColumnInfo("id_appareil")
    var idAppareil: String="",
    @ColumnInfo("libelle")
    var libelle: String="",
    @ColumnInfo("marque_code")
    var marque_code	: String="",
    @ColumnInfo("thermostatNbRobinets_code")
    var thermostatNbRobinets_code: String="",
    @ColumnInfo("anneeFabrication")
    var anneeFabrication	: String="",
    @ColumnInfo("chauffageEvacuationType_code")
    var chauffageEvacuationType_code	: String,
    @ColumnInfo("dateFinGarantie")
    var dateFinGarantie: Int = 0,
    @ColumnInfo("installe_par")
    var installe_par: String ="",
    @ColumnInfo("boucheVmcInterrupteurDsc")
    var boucheVmcInterrupteurDsc: Boolean=false,
    @ColumnInfo("modele")
    var modele: String ="",
    @ColumnInfo("sous_appareil")
    var sous_appareil: String="",
    @ColumnInfo("thermostatSondeExterieure")
    var thermostatSondeExterieure: Boolean=false,
    @ColumnInfo("quantite")
    var quantite: Int=0,
    @ColumnInfo("dureeGarantie")
    var dureeGarantie: String = "",
    @ColumnInfo("bruleurMarque_code")
    var bruleurMarqueCode: String="",
    @ColumnInfo("boucheVmcSalleDeBain")
    var boucheVmcSalleDeBain: String="",
    @ColumnInfo("boucheVmcCuisine")
    var boucheVmcCuisine: String="",
    @ColumnInfo("valeurRefEmissionPolluants")
    var valeurRefEmissionPolluants: String="",
    @ColumnInfo("evalEmissionCov")
    var evalEmissionCov: String="",
    @ColumnInfo("boucheVmcAutre")
    var boucheVmcAutre: String="",
    @ColumnInfo("mater_canalisation")
    var mater_canalisation : String="",
    @ColumnInfo("reseauRadio")
    var reseauRadio: String="",
    @ColumnInfo("thermostatRobinets")
    var thermostatRobinets: Boolean=false,
    @ColumnInfo("boucheVmcUniteMesureCode")
    var boucheVmcUniteMesureCode: String = "",
    @ColumnInfo("chauffagePuissanceNominale")
    var chauffagePuissanceNominale: String = "",
    @ColumnInfo("numeroSerie")
    var numeroSerie: String = "",


    @ColumnInfo("boucheVmcUniteMesure_code")
    var boucheVmcUniteMesure_code: String = "",
    @ColumnInfo("boucheVmcNbEntreeAir_code")
    var boucheVmcNbEntreeAir_code: String = "",
    @ColumnInfo("chauffageEmissionOxydeAzote_code")
    var chauffageEmissionOxydeAzote_code: String ="",
    @ColumnInfo("dateMiseEnService")
    var dateMiseEnService: Int = 0,
    @ColumnInfo("bruleurType_code")
    var bruleurType_code: String ="",
    @ColumnInfo("raccordGaziniere")
    var raccordGaziniere: String ="",
    @ColumnInfo("bruleurDateMiseEnService")
    var bruleurDateMiseEnService: Int =0,
    @ColumnInfo("bruleurModele")
    var bruleurModele: String ="",


    @ColumnInfo("date_flexible_gaz")
    var date_flexible_gaz: String ="",
    @ColumnInfo("valeurRefEmissionPoussieres")
    var valeurRefEmissionPoussieres	: String = "",
    @ColumnInfo("aboutCode")
    var aboutCode: String = "",
    @ColumnInfo("sous_contrat")
    var sous_contrat: Boolean = false,
    @ColumnInfo("flexibleGaz")
    var flexibleGaz: Boolean = false,
    @ColumnInfo("idRadio")
    var idRadio: String = "",
    @ColumnInfo("energieCode")
    var energie_code: String = "",
    @ColumnInfo("evalEmissionPoussieres")
    var evalEmissionPoussieres: String = "",
    @ColumnInfo("thermostatHorloge")
    var thermostatHorloge: Boolean = false,
   // @ColumnInfo("anomalies")
   // var anomalies: MutableList<String> = mutableListOf(),
    @ColumnInfo("combustible")
    var combustible: String = "",
    @ColumnInfo("valeurRefRendement")
    var valeurRefRendement: String = "",
    @ColumnInfo("boucheVmcWC")
    var boucheVmcWC: String = "",
    @ColumnInfo("classeEnergetique")
    var classeEnergetique: String = "",
    @ColumnInfo("depositPar")
    var depositPar: String,
    @ColumnInfo("chauffageChaudiereRendementCode")
    var chauffageChaudiereRendementCode: String = "",
    @ColumnInfo("valeurRefEmissionCov")
    var valeurRefEmissionCov: String = "",
    @ColumnInfo("duree_illimite")
    var duree_illimite: Boolean = false,
    @ColumnInfo("thermostatAmbiance")
    var thermostatAmbiance: Boolean = false,
    @ColumnInfo("chauffageChaudiereType_code")
    var chauffageChaudiereType_code	: String
){

}