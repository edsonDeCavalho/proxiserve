package com.proxiserve.proximobilite.core.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.room.PrimaryKey
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.ui.theme.Orange_proxi
import com.proxiserve.proximobilite.ui.theme.Rouge_proxi_900
import com.proxiserve.proximobilite.ui.theme.Vert_valide_proxi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.annotation.Nonnull

/**
 * Created by dloriot on 15/07/2024.
 */
object FakeUtils {

    private const val TAG = "FakeUtils"

    fun getAlphaNumRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getNumRandomString(length: Int) : String {
        val allowedChars = ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getAlphaRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getFakeUserAlias() : UserAlias {
        Timber.w("$TAG getFakeUserAlias returning UserAlias")
        return UserAlias(
            id = "0HnSa0000000HN45P8",
            matricule = "10008823",
            login = "ddurand",
            nom = "Durand",
            prenom = "David",
            idAdonix = getAlphaRandomString(1) + getNumRandomString(4),
            mailAgence = "fake-agence@proxiserve.fr",
            groupe = "TEST_PROXIMOB_Technicien"
        )
    }

    /**
     * Récupère le json d'une fausse feuille de route dans les assets
     * @return: le json de la feuille de route
     * */
    fun getFakeFeuilleDeRoute(@ApplicationContext context: Context) : String? {
        Timber.w("$TAG getFakeFeuilleDeRoute")
        val jsonFdr: String

        try {
            jsonFdr = context.assets.open("fake_fdr.json").bufferedReader().use {
                it.readText()
            }
        } catch (io: IOException) {
            io.printStackTrace()
            return null
        }

        return jsonFdr
    }

    // Statut SMS feuille de route
    fun getSmsStatus(smsRandom: Int): String {
        return listOf("SMS Envoyé", "SMS Manquant", "SMS Impossible")[smsRandom]
    }

    fun getSmsStatusColor(smsRandom: Int): Color {
        return listOf(Vert_valide_proxi, Orange_proxi, Rouge_proxi_900)[smsRandom]
    }
}
