package com.proxiserve.proximobilite.modules.connexion.presentation

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.okta.authfoundation.claims.email
import com.okta.authfoundation.claims.name
import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.okta.webauthenticationui.WebAuthenticationClient.Companion.createWebAuthenticationClient
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.core.utils.AuthenticatorConst
import com.proxiserve.proximobilite.core.utils.FakeUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import java.security.PrivateKey
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by dloriot on 01/07/2024.
 */

@HiltViewModel
class ConnexionViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    userUseCases: UserUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases
) : BaseViewModel(
    userUseCases = userUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {

    val TAG = "[ConnexionViewModel]"

//    private var saveUserJob: Job? = null


//    fun onEvent(event: UserEvent) {
//        when(event) {
//            is UserEvent.GetUser -> {
//                Timber.i("$TAG onEvent() GetUser")
//                userUseCases.getUser
//            }
//            is UserEvent.SaveUser -> {
//                userUseCases.insertUser
//            }
//
//        }
//    }


}

//val ConnexionUserState = compositionLocalOf<ConnexionViewModel> { error("ConnexionUserState context introuvable")}