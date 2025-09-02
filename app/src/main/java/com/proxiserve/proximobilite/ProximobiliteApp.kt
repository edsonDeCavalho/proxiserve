package com.proxiserve.proximobilite

import android.app.Application
import androidx.compose.runtime.Applier
import com.okta.authfoundation.AuthFoundationDefaults
import com.okta.authfoundation.client.OidcClient
import com.okta.authfoundation.client.OidcConfiguration
import com.okta.authfoundation.client.SharedPreferencesCache
import com.okta.authfoundation.credential.CredentialDataSource.Companion.createCredentialDataSource
import com.okta.authfoundationbootstrap.CredentialBootstrap
import dagger.hilt.android.HiltAndroidApp
import okhttp3.HttpUrl.Companion.toHttpUrl
import timber.log.Timber

/**
 * Created by dloriot on 27/06/2024.
 */

@HiltAndroidApp
class ProximobiliteApp: Application() {

    override fun onCreate() {
        super.onCreate()

//        Timber.plant(Timber.DebugTree())

        Timber.plant(object : Timber.DebugTree() {

            override fun log(
                priority: Int, tag: String?, message: String, t: Throwable?
            ) {
                super.log(priority, "[PROXIAPP]$tag", message, t)
            }
        })

        // Initializes Auth Foundation and Credential Bootstrap classes.
        AuthFoundationDefaults.cache = SharedPreferencesCache.create(this)
        val oidcConfiguration = OidcConfiguration(
            clientId = BuildConfig.CLIENT_ID,
            defaultScope = "openid groups email profile offline_access",
        )
        val client = OidcClient.createFromDiscoveryUrl(
            oidcConfiguration,
            BuildConfig.DISCOVERY_URL.toHttpUrl(),
        )
        CredentialBootstrap.initialize(client.createCredentialDataSource(this))
    }
}