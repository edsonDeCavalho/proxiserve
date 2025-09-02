package com.proxiserve.proximobilite.modules.connexion.presentation.state

import com.okta.authfoundation.claims.name
import com.okta.authfoundationbootstrap.CredentialBootstrap
import timber.log.Timber

/**
 * Created by dloriot on 01/07/2024.
 *
 * Connexion Okta / Android
 */
sealed class ConnexionState {

    data object Loading : ConnexionState()

    class LoggedOut(val errorMessage: String? = null) : ConnexionState()

    class LoggedIn private constructor(
        val name: String,
        val errorMessage: String?
    ) : ConnexionState() {

        companion object {
            /**
             * Creates the [LoggedIn] state using the [CredentialBootstrap.defaultCredential]s ID Token name claim.
             */
            suspend fun create(errorMessage: String? = null): ConnexionState {
                val credential = CredentialBootstrap.defaultCredential()
                val name = credential.idToken()?.name ?: ""
                return LoggedIn(name, errorMessage)
            }
        }

    }

    companion object {
        /**
         * Creates the [ConnexionState] given the [CredentialBootstrap.defaultCredential]s presence of a token.
         *
         * @return Either [LoggedIn] or [LoggedOut].
         */
        suspend fun currentCredentialState(errorMessage: String? = null): ConnexionState {

            val credential = CredentialBootstrap.defaultCredential()
//            Timber.i("[ConnexionState] currentCredentialState => %s", credential.token.toString())
            return if (credential.token == null) {
                Timber.i("[ConnexionState] currentCredentialState => token KO")
                LoggedOut(errorMessage)
            } else {
                Timber.i("[ConnexionState] currentCredentialState => token OK")
                LoggedIn.create(errorMessage)
            }
        }
    }
}
