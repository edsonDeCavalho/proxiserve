package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

/**
 * Created by dloriot on 23/08/2024.
 */
@Entity
data class Operation(
    @PrimaryKey
    @Nonnull
    var id: Int,

    var libelle: String = "",

    var metier: String = "",

    var prestation: String = "",

    var client: String = "",

    var type: String = "",

    var obligatoire: Boolean = false,

    var categorie: String = "", // ORN - OSRA

    var condition: String = "", // Vmc gaz - Condens - Vmc double-flux - ...

    var autoSelected: Boolean = false
)
