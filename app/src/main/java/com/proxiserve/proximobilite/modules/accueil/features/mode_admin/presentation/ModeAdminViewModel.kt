package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation


import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.core.services.authenticator.ProxiserveAuthenticator
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.ModeAdminUseCases
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import retrofit2.Response
import android.content.Context
import androidx.navigation.NavController
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes

/**
 * Created by dloriot on 14/07/2024.
 */
@HiltViewModel
class ModeAdminViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val userUseCases: UserUseCases,
    private val userAliasUseCases: UserAliasUseCases,
    private val interventionUseCases: InterventionUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases,
    private val modeAdminUseCases: ModeAdminUseCases
) : BaseViewModel(
    userUseCases = userUseCases,
    userAliasUseCases = userAliasUseCases,
    interventionUseCases = interventionUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {

    val TAG = "[ModeAdminViewModel]"

    private val _maState = MutableStateFlow<ModeAdminState>(ModeAdminState.Initial) // penser à préciser le state initial entre ()
    val maState = _maState.asStateFlow()

    private val _userAlias = MutableStateFlow<UserAlias?>(null)
    val userAlias = _userAlias.asStateFlow()

    suspend fun onEvent(event: ModeAdminEvent) {
        Timber.i("$TAG onEvent")
        when(event) {

            is ModeAdminEvent.SelectedEnvironnement -> {}

            is ModeAdminEvent.EnteredLogin -> {
                Timber.i("$TAG onEvent EnteredLogin name %s, mode %s", event.login, event.mode)
                viewModelScope.launch {
                    _maState.value = ModeAdminState.Loading
                    // je teste si j'ai déja mon alias dans la bdd
                    val loginAliasResult = modeAdminUseCases.loginAlias(event.login)

                    // si je n'ai pas d'alias en base, je vais le chercher en remote
                    if (loginAliasResult == null) {

                        val token = CredentialBootstrap.defaultCredential().getValidAccessToken()

                        token?.let {

                            val loginRemoteAliasResult: GetUserAliasResponse =
                                modeAdminUseCases.loginRemoteAlias(login = event.login, token = token)

                            when(loginRemoteAliasResult) {

                                is GetUserAliasResponse.Success -> {
                                    getUserSuccessHandler(response = loginRemoteAliasResult.userAliasResponse)

                                }

                                is GetUserAliasResponse.Fail -> {
                                    getUserFailHandler(response = loginRemoteAliasResult.error)
                                }

                                is GetUserAliasResponse.Error -> {
                                    getUserErrorHandler(
                                        exception = loginRemoteAliasResult.exception, message = loginRemoteAliasResult.message
                                    )
                                }

                            }
                        }
                    } else
                        _userAlias.value = loginAliasResult

                    Timber.w("$TAG onEvent EnteredLogin userAlias => ${_userAlias.value}")
                }
            }

            is ModeAdminEvent.EnteredLoginSuccess -> {
                Timber.i("$TAG onEvent EnteredLoginSuccess aliasId => %s", event.aliasId)
//                _maState.value = ModeAdminState.Success // bascule vers l'accueil
//                val user = getUser()
//                val userAlias = userAliasUseCases.getUserAliasById(event.aliasId)
                val userAlias: UserAlias? = _userAlias.value

                // je récupère l'id de l'alias et l'ajoute au user admin qui n'en avait pas
//                user?.let {
                userAlias?.let {
//                    it.id = event.aliasId
                    Timber.i("$TAG onEvent EnteredLoginSuccess alias => %s", gson.toJson(it))
                    val updatedUser = User(
                        id = it.id,
                        matricule = it.matricule,
                        nom = it.nom,
                        prenom = it.prenom,
                        login = it.prenom.first() + it.nom,
                        idAdonix = it.idAdonix,
                        mailAgence = it.mailAgence,
                        groupe = UserGroup.GROUP_TECHNICIEN.type
                    )
                    userUseCases.insertUser(user = updatedUser)

                    updateUserState(
                        UserState(
                            userId = it.id,
                            userGroup = it.groupe,
                            isLoggedIn = true,
                        ),
                        "EnteredLoginSuccess"
                    )
//                    val route = "accueil/${event.aliasId}"
                    val route = Routes.Accueil.route + event.aliasId
                    Timber.i("$TAG onEvent EnteredLoginSuccess => %s", gson.toJson(it))
                    event.navController.navigate(route)
                }


//                viewModelScope.launch {
//                    setFeuilleDeRoute(event.id)
//                }
            }

            is ModeAdminEvent.EnteredLoginFailure -> {
                Timber.e("$TAG onEvent EnteredLoginFailure")
                _maState.value = ModeAdminState.Error
            }

            is ModeAdminEvent.SelectedMode -> {}

            is ModeAdminEvent.SelectedFrequenceSynchro -> {}

            is ModeAdminEvent.SelectedGroupe -> {}

        }
    }

    private fun getUserSuccessHandler(response: Response<UserAlias>) {
        Timber.i("$TAG getUserSuccessHandler Success")
        val userAlias: UserAlias? =  response.body()
        userAlias?.let {
            _userAlias.value = userAlias
        }
    }

    private fun getUserFailHandler(response: String?) {
        Timber.i("$TAG getUserFailHandler Fail => $response")

    }

    private fun getUserErrorHandler(exception: Exception, message: String) {
        Timber.i("$TAG getUserErrorHandler Error => $message")

    }

    private suspend fun setFeuilleDeRoute(id: String) {
        val token: String? = CredentialBootstrap.defaultCredential().getValidAccessToken()

        token?.let {

            val getFeuilleDeRouteResponse : GetFeuilleDeRouteResponse =
                feuilleDeRouteUseCases.getRemoteFeuilleDeRoute(id, token)

            when(getFeuilleDeRouteResponse) {

                is GetFeuilleDeRouteResponse.Success -> {
                    Timber.i("$TAG onEvent getFeuilleDeRoute Success")
                    getFeuilleDeRouteSuccessHandler(response = getFeuilleDeRouteResponse.response)
                }

                is GetFeuilleDeRouteResponse.Error -> {
                    Timber.i("$TAG onEvent getFeuilleDeRoute Error")
                    getFeuilleDeRouteErrorHandler(
                        exception = getFeuilleDeRouteResponse.exception, message = getFeuilleDeRouteResponse.message
                    )
                }

                is GetFeuilleDeRouteResponse.Fail -> {
                    Timber.i("$TAG onEvent getFeuilleDeRoute Fail")
                    getFeuilleDeRouteFailHandler(response = getFeuilleDeRouteResponse.error)
                }

                null -> {}
            }

        }
    }

    private suspend fun getFeuilleDeRouteSuccessHandler(response: Response<FeuilleDeRoute>) {
        Timber.i("$TAG onEvent getFeuilleDeRouteSuccessHandler")
        val feuilleDeRoute = response.body()

        feuilleDeRoute?.let {

            interventionUseCases.insertAllInterventions.let {
                insertAllInterventions ->
                insertAllInterventions(list = feuilleDeRoute.interventions)
                _maState.value = ModeAdminState.Success // bascule vers l'accueil
            }
        }
    }

    private fun getFeuilleDeRouteErrorHandler(exception: Exception, message: String) {}

    private fun getFeuilleDeRouteFailHandler(response: String?) {}
}

sealed class ModeAdminState {
    data object Initial: ModeAdminState()
    data object Loading: ModeAdminState()
    data object Success: ModeAdminState()
    data object Error: ModeAdminState()
}

sealed class ModeAdminEvent {
    data class SelectedEnvironnement(val value: String): ModeAdminEvent()
    data class SelectedFrequenceSynchro(val value: Int): ModeAdminEvent()
    data class SelectedGroupe(val value: UserGroup): ModeAdminEvent()
    data class EnteredLogin(val login: String, val mode: String): ModeAdminEvent()
    data class EnteredLoginSuccess(val aliasId: String, val navController: NavController) : ModeAdminEvent()
    data object EnteredLoginFailure : ModeAdminEvent()
    data class SelectedMode(val value: String): ModeAdminEvent()
}