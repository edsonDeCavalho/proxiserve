package com.proxiserve.proximobilite

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by dloriot on 19/11/2024.
 * Cette classe de test fournie par Android permet de faciliter l'exécution des tests.
 * Lorsqu'on utilise de la DI comme Hilt, elle permet d'intégrér Hilt dans l'environnement de test.
 * Penser à la déclarer dans le Build !
 */

class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}