package com.proxiserve.proximobilite.feature_modules.connexion.data.repository

import android.content.SharedPreferences
import com.proxiserve.proximobilite.feature_modules.connexion.data.remote.ConnexionApi
import com.proxiserve.proximobilite.feature_modules.connexion.data.remote.ConnexionRequest
import com.proxiserve.proximobilite.feature_modules.connexion.data.remote.ConnexionResult
import com.proxiserve.proximobilite.feature_modules.connexion.domain.repository.ConnexionRepository
import retrofit2.HttpException

/**
 * Created by dloriot on 28/06/2024.
 */
class ConnexionRepositoryImpl(
    private val api: ConnexionApi,
    private val prefs: SharedPreferences
): ConnexionRepository {

    override suspend fun logIn(username: String): ConnexionResult<Unit> {
        return try {
            val response = api.logIn(
                request = ConnexionRequest(
                    username = username
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .apply()
            ConnexionResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                ConnexionResult.Unauthorized()
            } else {
                ConnexionResult.UnknownError()
            }
        } catch (e: Exception) {
            ConnexionResult.UnknownError()
        }
    }

    override suspend fun logOut(username: String): ConnexionResult<Unit> {
        return try {
            api.logOut(
                request = ConnexionRequest(
                    username = username
                )
            )
            logOut(username)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                ConnexionResult.Unauthorized()
            } else {
                ConnexionResult.UnknownError()
            }
        } catch (e: Exception) {
            ConnexionResult.UnknownError()
        }
    }

    override suspend fun authenticate(): ConnexionResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return ConnexionResult.Unauthorized()
            api.authenticate("Bearer $token")
            ConnexionResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                ConnexionResult.Unauthorized()
            } else {
                ConnexionResult.UnknownError()
            }
        } catch (e: Exception) {
            ConnexionResult.UnknownError()
        }
    }

}