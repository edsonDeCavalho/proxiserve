package com.proxiserve.proximobilite.modules.accueil.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.utils.UserGroup
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.Test

/**
 * Created by dloriot on 03/12/2024.
 */
class AccueilScreenTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: AccueilViewModel
    private lateinit var mockUserUseCases: UserUseCases
    private lateinit var mockUserAliasUseCases: UserAliasUseCases
    private lateinit var mockFeuilleDeRouteUseCases: FeuilleDeRouteUseCases

    private val userTechId = "0h123456789"
    private val userCiId = "0h567891234"
    private val userAdminId = "0h987654321"
    private val userTech = User(
        id = userTechId,
        matricule = "123456789",
        login = "tUser",
        nom = "User",
        prenom = "Technicien",
        idAdonix = "A123456",
        groupe = UserGroup.GROUP_TECHNICIEN.type
    )

    private val userCi = User(
        id = userAdminId,
        matricule = "567891234",
        login = "ciUser",
        nom = "User",
        prenom = "Ci",
        idAdonix = "A123456",
        groupe = UserGroup.GROUP_CI.type
    )

    private val userAdmin = User(
        id = userAdminId,
        matricule = "123456789",
        login = "aUser",
        nom = "User",
        prenom = "Admin",
        idAdonix = "A123456",
        groupe = UserGroup.GROUP_ADMIN.type
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockUserUseCases = mockk(relaxed = true)
        mockUserAliasUseCases = mockk(relaxed = true)
        mockFeuilleDeRouteUseCases = mockk(relaxed = true)

        viewModel = AccueilViewModel(
            userUseCases = mockUserUseCases,
            userAliasUseCases = mockUserAliasUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )
    }

    @Test
    fun userTechShouldBeRedirectToAccueilScreen() = runTest {
        coEvery { viewModel.getUser() } returns userTech

        val state = viewModel.state.first()
        assertEquals("", state.userId)
        assertEquals(false, state.isAdmin)

        val job = launch {
            viewModel.state.collect() { state ->
//                stateChanges.add(state)
                if (state.userId == userTechId) {
                    assertEquals(userTechId, state.userId)
                    assertEquals(false, state.isAdmin)
                    cancel()
                }
            }
        }

        // J'initialise le viewModel ce qui appellera init{} et déclenchera getUser()
        viewModel = AccueilViewModel(
            userUseCases = mockUserUseCases,
            userAliasUseCases = mockUserAliasUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )

        testDispatcher.scheduler.advanceUntilIdle()

        job.cancel()
    }

    @Test
    fun userCiShouldBeRedirectToAccueilScreen() = runTest {
        coEvery { viewModel.getUser() } returns userCi

        val state = viewModel.state.first()
        assertEquals("", state.userId)
        assertEquals(false, state.isAdmin)

        val job = launch {
            viewModel.state.collect() { state ->
                if (state.userId == userCiId) {
                    assertEquals(userCiId, state.userId)
                    assertEquals(false, state.isAdmin)
                    cancel()
                }
            }
        }

        // J'initialise le viewModel ce qui appellera init{} et déclenchera getUser()
        viewModel = AccueilViewModel(
            userUseCases = mockUserUseCases,
            userAliasUseCases = mockUserAliasUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )

        testDispatcher.scheduler.advanceUntilIdle()

        job.cancel()
    }

    @Test
    fun userAdminShouldBeRedirectToAccueilScreen() = runTest {
        coEvery { viewModel.getUser() } returns userAdmin

        val state = viewModel.state.first()
        assertEquals("", state.userId)
        assertEquals(false, state.isAdmin)

        val job = launch {
            viewModel.state.collect() { state ->
//                stateChanges.add(state)
                if (state.userId == userAdminId) {
                    assertEquals(userAdminId, state.userId)
                    assertEquals(true, state.isAdmin)
                    cancel()
                }
            }
        }

        // J'initialise le viewModel ce qui appellera init{} et déclenchera getUser()
        viewModel = AccueilViewModel(
            userUseCases = mockUserUseCases,
            userAliasUseCases = mockUserAliasUseCases,
            feuilleDeRouteUseCases = mockFeuilleDeRouteUseCases
        )

        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }
}