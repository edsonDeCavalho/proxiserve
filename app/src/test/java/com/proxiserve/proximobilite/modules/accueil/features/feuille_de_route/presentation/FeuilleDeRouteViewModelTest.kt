package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation

import android.content.Context
import com.proxiserve.proximobilite.core.utils.TimeUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.FdrUtils
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Created by dloriot on 28/11/2024.
 */
class FeuilleDeRouteViewModelTest {

    private lateinit var viewModel: FeuilleDeRouteViewModel
    private val mockUserUseCases = mock(UserUseCases::class.java)
    private val mockFeuilleDeRouteUseCases = mock(FeuilleDeRouteUseCases::class.java)
    private val mockInterventionUseCases = mock(InterventionUseCases::class.java)
    private val mockHistoriqueUseCases = mock(HistoriqueUseCases::class.java)
    private val mockAppareilUseCases = mock(AppareilUseCases::class.java)
    private val context = mock(Context::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = FeuilleDeRouteViewModel(
            context = context,
            userUseCases = mockUserUseCases,
            useCases = mockFeuilleDeRouteUseCases,
            interventionUseCases = mockInterventionUseCases,
            historiqueUseCases = mockHistoriqueUseCases,
            appareilUseCases = mockAppareilUseCases
        )
    }

    @Test
    fun `checkHasAllAppareils throws exception when an appareil is missing`()  {
        val interventions = listOf(
            Intervention(interventionCode = "1", appareils = listOf("A1", "A2")),
            Intervention(interventionCode = "2", appareils = listOf("A3"))
        )
        val appareils = listOf(
            Appareil(appareilCode = "A1"),
            Appareil(appareilCode = "A2")  // A3 est manquant
        )

        val exception = assertThrows<AppareilMissingException> {
            viewModel.checkHasAllAppareils(interventions, appareils)
        }



        assertEquals("Un ou plusieurs appareils sont manquants dans la feuille de route", exception.message)
    }

    @Test
    fun `getIntervention should sort interventions by date and period`() {

        val date1 = 1732662000 // 2024-11-27
        val date2 = 1732748400 // 2024-11-28

        val intervention1 = Intervention(interventionCode = "1", dateVisite = date1, periodeRdv = FdrUtils.MATIN_FLAG)
        val intervention2 = Intervention(interventionCode = "2", dateVisite = date1, periodeRdv = FdrUtils.APRES_MIDI_FLAG)
        val intervention3 = Intervention(interventionCode = "3", dateVisite = date2, periodeRdv = FdrUtils.MATIN_FLAG)

        val interventions = listOf(intervention2, intervention3, intervention1)

        val result = viewModel.getIntervention(interventions)

        val expectedOrder = listOf(
            Pair("2024-11-27", FdrUtils.MATIN) to listOf(intervention1),
            Pair("2024-11-27", FdrUtils.APRES_MIDI) to listOf(intervention2),
            Pair("2024-11-28", FdrUtils.MATIN) to listOf(intervention3)
        )

        assertEquals(expectedOrder.map { it.first }, result.keys.toList())
        assertEquals(expectedOrder.map { it.second }, result.values.toList())
    }

}