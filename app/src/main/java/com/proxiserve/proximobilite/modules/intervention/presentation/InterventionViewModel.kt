package com.proxiserve.proximobilite.modules.intervention.presentation




import android.content.Context
import androidx.lifecycle.viewModelScope
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
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.PhotoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * CertificatInterventionViewModel
 * @author Edson De Carvalho
 */
@HiltViewModel
class IntervetionScreenViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val certificatInterventionUseCases: CertificatInterventionUseCases,
    private val interventionUseCases: InterventionUseCases,
    private val userUseCases : UserUseCases,
    private val historiqueUseCases: HistoriqueUseCases,
    private val appareilUseCases: AppareilUseCases,
    private val feuilleDeRouteUseCases: FeuilleDeRouteUseCases,
    private val photoUseCases: PhotoUseCases,
    private val repository : CertificatInterventionRepository): BaseViewModel(
    certificationInterventionUseCases = certificatInterventionUseCases,
    userUseCases = userUseCases,
    interventionUseCases = interventionUseCases,
    historiqueUseCases = historiqueUseCases,
    appareilUseCases = appareilUseCases,
    feuilleDeRouteUseCases = feuilleDeRouteUseCases,
    photoUseCases = photoUseCases,
) {
    val TAG = "[CertificatInterventionViewModel]"
    fun onEvent(event: IntervetionScreenEvent) {
        Timber.i("$TAG onEvent")
        when(event) {
            is IntervetionScreenEvent.GoToOperationsRealisees -> {
                Timber.i("$TAG GoToOperationsRealisees")
                event.navController.navigate(Routes.Intervention.route)
            }
            is IntervetionScreenEvent.GoToPiecesUtilisees -> {
                Timber.i("$TAG GoToPiecesUtilisees")
                event.navController.navigate(Routes.Intervention.route)
            }
            is IntervetionScreenEvent.GoToInterventionValication -> {
                Timber.i("$TAG GoToInterventionValication")
                event.navController.navigate(Routes.InterventionValidation.route)
            }
//                is CertificatInterventionEvent.SendCiInjustifie -> {
//                    Timber.i("$TAG SendCiInjustifie")
//                }
            is IntervetionScreenEvent.GoToScannerQRCodeOuAjouterPhotos -> {
                Timber.i("$TAG GoToScannerQRCodeOuAjouterPhotos")
                event.navController.navigate(Routes.Intervention.route)
            }

            is IntervetionScreenEvent.InsertPhoto -> {
                viewModelScope.launch {
                    photoUseCases.insertPhoto(event.photo)
                }
            }

            is IntervetionScreenEvent.DelatePhoto -> {
                viewModelScope.launch {
                    photoUseCases.deletePhoto(event.photo)
                }
            }
        }
    }
}
// Event
sealed class IntervetionScreenEvent {
    data class GoToOperationsRealisees(val navController: NavController): IntervetionScreenEvent()
    data class GoToPiecesUtilisees(val navController: NavController): IntervetionScreenEvent()
    data class GoToScannerQRCodeOuAjouterPhotos(val navController: NavController): IntervetionScreenEvent()
    data class GoToInterventionValication(val navController: NavController): IntervetionScreenEvent()
    // data object SendCiInjustifie: CertificatInterventionEvent()
    data class InsertPhoto(val photo: Photo): IntervetionScreenEvent()
    data class DelatePhoto(val photo: Photo): IntervetionScreenEvent()
}

