
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToNode
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.proxiserve.proximobilite.core.utils.TestTag
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.FdrUtils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.FeuilleDeRouteViewModel
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.screens.FeuilleDeRouteScreen
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state.FeuilleDeRouteState
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.DetailInterventionViewModel
import com.proxiserve.proximobilite.modules.detail_intervention.presentation.screens.DetailInterventionScreen
import com.proxiserve.proximobilite.ui.theme.ProximobiliteTheme
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by dloriot on 28/11/2024.
 */
class FeuilleDeRouteScreenTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var mockFdrViewModel: FeuilleDeRouteViewModel
    private lateinit var mockDetailViewModel: DetailInterventionViewModel
    private lateinit var interventionCode: String
    private lateinit var contactName: String
    private lateinit var routeFdr: String
    private lateinit var routeDetail: String
    private lateinit var interventions: List<Intervention>
    private lateinit var intervention: Intervention

    @Before
    fun setup() {

        initData()

        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            provideFdrStateOnViewModelCall()

            provideInterventionsOnViewModelCall()

            provideInterventionOnDetailViewModelCall()


            ProximobiliteTheme {
                NavHost(
                    navController = navController,
                    startDestination = routeFdr
                ) {

                    composable(route = routeFdr) {
                        FeuilleDeRouteScreen(
                            navController = navController,
                            viewModel = mockFdrViewModel,
                            id = "1"
                        )
                    }

                    composable(route = routeDetail) { backStackEntry ->
                         DetailInterventionScreen(
                            navController = navController,
                            viewModel = mockDetailViewModel,
                            id = interventionCode
                        )
                    }
                }
            }
        }
    }

    @Test
    fun interventionListShouldDisplayItems() {
        composeTestRule.onNodeWithTag(TestTag.FDR_LAZY_COLUMN).performScrollToNode(hasTestTag(interventionCode)) // Attendre que l'UI réagisse et vérifier l'affichage de l'écran de détail
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(interventionCode).assertIsDisplayed()
//        composeTestRule.onNodeWithTag(interventionCode).performClick()

    }

    private fun initData() {
        mockFdrViewModel = mockk(relaxed = false)
        mockDetailViewModel = mockk(relaxed = false)
        interventionCode = "12345"
        contactName = "Test Contact"
        routeFdr = "fdr/"
        routeDetail = "detail_intervention/12345"
        intervention = Intervention(interventionCode = interventionCode, contactNom = contactName)
        interventions = listOf(intervention)
    }

    private fun provideFdrStateOnViewModelCall() {
        every {
            mockFdrViewModel.fdrState
        } returns
                MutableStateFlow(
                    FeuilleDeRouteState(
                        currentFeuilleDeRoute = FeuilleDeRoute(
                            idTechnicien = "1",
                            interventions = interventions,
                            appareils = emptyList(),
                            historiques = emptyList()
                        ),
                        errorMessage = null
                    )
                )
    }

    private fun provideInterventionsOnViewModelCall() {
        every {
            mockFdrViewModel.getIntervention(interventions = interventions)
        }  returns interventions.groupBy { it.getDateVisiteToString() to it.getPeriode() }
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

    private fun provideInterventionOnDetailViewModelCall() {
        coEvery {
            mockDetailViewModel.getCurrentIntervention(interventionCode)
        } returns
                intervention
    }

}