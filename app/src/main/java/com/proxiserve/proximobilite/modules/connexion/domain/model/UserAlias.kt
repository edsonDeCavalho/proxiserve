package com.proxiserve.proximobilite.modules.connexion.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import kotlinx.coroutines.flow.MutableStateFlow
import javax.annotation.Nonnull

/**
 * Created by dloriot on 27/06/2024.
 * Lorsqu'un Admin ou un CI se connecte à un profile via le mode Admin,
 * Ce model utilisé est un UserAlias
 */

@Entity
data class UserAlias(
    @PrimaryKey
    @SerializedName("Id")
    val id: String = "",
//    @SerializedName("") // manquant
    val matricule: String = "",
    @SerializedName("SR_LoginMobilite__c")
    val login: String = "",
    @SerializedName("SR_Nom__c")
    val nom: String = "",
    @SerializedName("SR_Prenom__c")
    val prenom: String = "",
//    @SerializedName("")
    val idAdonix: String = "", // manquant
//    @SerializedName("")
    val mailAgence: String = "dloriot@proxiserve.fr", // manquant pour message agence
//    @SerializedName("SR_TitreJob__c")
    val groupe: String = UserGroup.GROUP_TECHNICIEN.type,
)

class InvalidUserAliasException(message: String) : Exception(message)
