package com.proxiserve.proximobilite.core.services.authenticator

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by dloriot on 12/07/2024.
 */
class AuthenticatorService : Service() {

    val authenticator = ProxiserveAuthenticator(this)

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder

}