package com.proxiserve.proximobilite.feature_modules.connexion.data.remote

/**
 * Created by dloriot on 28/06/2024.
 */
sealed class ConnexionResult<T>(
    val data: T? = null
) {
    class Authorized<T>(data: T? = null): ConnexionResult<T>(data)
    class Unauthorized<T>: ConnexionResult<T>()
    class UnknownError<T>: ConnexionResult<T>()
}