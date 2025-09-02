package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.GetFeuilleDeRouteResponse
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.FdrUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.event.FeuilleDeRouteEvent
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state.FeuilleDeRouteState
import com.proxiserve.proximobilite.modules.connexion.domain.model.InvalidUserException
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.SortedMap
import javax.inject.Inject

/**
 * Created by dloriot on 26/08/2024.
 */
@HiltViewModel
class FeuilleDeRouteViewModel@Inject constructor(
    @ApplicationContext val context: Context,
    userUseCases: UserUseCases,
    private val useCases: FeuilleDeRouteUseCases,
    private val interventionUseCases: InterventionUseCases,
    private val historiqueUseCases: HistoriqueUseCases,
    private val appareilUseCases: AppareilUseCases
) : BaseViewModel(
    userUseCases = userUseCases,
    feuilleDeRouteUseCases = useCases,
    interventionUseCases = interventionUseCases,
    appareilUseCases = appareilUseCases
) {
    val TAG = "[FeuilleDeRouteViewModel]"

    private val _fdrState = MutableStateFlow(FeuilleDeRouteState())
    val fdrState: StateFlow<FeuilleDeRouteState> = _fdrState.asStateFlow()

    init {
        viewModelScope.launch {
//            _fdrState.value.currentFeuilleDeRoute = useCases.getFakeFeuilleDeRoute(context)
            val user = getUser()
            val accessToken =  CredentialBootstrap.defaultCredential().getValidAccessToken()
            Timber.i("$TAG GetFeuilleDeRoute => %s", gson.toJson(user))

            user?.id?.let {
                if (it.startsWith("0Hn")) {
//                    setFeuilleDeRoute(id = it, token = "Bearer ${accessToken?.dropLast(1)}", from = "init")
                    setFeuilleDeRoute(id = it, token = accessToken, from = "init")

                } else
                    Timber.e(InvalidUserException("Profil User Admin !"))

            }?:run {
                Timber.e(InvalidUserException("Profil User invalide !"))
            }
        }
    }

    fun onEvent(event: FeuilleDeRouteEvent) {
        when(event) {
            is FeuilleDeRouteEvent.GetFeuilleDeRoute -> {
                Timber.i("$TAG onEvent() GetFeuilleDeRoute")
//                _state.value.currentInterventions =
            }

            is FeuilleDeRouteEvent.SelectIntervention -> {
                Timber.i("$TAG onEvent() SelectIntervention")
//                updateSelectedIntervention(event.intervention)
            }

            is FeuilleDeRouteEvent.GoToDetail -> {
                Timber.i("$TAG onEvent() GoToDetail %s%s" ,Routes.DetailIntervention.route, event.id)
                val id = event.id
                event.navController.navigate("detail_intervention/$id" )
//                event.navController.navigate(Routes.DetailIntervention.route + id )
            }

        }
    }

    suspend fun setFeuilleDeRoute(id: String, token: String?, from: String) {
        Timber.d("$TAG setFeuilleDeRoute from %s => id = %s", from, id)
        token?.let {
            val response: GetFeuilleDeRouteResponse =
                useCases.getRemoteFeuilleDeRoute(id = id, token = token)
//                useCases.getRemoteFeuilleDeRoute(id = id, token = token.dropLast(1))

            when (response) {
                is GetFeuilleDeRouteResponse.Success -> {
                    getFeuilleDeRouteSuccessHandler(response = response.response)
                }

                is GetFeuilleDeRouteResponse.Fail -> {
                    getFeuilleDeRouteFailHandler(error = response.error)
                }

                is GetFeuilleDeRouteResponse.Error -> {
                    getFeuilleDeRouteErrorHandler(
                        exception = response.exception,
                        message = response.message
                    )
                }
            }
        }

        _fdrState.value.currentFeuilleDeRoute?.let {
            clearFeuilleDeRoute()
            try {
                checkHasAllAppareils(interventions = it.interventions, appareils = it.appareils)

            } catch (e: AppareilMissingException) {
                Timber.e(e)
            }
            interventionUseCases.insertAllInterventions(list = it.interventions)
            historiqueUseCases.insertAllHistoriques(list = it.historiques)
            historiqueUseCases.insertAllHistoriques(list = it.historiques)
            appareilUseCases.insertAppareils(list = it.appareils)
        } ?: run {
            Timber.e(
                CurrentFeuilleDeRouteException(
                    message = "Les interventions n'ont pas pu être récupérées (currentFeuilleDeRoute null)"
                )
            )
        }
    }

    private suspend fun clearFeuilleDeRoute() {
        Timber.d( "$TAG clearFeuilleDeRoute() called")
        interventionUseCases.deleteAllIntervention()
        historiqueUseCases.deleteHistorique()
        appareilUseCases.deleteAppareil()
    }

    fun checkHasAllAppareils(interventions: List<Intervention>, appareils: List<Appareil>) {
        Timber.i( "$TAG checkHasAllAppareils() called")
        var hasAppareilMissing = false
//        CoroutineScope(Dispatchers.IO).launch {

            interventions.forEach { intervention ->

                intervention.appareils.forEach { idAppareil ->

                    val result = appareils.find {
                        it.appareilCode == idAppareil
//                        it.appareilCode == "test"
                    }

                    if (result == null) {
                        hasAppareilMissing = true

//                        return@launch
                    }
                }
            }
//        }

        if (hasAppareilMissing) {
            throw AppareilMissingException("Un ou plusieurs appareils sont manquants dans la feuille de route")
        } else
            Timber.i( "$TAG checkHasAllAppareils() result => OK" )
    }

    private suspend fun getFeuilleDeRouteSuccessHandler(response: Response<FeuilleDeRoute>) {

        val feuilleDeRoute: FeuilleDeRoute? = response.body()
        Timber.w("$TAG getFeuilleDeRouteSuccessHandler => %s", gson.toJson(feuilleDeRoute))

        feuilleDeRoute?.let {
            updateCurrentFeuilleDeRoute(feuilleDeRoute = it)
        }?:run {
            Timber.e(CurrentFeuilleDeRouteException(message = "La feuille de route récupérée est vide"))
        }
    }

    private fun getFeuilleDeRouteFailHandler(error: String?) {
//        val message = "La récupération de feuille de route a echoué !: $error"
        val message = "La récupération de la feuille de route a echoué !"
        Timber.e(CurrentFeuilleDeRouteException(message = message))
        updateFdrState(
            FeuilleDeRouteState(
                aliasId = null,
                currentFeuilleDeRoute = null,
                currentAppareils = emptyList(),
                currentHistoriques = emptyList(),
                currentInterventions = emptyList(),
                currentOperations = emptyList(),
                currentPieces = emptyList(),
                currentPrestations = emptyList(),
                errorMessage = message
            )
        )
    }

    private fun getFeuilleDeRouteErrorHandler(exception: Exception, message: String) {

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

        updateFdrState(
            FeuilleDeRouteState(
                aliasId = null,
                currentFeuilleDeRoute = null,
                currentAppareils = emptyList(),
                currentHistoriques = emptyList(),
                currentInterventions = emptyList(),
                currentOperations = emptyList(),
                currentPieces = emptyList(),
                currentPrestations = emptyList(),
                errorMessage = message
            )
        )
    }

    private suspend fun updateCurrentFeuilleDeRoute(feuilleDeRoute: FeuilleDeRoute) {
        Timber.i("$TAG updateCurrentFeuilleDeRoute %s", feuilleDeRoute.idTechnicien)
        _fdrState.value.currentFeuilleDeRoute = feuilleDeRoute
        _fdrState.emit(value = FeuilleDeRouteState(
                currentFeuilleDeRoute = feuilleDeRoute,
                currentAppareils = feuilleDeRoute.appareils,
                currentHistoriques = feuilleDeRoute.historiques,
                currentInterventions = feuilleDeRoute.interventions,
                currentOperations = emptyList(),
                currentPieces = emptyList(),
                errorMessage = null
            )
        )
    }

    private fun updateFdrState(fdrState: FeuilleDeRouteState) {
        Timber.i("$TAG updateFdrState => %s", gson.toJson(fdrState))
        _fdrState.value = fdrState
    }

    fun getIntervention(interventions: List<Intervention>) : SortedMap<Pair<String?, String?>, List<Intervention>> {
        return interventions.groupBy { it.getDateVisiteToString() to it.getPeriode() }
            .toSortedMap(
                compareBy({it.first}, {
                    when(it.second) {
                        FdrUtils.MATIN -> 0
                        FdrUtils.APRES_MIDI -> 1
                        else -> 2
                    }
                })
            )
    }
}

class CurrentFeuilleDeRouteException(message: String) : Exception(message)
class AppareilMissingException(message: String) : Exception(message)