package com.proxiserve.proximobilite.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.okta.authfoundation.client.OidcClient
import com.okta.authfoundation.client.OidcConfiguration
import com.okta.authfoundation.credential.CredentialDataSource.Companion.createCredentialDataSource
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.proxiserve.proximobilite.BuildConfig
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.DeleteFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.GetFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.GetRemoteFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.OperationUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.PieceUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state.GetFakeFeuilleDeRoute
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.DeleteUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetRemoteUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserById
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserState
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.InsertUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.PrestationUseCases
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import timber.log.Timber

/**
 * Created by dloriot on 27/11/2024.
 */
class BaseViewModelLogoutTest {
    // Remplacement des exécuteurs de tâches par défaut par des synchrones pour les tests
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var baseViewModel: BaseViewModel

    private val mockFeuilleDeRouteUseCases = mockk<FeuilleDeRouteUseCases>(relaxed = true)
    private val mockUserUseCases = mockk<UserUseCases>(relaxed = true)
    private var mockNavController = mockk<NavController>(relaxed = true)

    // Configuration du Dispatcher pour les tests coroutine
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        baseViewModel = BaseViewModel(
            userUseCases = mockUserUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )
        baseViewModel.updateUserState(
            userState = UserState(
                username = "testUserName",
                userId = "123",
                isLoggedIn = true,
                errorMessage = ""
            ),
            from = "test-setup"
        )


        try {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val oidcConfiguration = OidcConfiguration(
                clientId = BuildConfig.CLIENT_ID,
                defaultScope = "openid groups email profile offline_access",
            )
            val client = OidcClient.createFromDiscoveryUrl(
                oidcConfiguration,
                BuildConfig.DISCOVERY_URL.toHttpUrl(),
            )
            CredentialBootstrap.initialize(client.createCredentialDataSource(context))
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    @Test
    fun callLogoutShouldReinitUserState() = runTest {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val mockCredentialBootstrap = mockk<CredentialBootstrap>()

        // Equivalent de when().thenReturn pour Mockk coEvery pour les fonctions suspendues
        coEvery { mockCredentialBootstrap.defaultCredential().delete() } just Runs
        every { mockNavController.navigate(route = any()) } just Runs

        // On initialise le state
        val userState = baseViewModel.userState.first()
        // On vérifie les infos
        assertEquals("testUserName", userState.username)
        assertEquals("123", userState.userId)
        assertEquals(true, userState.isLoggedIn)
        assertEquals("", userState.errorMessage)

        // On lance un job qui écoute et attend les changements via un collecteur suspendu
        // Une fois les changements assertis on l'arrête
        val job = launch {
            baseViewModel.userState.collect() { state ->
//                stateChanges.add(state)
                if (state.username == "") {
                    assertEquals("", userState.username)
                    assertEquals("", userState.userId)
                    assertEquals(false, userState.isLoggedIn)
                    assertEquals(ConnexionConst.DISCONNECTED, userState.errorMessage)
                    cancel()
                }
            }
        }

        // On lance le logout
        baseViewModel.logout(context = context, mockNavController)
        // On utilise advanceUntilIdle() pour attendre l'emission complète de l'état
        testDispatcher.scheduler.advanceUntilIdle()

        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Réinitialisation du Dispatcher principal
    }
}