package com.proxiserve.proximobilite.modules.connexion.presentation.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by dloriot on 02/07/2024.
 */

class Prefs {
    companion object Prefs {

        const val DEFAULT_PREFS = "com.proxiserve.proximobilite.PREFERENCE_FILE_KEY"
        const val THEME_IS_DARK_KEY = "com.proxiserve.proximobilite.PREFERENCE_FILE_THEME_KEY"

        fun getInstance(context: Context): SharedPreferences {
            return context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE)
        }


        fun getThemePref(context: Context): Boolean {
            return getInstance(context).getBoolean(THEME_IS_DARK_KEY, true)
        }

        fun setThemePref(context: Context, isDark: Boolean) {
            val sharedPref = getInstance(context) ?: return
            with(sharedPref.edit()) {
                putBoolean(THEME_IS_DARK_KEY, isDark)
                apply()
            }

        }


    }
}