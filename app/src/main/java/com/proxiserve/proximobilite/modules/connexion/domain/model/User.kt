package com.proxiserve.proximobilite.modules.connexion.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nonnull

/**
 * Created by dloriot on 27/06/2024.
 *
 */

@Entity
data class User(
    @PrimaryKey
    @SerializedName("Id")
    var id: String = "",
    @SerializedName("SR_MatriculeRH__c")
    var matricule: String = "",
//    @SerializedName("SR_LoginMobilite__c")
    var login: String = "",
    @SerializedName("SR_Nom__c")
    val nom: String = "",
    @SerializedName("SR_Prenom__c")
    val prenom: String = "",
//    @SerializedName("SR_CodeTechnique__c")
    var idAdonix: String = "",
//    @SerializedName("")
    val mailAgence: String = "dloriot@proxiserve.fr", // manquant
//    @SerializedName("")
//    val mailDGI: String = "", // Pas besoin, event dans Orion qui envoie mail
//    @SerializedName("id_technicien")
//    val mailMagasinier: String = "", // Pas besoin, event dans Orion qui envoie mail
    @SerializedName("SR_TitreJob__c")
    var groupe: String = "",
)

class InvalidUserException(message: String) : Exception(message)
