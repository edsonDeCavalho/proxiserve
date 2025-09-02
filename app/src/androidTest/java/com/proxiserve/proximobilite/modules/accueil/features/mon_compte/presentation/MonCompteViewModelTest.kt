package com.proxiserve.proximobilite.modules.accueil.features.mon_compte.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.junit.jupiter.api.Assertions.*


/**
 * Created by dloriot on 26/11/2024.
 */
class MonCompteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Pour manipuler les StateFlow en test

    private lateinit var viewModel: MonCompteViewModel
    private lateinit var mockUserUseCases: UserUseCases
    private lateinit var mockFeuilleDeRouteUseCases: FeuilleDeRouteUseCases
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Remplace le dispatcher principal par un dispatcher de test
        mockUserUseCases = mockk(relaxed = true)
        mockFeuilleDeRouteUseCases = mockk(relaxed = true)

        viewModel = MonCompteViewModel(
            context = InstrumentationRegistry.getInstrumentation().targetContext,
            userUseCases = mockUserUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )
    }

    @Test
    fun `synchroProfil should update MonCompteState with user profile when successful`() = runTest {
        //Simulation d'un utilisateur récupéré
        val user = User(
            id = "0h123456789",
            matricule = "123456789",
            login = "dloriot",
            nom = "Loriot",
            prenom = "David",
            idAdonix = "A123456",
            groupe = "TECH"
        )

        val userUpdate = User(
            id = "0h123456789",
            matricule = "123456789",
            login = "dloriot",
            nom = "Loriot",
            prenom = "David",
            idAdonix = "A123456",
            groupe = "ADMIN"
        )

        // Mock du use case pour retourner un utilisateur valide
        coEvery { mockUserUseCases.getUser() } returns user
//        whenever(mockUserUseCases.getUser()).thenReturn(user)

        // Appel à `synchroProfil` qui appelle getUser et met à jour le state
        viewModel.synchroProfil()

        // Je récupère le state et vérifie
        val state = viewModel.mcState.first()

        assertNotNull(state.user)
        assertEquals("Loriot", state.user?.nom)
        assertEquals("David", state.user?.prenom)
        assertEquals("TECH", state.user?.groupe)

        // Comme pour baseViewModeltest je lance un job qui écoute en permanence le state jusqu'au changement
        val job = launch {
            viewModel.mcState.collect() { state ->
                if (state.user?.groupe  == "ADMIN") {
                    assertEquals("Loriot", state.user?.nom)
                    assertEquals("David", state.user?.prenom)
                    assertEquals("ADMIN", state.user?.groupe)
                    cancel()
                }
            }
        }
        // Je modifie le mockk pour que getUser renvoi le userUpdate puis relance le synchroProfil()
        coEvery { mockUserUseCases.getUser() } returns userUpdate
        viewModel.synchroProfil()
        // J'attends la fin des opérations
        testDispatcher.scheduler.advanceUntilIdle()

        job.cancel()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Réinitialisation du Dispatcher après les tests
    }
}