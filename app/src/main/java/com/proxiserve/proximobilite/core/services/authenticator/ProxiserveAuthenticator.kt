package com.proxiserve.proximobilite.core.services.authenticator

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import com.proxiserve.proximobilite.MainActivity
import timber.log.Timber

/**
 * Created by dloriot on 12/07/2024.
 */
class ProxiserveAuthenticator(val context: Context) : AbstractAccountAuthenticator(context ){


    private val TAG = "[ProxiserveAuth]"
    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ) : Bundle? {
        Timber.i("$TAG addAccount()")
        return  null
//        val bundle = Bundle()
//        bundle.putParcelable(
//            AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
//            response
//        )
//
//        return bundle
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle =
        // SI on a un compte on a un token
        AccountManager.get(context).let { accountManager ->
            Timber.i("$TAG getAuthToken()")
            Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account?.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
                putString(
                    AccountManager.KEY_AUTHTOKEN,
                    accountManager.peekAuthToken(account, authTokenType)
                )
            }
        }

    override fun editProperties(p0: AccountAuthenticatorResponse?, p1: String?): Bundle {
        throw UnsupportedOperationException()
    }

    override fun confirmCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Bundle?
    ): Bundle {
        throw UnsupportedOperationException()
    }

    override fun getAuthTokenLabel(p0: String?): String {
        throw UnsupportedOperationException()
    }

    override fun updateCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: String?,
        p3: Bundle?
    ): Bundle {
        throw UnsupportedOperationException()
    }

    override fun hasFeatures(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Array<out String>?
    ): Bundle {
        throw UnsupportedOperationException()
    }
}