package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserApiService
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserProfileRemoteDataSource
import com.proxiserve.proximobilite.modules.connexion.data.repository.UserRepositoryImpl
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response
import java.io.IOException
import java.net.Socket
import java.net.SocketTimeoutException

/**
 * Created by dloriot on 22/09/2024.
 */

class UserProfileRemoteDataSourceTest {
    private lateinit var apiService: UserApiService
    private lateinit var dataSource: UserProfileRemoteDataSource
    private lateinit var userId: String
    private lateinit var userAliasLogin: String
    private lateinit var token: String
    private lateinit var mockUser: User
    private lateinit var mockUserAlias: UserAlias

    @Before
    fun setUp() {
        apiService = mock(UserApiService::class.java)
        dataSource = UserProfileRemoteDataSource(apiService = apiService)
        userId = "123"
        userAliasLogin = "dloriot"
        token = "mock-jwt-token"
        mockUser = User(
            id = userId,
            matricule = "123456789",
            login = "dloriot",
            nom = "Loriot",
            prenom = "David",
            idAdonix = "A123456",
            groupe = "TECH"
        )
        mockUserAlias = UserAlias(
            id = userId,
            matricule = "123456789",
            login = "dloriot",
            nom = "Loriot",
            prenom = "David",
            idAdonix = "A123456",
            groupe = "TECH"
        )
    }

    // USER
    @Test
    fun `getUserProfile returns Success response on successful API call`() = runTest {
        // Réponse simulée
        val mockApiResponse = Response.success(mockUser)
//        val mockResponse = GetUserResponse.Success(Response.success(mockUser))
        `when`(apiService.getUserProfile(userId, token)).thenReturn(mockApiResponse)
        // Appeler la méthode
        val result = dataSource.getUserProfile(userId, token)
        // Vérifier les résultats
        assert(result is GetUserResponse.Success)
        val sucessResult = result as GetUserResponse.Success
        assertEquals(sucessResult.userResponse.code(), 200)
        assertEquals(sucessResult.userResponse.body(), mockUser)
    }

    @Test
    fun `getUserProfile returns Fail on 401 error`() = runTest {
        val mockApiResponse = Response.error<User>(401, ResponseBody.create(null, "Unauthorized"))
        `when`(apiService.getUserProfile(userId, token)).thenReturn(mockApiResponse)

        val result = dataSource.getUserProfile(userId, token)
        assert(result is GetUserResponse.Fail)

        val failResult = result as GetUserResponse.Fail
        assertEquals(failResult.error, AppConstant.TOKEN_INVALIDE)
    }

    @Test
    fun `getUserProfile returns Fail on 500 error`() = runTest {
        val mockApiResponse = Response.error<User>(500, ResponseBody.create(null, "Unauthorized"))
        `when`(apiService.getUserProfile(userId, token)).thenReturn(mockApiResponse)

        val result = dataSource.getUserProfile(userId, token)
        assert(result is GetUserResponse.Fail)

        val failResult = result as GetUserResponse.Fail
        assertEquals(failResult.error, AppConstant.ERREUR_SERVER)
    }

    @Test
    fun `getUserProfile returns Error on SocketTimeoutException`() = runTest {
        // On ne peut pas utiliser when() thenThrow() pour les exception de type SocketTimeout et
        // IOException car en Kotlin les méthode suspend ne déclarent pas explicitement les
        // exceptions via une methode throws
        val exception = SocketTimeoutException()
        `when`(apiService.getUserProfile(userId, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserProfile(userId, token)
        assert(result is GetUserResponse.Error)

        val errorResult = result as GetUserResponse.Error
        assert(errorResult.exception is SocketTimeoutException)
        assertEquals(errorResult.message, AppConstant.CONNECTION_TIMEOUT)
    }

    @Test
    fun `getUserProfile returns Error on IOException`() = runTest {
        val exception = IOException()
        `when`(apiService.getUserProfile(userId, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserProfile(userId, token)
        assert(result is GetUserResponse.Error)

        val errorResult = result as GetUserResponse.Error
        assert(errorResult.exception is IOException)
        assertEquals(errorResult.message, AppConstant.CONNECTION_ERROR)
    }

    @Test
    fun `getUserProfile returns Error on generic exception`() = runTest {
        val exceptionMessage = AppConstant.CONNECTION_ERROR
        val exception = Exception(exceptionMessage)
        `when`(apiService.getUserProfile(userId, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserProfile(userId, token)
        assert(result is GetUserResponse.Error)

        val errorResult = result as GetUserResponse.Error
        assert(true)
        assertEquals(errorResult.message, "Une erreur est survenue: $exceptionMessage")
    }


    // USER ALIAS
    @Test
    fun `getUserAliasProfile returns Success response on successful API call`() = runTest {
        val mockApiResponse = Response.success(mockUserAlias)
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenReturn(mockApiResponse)

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)

        assert(result is GetUserAliasResponse.Success)
        val sucessResult = result as GetUserAliasResponse.Success
        assertEquals(sucessResult.userAliasResponse.code(), 200)
        assertEquals(sucessResult.userAliasResponse.body(), mockUserAlias)
    }

    @Test
    fun `getUserAliasProfile returns Fail on 401 error`() = runTest {
        val mockApiResponse = Response.error<UserAlias>(401, ResponseBody.create(null, "Unauthorized"))
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenReturn(mockApiResponse)

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)
        assert(result is GetUserAliasResponse.Fail)

        val failResult = result as GetUserAliasResponse.Fail
        assertEquals(failResult.error, AppConstant.TOKEN_INVALIDE)
    }

    @Test
    fun `getUserAliasProfile returns Fail on 500 error`() = runTest {
        val mockApiResponse = Response.error<UserAlias>(500, ResponseBody.create(null, "Unauthorized"))
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenReturn(mockApiResponse)

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)
        assert(result is GetUserAliasResponse.Fail)

        val failResult = result as GetUserAliasResponse.Fail
        assertEquals(failResult.error, AppConstant.ERREUR_SERVER)
    }

    @Test
    fun `getUserAliasProfile returns Error on SocketTimeoutException`() = runTest {
        val exception = SocketTimeoutException()
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)
        assert(result is GetUserAliasResponse.Error)

        val errorResult = result as GetUserAliasResponse.Error
        assert(errorResult.exception is SocketTimeoutException)
        assertEquals(errorResult.message, AppConstant.CONNECTION_TIMEOUT)
    }

    @Test
    fun `getUserAliasProfile returns Error on IOException`() = runTest {
        val exception = IOException()
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)
        assert(result is GetUserAliasResponse.Error)

        val errorResult = result as GetUserAliasResponse.Error
        assert(errorResult.exception is IOException)
        assertEquals(errorResult.message, AppConstant.CONNECTION_ERROR)
    }

    @Test
    fun `getUserAliasProfile returns Error on generic exception`() = runTest {
        val exceptionMessage = AppConstant.CONNECTION_ERROR
        val exception = Exception(exceptionMessage)
        `when`(apiService.getUserAliasProfile(userAliasLogin, token)).thenAnswer {
            throw exception
        }

        val result = dataSource.getUserAliasProfile(userAliasLogin, token)
        assert(result is GetUserAliasResponse.Error)

        val errorResult = result as GetUserAliasResponse.Error
        assert(true)
        assertEquals(errorResult.message, "Une erreur est survenue: $exceptionMessage")
    }

}