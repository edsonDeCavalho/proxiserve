package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils

/**
 * Created by dloriot on 04/09/2024.
 */
object FdrUtils {

    const val MATIN_FLAG = "M"
    const val MATIN = "Matin"
    const val APRES_MIDI_FLAG = "A"
    const val APRES_MIDI = "Après-Midi"

    fun getPeriodeRdv(period: String): String {
        if (MATIN_FLAG == period) return MATIN
        if (APRES_MIDI_FLAG == period) return APRES_MIDI

        return "Erreur"
    }
}