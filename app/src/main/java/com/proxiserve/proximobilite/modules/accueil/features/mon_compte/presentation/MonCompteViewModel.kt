package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.ModeAdminEvent
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserUtils
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dloriot on 06/08/2024.
 */
@HiltViewModel
class MonCompteViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val userUseCases: UserUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases
) : BaseViewModel(
    userUseCases = userUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {

    val TAG = "[MonCompteViewModel]"
    private val _mcState = MutableStateFlow(MonCompteState())
    val mcState: StateFlow<MonCompteState> = _mcState.asStateFlow()

    init {
        viewModelScope.launch {
            synchroProfil()
        }
    }
    suspend fun updateMonCompteState(state: MonCompteState) {
        _mcState.value = state
        _mcState.emit(state)
    }


    suspend fun onEvent(event: MonCompteEvent) {
        Timber.i("$TAG onEvent")
        when(event) {

            is MonCompteEvent.GoToModeAdmin -> {
                event.navController.navigate(Routes.ModeAdmin.route + event.userId)
            }

            is MonCompteEvent.GoToAccueil -> {
//                event.navController.navigate(Routes.Accueil.route + event.userId)
            }

            is MonCompteEvent.SynchroProfil -> {
                Timber.i("$TAG onEvent SynchroProfil")
                delay(3000)
                synchroProfil()

            }
        }
    }

    suspend fun synchroProfil() {
        Timber.d("$TAG SynchroProfil")
        val user: User? = getUser()

        user?.let {
            updateMonCompteState(
                state = MonCompteState(
                    modeAdmin = UserUtils.isAdmin(it.groupe) || UserUtils.isCi(it.groupe),
                    socciete = "",
                    agence = it.mailAgence,
                    user = it
                )
            )
        }?: run {
            Timber.e(
                SynchroProfilException(
                    message = "La synchronisation de profil a echoué !"
                )
            )
        }
    }
}

class SynchroProfilException(message: String) : Exception(message)