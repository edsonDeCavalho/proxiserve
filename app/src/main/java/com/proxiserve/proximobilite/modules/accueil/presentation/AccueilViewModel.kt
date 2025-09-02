package com.proxiserve.proximobilite.modules.accueil.presentation


import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.log

/**
 * Created by dloriot on 06/07/2024.
 */
@HiltViewModel
class AccueilViewModel @Inject constructor(
    val userUseCases: UserUseCases,
    private val userAliasUseCases: UserAliasUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases
) : BaseViewModel(
    userUseCases = userUseCases,
    userAliasUseCases = userAliasUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {
    val TAG = "[AccueilViewModel]"

    private val _state = MutableStateFlow(AccueilState())
    val state = _state.asStateFlow()

    private val _user = MutableStateFlow<User?>(User())
    val user = _user.asStateFlow()

    private val _userAlias = MutableStateFlow<UserAlias?>(UserAlias())
    val userAlias = _userAlias.asStateFlow()

    init {
        viewModelScope.launch {
            Timber.i("$TAG init")

            // delay pour laisser le temps aux opérations bdd (#pasOuf)
//            delay(3000)
            // récupérer le user
            _user.value = getUser()

            // selon le groupe mettre à jour le state par la bascule entre les ecrans

            user.value?.let {
                Timber.i("$TAG init user => ${gson.toJson(it)}")
                it.groupe.let {

                    when(it) {

                        UserGroup.GROUP_ADMIN.type -> {

                            updateState(
                                AccueilState(
                                    userId = user.value!!.id,
                                    isLoading = false,
                                    isAdmin = true
                                )
                            )
                        }

                        UserGroup.GROUP_CI.type -> {
                            updateState(
                                AccueilState(
                                    userId = user.value!!.id,
                                    isLoading = false,
                                    isAdmin = true
                                )
                            )
                        }

                        else -> {
                            updateState(
                                AccueilState(
                                    userId = user.value!!.id,
                                    isLoading = false,
                                    isAdmin = false
                                )
                            )
                        }
                    }
                }
            }

        }
    }

    fun updateState(newState: AccueilState) {
        Timber.i("$TAG ADMIN updateState ${gson.toJson(newState)}")
        _state.value = newState
    }


    suspend fun onEvent(event: AccueilEvent) {
        when(event) {
            AccueilEvent.Logout -> TODO()
            is AccueilEvent.MonCompteEvent -> {

            }
            is AccueilEvent.GoToFeuilleDeRouteEvent -> {
                event.navController.navigate(event.route)
            }

            is AccueilEvent.EnteredLogin -> {
                val accessToken = CredentialBootstrap.defaultCredential().getValidAccessToken()
                accessToken?.let {

                    val response: GetUserAliasResponse = userAliasUseCases.getRemoteUserAlias(login = event.login, token = accessToken)

                    when(response) {
                        is GetUserAliasResponse.Success -> {
                            val newUserAlias: UserAlias? = response.userAliasResponse.body()

                            newUserAlias?.let {
                                // maj du de l'id tech du user admin: les users admin n'ont pas d'id tech
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
                                userUseCases.deleteUser()
                                userUseCases.insertUser(user = updatedUser)

                                updateState(
                                    AccueilState(
                                        userId = it.id,
                                        userAlias = it,
                                        isLoading = false,
                                        isAdmin = false
                                    )
                                )
                            }
                        }

                        is GetUserAliasResponse.Error -> {

                        }

                        is GetUserAliasResponse.Fail -> {

                        }

                    }

                }?:run {
                    Timber.e("$TAG token NULL")
                }

            }

            AccueilEvent.EnteredLoginFailure -> TODO()
            is AccueilEvent.EnteredLoginSuccess -> TODO()
        }


    }
}