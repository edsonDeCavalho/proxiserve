package com.proxiserve.proximobilite.modules.detail_intervention.presentation

import android.content.Context
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dloriot on 05/09/2024.
 */
@HiltViewModel
class DetailInterventionViewModel@Inject constructor(
    @ApplicationContext val context: Context,
    userUseCases: UserUseCases,
    private val interventionUseCases: InterventionUseCases,
    private val historiqueUseCases: HistoriqueUseCases,
    private val appareilUseCases: AppareilUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases
) : BaseViewModel(userUseCases = userUseCases,
    interventionUseCases = interventionUseCases,
    historiqueUseCases = historiqueUseCases,
    appareilUseCases = appareilUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {
    val TAG = "[DetailInterventionViewModel]"

    suspend fun getInterventionHistoriques(lieuCode: String): List<Historique>? {
        Timber.i("$TAG getInterventionHistoriques $lieuCode")
        return historiqueUseCases.getHistoriqueByLieuCode(lieuCode = lieuCode)
    }

    suspend fun getCurrentHistorique(id: String): Historique? {
        Timber.i("$TAG getCurrentHistorique $id")
        return historiqueUseCases.getHistoriqueByInterventionCode.let { it(id) }
    }

    suspend fun getInterventionAppareils(intervention: Intervention): List<Appareil>? {
        Timber.i("$TAG getInterventionAppareils ${intervention.interventionCode}")
        return appareilUseCases.getAppareils(intervention = intervention)
    }

    suspend fun getCurrentAppareil(id: String): Appareil? {
        Timber.i("$TAG getCurrentHistorique $id")
        return appareilUseCases.getAppareilById.let { it(id) }
    }

    fun onEvent(event: DetailInterventionEvent) {
        Timber.i("$TAG onEvent")

        when(event) {
            is DetailInterventionEvent.GoToSmsClient -> {
                Timber.i("$TAG GoToSmsClient")
            }
            is DetailInterventionEvent.GoToAppareil -> {
                Timber.i("$TAG GoToAppareil")
                val idIntervention = event.id
                event.navController.navigate("${Routes.Appareil.route}$idIntervention")
            }
            is DetailInterventionEvent.GoToSelectedAppareil -> {
                Timber.i("$TAG GoToSelectedAppareil")
                val appareilCode = event.appareilCode
                event.navController.navigate("${Routes.AppareilDetail.route}$appareilCode")
            }
            is DetailInterventionEvent.GoToHistorique -> {
                Timber.i("$TAG GoToHistorique lieuCode => %s%s", Routes.Historique.route, event.lieuCode)
                val lieuCode = event.lieuCode
                event.navController.navigate("${Routes.Historique.route}$lieuCode")
            }
            is DetailInterventionEvent.SendCiAbsent -> {
                Timber.i("$TAG SendCiAbsent")
            }
            is DetailInterventionEvent.SendCiInjustifie -> {
                Timber.i("$TAG SendCiInjustifie")
            }
            is DetailInterventionEvent.SendCiRefus -> {
                Timber.i("$TAG SendCiRefus")
            }
            is DetailInterventionEvent.GoToSelectedHistorique -> {
                Timber.i("$TAG SelectHistorique")
                val interventionCode = event.interventionCode
                event.navController.navigate("${Routes.HistoriqueDetail.route}$interventionCode")
            }
            is DetailInterventionEvent.GoToAccueilCertificatIntervention -> {
                Timber.i("$TAG GoToAccueilCertificatIntervention")
                event.navController.navigate(Routes.CertificatIntervention.route)
            }
            is DetailInterventionEvent.GoToIntervantion -> {
                Timber.i("$TAG GoToIntervantion")
                event.navController.navigate(Routes.Intervention.route)
            }
        }
    }
}
class HistoriquesException(message: String) : Exception(message)

// State
//data class InterventionState(
//    val selectedIntervention: Intervention? = null
//)

// Event
sealed class DetailInterventionEvent {
    data class GoToSmsClient(val navController: NavController): DetailInterventionEvent()
    data class GoToHistorique(val navController: NavController, val lieuCode: String): DetailInterventionEvent()
    data class GoToSelectedHistorique(val navController: NavController, val interventionCode: String): DetailInterventionEvent()
    data class GoToAppareil(val navController: NavController, val id: String): DetailInterventionEvent()
    data class GoToSelectedAppareil(val navController: NavController, val appareilCode: String): DetailInterventionEvent()
    data class GoToAccueilCertificatIntervention(val navController: NavController): DetailInterventionEvent()
    data class GoToIntervantion(val navController: NavController): DetailInterventionEvent()
    data object SendCiAbsent: DetailInterventionEvent()
    data object SendCiRefus: DetailInterventionEvent()
    data object SendCiInjustifie: DetailInterventionEvent()
}