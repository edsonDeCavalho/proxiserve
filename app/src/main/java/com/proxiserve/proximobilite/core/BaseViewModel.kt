package com.proxiserve.proximobilite.core

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.okta.authfoundation.claims.email
import com.okta.authfoundation.claims.name
import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.okta.webauthenticationui.WebAuthenticationClient.Companion.createWebAuthenticationClient
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.R
import com.proxiserve.proximobilite.core.utils.AuthenticatorConst
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.OperationUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.PieceUseCases
import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.CertificatInterventionUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.PrestationUseCases
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.PhotoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by dloriot on 09/08/2024.
 */
@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val certificationInterventionUseCases: CertificatInterventionUseCases? = null,
    private val userAliasUseCases: UserAliasUseCases? = null,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases,
    private val interventionUseCases: InterventionUseCases? = null,
    private val historiqueUseCases: HistoriqueUseCases? = null,
    private val appareilUseCases: AppareilUseCases? = null,
    private val operationUseCases: OperationUseCases? = null,
    private val pieceUseCases: PieceUseCases? = null,
    private val prestationUseCases: PrestationUseCases? = null,
    private val photoUseCases: PhotoUseCases? = null,

    ): ViewModel() {

    private val TAG = "[BaseViewModel]"

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    private val _currentUser = MutableStateFlow(User())
    val currentUser: StateFlow<User> = _currentUser.asStateFlow()

//    var currentUser by mutableStateOf<User?>(null)
    val gson = Gson()

    suspend fun getToken(): String? {
        return CredentialBootstrap.defaultCredential().getValidAccessToken()
    }

    fun updateUserState(userState: UserState, from: String) {
        Timber.i("$TAG[DEBUG_RECOMPOSE] from %s updateUserState %s", from, gson.toJson(userState))
        _userState.value = userState
    }

    private fun updateCurrentUser(user: User) {
        Timber.i("$TAG[DEBUG_RECOMPOSE] from %s updateCurrentUser", gson.toJson(user))
        _currentUser.value = user
    }

    suspend fun getUser(): User? {
        Timber.i("$TAG get user")
        return userUseCases.getUser()
    }

    suspend fun getUserById(id: String): User? {
        Timber.i("$TAG get user")
        return userUseCases.getUserById(id = id)
    }

    suspend fun getCurrentIntervention(id: String): Intervention? {
        Timber.i("$TAG getCurrentIntervention $id")
       return interventionUseCases?.getInterventionById?.let { it(id) }
    }

    suspend fun getNextIntervention(userId: String): Intervention? {
        Timber.i("$TAG getNextIntervention with userId => $userId")

        val accessToken = CredentialBootstrap.defaultCredential().getValidAccessToken()

        accessToken?.let {
            val response: GetFeuilleDeRouteResponse =
                feuilleDeRouteUseCases.getRemoteFeuilleDeRoute(id = userId, token = accessToken)
//            Timber.w("$TAG getNextIntervention response => %s", gson.toJson(response))
            when (response) {
                is GetFeuilleDeRouteResponse.Success -> {
                    val feuilleDeRoute: FeuilleDeRoute? = response.response.body()
                    feuilleDeRoute?.let {
                        return feuilleDeRoute.interventions.get(0)
                    }
                }

                is GetFeuilleDeRouteResponse.Fail -> {
                    Timber.e("$TAG getNextIntervention => Fail")
                }

                is GetFeuilleDeRouteResponse.Error -> {
                    Timber.e("$TAG getNextIntervention => Error")
                }

            }

        }?:run {
            Timber.e("$TAG getNextIntervention => Token null")
        }
        return null
    }

    suspend fun login(context: Context) {
        Timber.i("$TAG login()")
//        Sentry.captureMessage("login() called with: context", SentryLevel.INFO)
        viewModelScope.launch {

            val result = CredentialBootstrap.oidcClient.createWebAuthenticationClient().login(
                context = context,
                redirectUrl = BuildConfig.SIGN_IN_REDIRECT_URI,
            )


            when (result) {

                is OidcClientResult.Error -> {
                    Timber.e(result.exception, "Failed to login.")
                    // sentry
//                    Sentry.captureException(result.exception, Hint())
//                    _state.value = ConnexionState.currentCredentialState("Failed to login.")
//                    _userState.value = UserState(
                    updateUserState(UserState(
                        username = "",
                        userId = "",
                        userGroup = "",
                        isLoggedIn = false,
                        user = null,
                        errorMessage = ConnexionConst.CONNECT_FAIL
                    ), "OidcClientResult.Error")
                }

                is OidcClientResult.Success -> {
                    Timber.w("$TAG[LOGIN] Success : %s", gson.toJson(result))
//                    Sentry.captureMessage(
//                        "Login Success : " + gson.toJson(result),
//                        SentryLevel.DEBUG
//                    )
                    val credential = CredentialBootstrap.defaultCredential()
                    credential.storeToken(token = result.result)

                    // Récupération user infos
                    val accessToken = result.result.accessToken
                    var userId = ""
                    var groups = emptyList<String>()

                    try {
                        val userInfo = getUserInfo(accessToken)
                        userId = userInfo["userId"] as? String ?: "id"
                        groups = userInfo["groups"] as? List<String> ?: emptyList()

                        Timber.w("$TAG[LOGIN] User infos : %s", gson.toJson(userInfo))
                    } catch (e: Exception) {
                        Timber.e("$TAG: %s", e.stackTrace)
                    }


                    if (credential.idToken()?.name?.isNotBlank() == true) {
                        var formatedName = ""
                        if (credential.idToken()?.name?.contains(",") == false)
                            formatedName = credential.idToken()?.name?.replace(" ", ", ").toString()

                        val nomUser = formatedName.split(",").get(1) ?: ""
                        val prenomUser = formatedName.split(",").get(0).trim() ?: ""
                        val mailUser = credential.idToken()?.email ?: ""
                        val loginUser = credential.idToken()?.email?.split("@")?.get(0) ?: ""
                        val groupUser: String = groups[1]

                        // création du User
                        if (groupUser == UserGroup.GROUP_ADMIN.type || groupUser == UserGroup.GROUP_CI.type) {
                            val adminUser = User(
                                id = userId,
                                nom = nomUser,
                                prenom = prenomUser,
                                login = loginUser,
                                groupe = groupUser
                            )
                            getAdminUserSuccessHandler(
                                context = context,
                                adminUser = adminUser,
                                accessToken = accessToken
                            )

                        } else {
                            val getUserResponse: GetUserResponse =
          //                      userUseCases.getRemoteUserByLogin(login = mailUser, token = accessToken)
                                userUseCases.getRemoteUser(id = userId, token = accessToken)

                            when (getUserResponse) {
                                is GetUserResponse.Success -> {
                                    getUserSuccessHandler(
                                        response = getUserResponse.userResponse,
                                        context = context,
                                        accessToken = accessToken,
                                        userMail = mailUser,
                                        group = groups[1]
                                    )
                                }

                                is GetUserResponse.Fail -> {
                                    getUserFailHandler(response = getUserResponse.error)
                                }

                                is GetUserResponse.Error -> {
                                    getUserErrorHandler(
                                        exception = getUserResponse.exception,
                                        message = getUserResponse.message
                                    )
                                }

                                else -> { TODO() }
                            }
                        }
                    }
                }
            }
        }
    }
    /*
     fun insertCertificatIntervention(certificatIntervention: CertificatIntervention){
        certificationInterventionUseCases.insertCertificatIntervention(certificatIntervention)
     }
     */
    private suspend fun getAdminUserSuccessHandler(
        context: Context,
        adminUser: User,
        accessToken: String
    ) {
        Timber.w("$TAG[LOGIN] getAdminUserSuccessHandler")
        updateCurrentUser(adminUser)
        userUseCases.deleteUser()
        userUseCases.insertUser(currentUser.value)

        // Attention au placement de l'update du state, toujours après l'insert
        updateUserState(
            UserState(
                username = "${adminUser.prenom} ${adminUser.nom}",
                userId = adminUser.id,
                userGroup = adminUser.groupe,
                isLoggedIn = true,
                user = adminUser
            ), "getAdminUserSuccessHandler"
        )
        // Enregistrement du compte dans AccountManager
        setAccount(context = context, accessToken = accessToken, userMail = adminUser.mailAgence)

        Timber.w("$TAG[LOGIN] UserState : account added !")
    }

    private suspend fun getUserSuccessHandler(
        response: Response<User>, context: Context, accessToken: String, userMail: String, group: String
    ) {
        Timber.w("$TAG[LOGIN] getUserSuccessHandler => %s", gson.toJson(response.body()))

        val user: User? = response.body()
        Timber.w(
            "$TAG[LOGIN] getUserSuccessHandler =>\n id = %s\n matricule = %s\n idAdonix = %s",
            user?.id, user?.matricule, user?.idAdonix
        )

        user?.let {
            it.groupe = group
            updateCurrentUser(it)
            userUseCases.deleteUser()
            userUseCases.insertUser(currentUser.value)

            Timber.w("$TAG[LOGIN] User : user saved => %s", gson.toJson(currentUser))

            // Mise à jour du state
            updateUserState(
                UserState(
                    username = currentUser.value.login,
                    userId = currentUser.value.id,
                    userGroup = currentUser.value.groupe,
                    isLoggedIn = true,
                    user = user
                ), "getUserSuccessHandler"
            )
            Timber.w("$TAG[LOGIN] UserState : %s", gson.toJson(userState))

            // Enregistrement du compte dans AccountManager
            setAccount(context = context, accessToken = accessToken, userMail = userMail)
        }?: run {
            Timber.e("$TAG User null")
        }
    }

    private fun getUserFailHandler(response: String?) {
        Timber.e("$TAG[LOGIN] getUserFailHandler : $response")
//        var responseError: ResponseError? = null

//        try {
//            responseError = gson.fromJson(response, ResponseError::class.java)
//        } catch (e: Exception) {
//
//        }
//        val errorMessage = responseError?.message?: ""
//        Timber.e("$TAG[LOGIN] Remote User Error 401 message => %s", response)

        // Prepa: on set le userState errorMessage pour faire aparaitre le message et on retourne sur Login

        updateUserState(UserState(
            username = "",
            userId = "",
            userGroup = "",
            isLoggedIn = false,
            user = null,
            errorMessage = response
        ), "getUserFailHandler")

    }

    private fun getUserErrorHandler(exception: Exception, message: String) {
        Timber.e("$TAG[LOGIN] Remote User else")
        Timber.e("$TAG[LOGIN] Remote User message => %s", message)

        when(exception) {
            is SocketTimeoutException -> {
                Timber.e(exception)
            }
            is IOException -> {
                Timber.e(exception)
            }
            else -> {
                Timber.e(exception)
            }
        }

        updateUserState(UserState(
            username = "",
            userId = "",
            userGroup = "",
            isLoggedIn = false,
            errorMessage = message
        ), "getUserErrorHandler")
    }

    private fun setAccount(context: Context, accessToken: String, userMail: String) {

        val accountUser: String = if (userState.value.username.isBlank())
            AuthenticatorConst.DEFAULT_ACCOUNT else userMail
        val accountManager = AccountManager.get(context)
        val account =
            Account(accountUser, context.getString(R.string.account_type))
        accountManager.addAccountExplicitly(account, null, null)
        accountManager.setAuthToken(
            account,
            AuthenticatorConst.TOKEN_TYPE_FULL,
            accessToken
        )
        Timber.w("$TAG[LOGIN] setAccount : account added !")
    }

    /**
     * Afin de pouvoir récupérer le groups claim dans l'accessToken sans avoir à accéder
     * à l'option payante API Access Managment, on demande les user_infos via une requête
     * POST dans laquelle on fournit l'accessToken fraîchement acquis
     * */
    suspend fun getUserInfo(accessToken: String): Map<String, Any> {
        Timber.w("[LOGIN] Calling getUserInfo")
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BuildConfig.USER_INFOS_URI)
//            .url("https://proxiserve.okta.com/oauth2/v1/userinfo")
            .header("Authorization", "Bearer $accessToken")
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                throw Exception("Failed to fetch user infos !")
            }

            val responseBody = response.body?.string() ?: throw Exception("Empty response body !")
            val userInfos = JSONObject(responseBody)
            userInfos.toMap()

        }
    }

    fun JSONObject.toMap(): Map<String, Any> {
        Timber.w("[LOGIN] Calling toMap()")
        val map = mutableMapOf<String, Any>()
        val keys = this.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            var value = this.get(key)
            if (value is JSONArray) {
                value = value.toList()
            } else if (value is JSONObject) {
                value = value.toMap()
            }
            map[key] = value
        }
        return map
    }

    fun JSONArray.toList(): List<Any> {
        Timber.w("[LOGIN] Calling toList()")
        val list = mutableListOf<Any>()
        for (i in 0 until this.length()) {
            var value = this.get(i)
            if (value is JSONArray) {
                value = value.toList()
            } else if (value is JSONObject) {
                value = value.toMap()
            }
            list.add(value)
        }
        return list
    }

    fun logout(context: Context, navController: NavController) {
        Timber.i("$TAG login()")
        viewModelScope.launch {
//            _state.value = ConnexionState.Loading

//            val result = CredentialBootstrap.oidcClient.createWebAuthenticationClient().logoutOfBrowser(
            val result = CredentialBootstrap.oidcClient.createWebAuthenticationClient().logoutOfBrowser(
                context = context,
                redirectUrl = BuildConfig.SIGN_OUT_REDIRECT_URI,
                idToken = CredentialBootstrap.defaultCredential().token?.idToken ?: "",
            )
            when (result) {
                is OidcClientResult.Error -> {
                    Timber.i("$TAG Error")
                    Timber.e(result.exception, "Failed to logout.")
//                    _state.value = ConnexionState.currentCredentialState("Failed to logout.")
//                    _userState.value = UserState(errorMessage = ConnexionConst.CONNECT_FAIL)
                    updateUserState(UserState(errorMessage = ConnexionConst.CONNECT_FAIL), "logout/OidcClientResult.Error")
                }
                is OidcClientResult.Success -> {
                    Timber.i("$TAG Success")
                    CredentialBootstrap.defaultCredential().delete()
//                    _state.value = ConnexionState.LoggedOut()
//                    _userState.value = UserState(

                    updateUserState(UserState(
                        username = "",
                        userId = "",
                        userGroup = "",
                        isLoggedIn = false,
                        errorMessage = ConnexionConst.DISCONNECTED
                    ), "logout/OidcClientResult.Success")

//
                    navController.navigate(route = Routes.Login.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

}

data class ResponseError(
    val error: String = "",
    val code: String = "",
    val message: String = ""
)