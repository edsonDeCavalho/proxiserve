package com.proxiserve.proximobilite.feature_modules.connexion.domain.repository

import android.content.Context
import com.proxiserve.proximobilite.feature_modules.connexion.data.remote.ConnexionResult

/**
 * Created by dloriot on 28/06/2024.
 *
 */
interface ConnexionRepository {

    suspend fun logIn(username: String): ConnexionResult<Unit>
    suspend fun logOut(username: String): ConnexionResult<Unit>
    suspend fun authenticate(): ConnexionResult<Unit>
}