package com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation

import android.content.Context
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.BaseViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.CertificatInterventionUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.presentation.utils.Routes
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

/**
 * InterventionValidationViewModel
 * @author Edson De Carvalho
 */
@HiltViewModel
class InterventionValidationViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val certificatInterventionUseCases: CertificatInterventionUseCases,
    private val interventionUseCases: InterventionUseCases,
    private val userUseCases : UserUseCases,
    private val historiqueUseCases: HistoriqueUseCases,
    private val appareilUseCases: AppareilUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases,
    private val repository : CertificatInterventionRepository): BaseViewModel(
    certificationInterventionUseCases = certificatInterventionUseCases,
    userUseCases = userUseCases,
    interventionUseCases = interventionUseCases,
    historiqueUseCases = historiqueUseCases,
    appareilUseCases = appareilUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases
) {
    val TAG = "[CertificatInterventionViewModel]"
    fun onEvent(event: IntervetionValidationScreenEvent) {
        Timber.i("$TAG onEvent")
        when(event) {

            is IntervetionValidationScreenEvent.GoToValidate -> {
                Timber.i("$TAG GoToValidate")
                event.navController.navigate(Routes.Intervention.route)
            }

        }
    }
}
// Event
sealed class IntervetionValidationScreenEvent {
    data class GoToValidate(val navController: NavController): IntervetionValidationScreenEvent()


}

