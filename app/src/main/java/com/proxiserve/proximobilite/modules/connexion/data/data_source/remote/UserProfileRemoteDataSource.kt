package com.proxiserve.proximobilite.modules.connexion.data.data_source.remote

import android.provider.SyncStateContract.Constants
import androidx.navigation.NavController
import com.proxiserve.proximobilite.core.ResponseError
import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.event.FeuilleDeRouteEvent
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by dloriot on 20/09/2024.
 */

class UserProfileRemoteDataSource @Inject constructor(
    private val apiService: UserApiService
) {
    suspend fun getUserProfile(id: String, token: String): GetUserResponse {
         return try {
            val response = apiService.getUserProfile(userId = id, token = token)
             if (response.isSuccessful) {
                 Timber.i("response.isSuccessful")
                 GetUserResponse.Success(userResponse = response)
             } else
                 setFailUserResponse(response = response)

        } catch (e: SocketTimeoutException) {
            GetUserResponse.Error(exception = e, message = AppConstant.CONNECTION_TIMEOUT)
        } catch (e: IOException) {
             GetUserResponse.Error(exception = e, message = AppConstant.CONNECTION_ERROR)
        } catch (e: Exception) {
             GetUserResponse.Error(exception = e, message = "Une erreur est survenue: ${e.localizedMessage}")
        }
    }

    suspend fun getUserProfileByLogin(login: String, token: String): GetUserResponse {
        return try {
            val response = apiService.getUserProfileByLogin(userLogin = login, token = token)
            if (response.isSuccessful)
                GetUserResponse.Success(userResponse = response)
            else
                setFailUserResponse(response = response)

        } catch (e: SocketTimeoutException) {
            GetUserResponse.Error(exception = e, message = AppConstant.CONNECTION_TIMEOUT)
        } catch (e: IOException) {
            GetUserResponse.Error(exception = e, message = AppConstant.CONNECTION_ERROR)
        } catch (e: Exception) {
            GetUserResponse.Error(exception = e, message = "Une erreur est survenue: ${e.localizedMessage}")
        }
    }

    private fun setFailUserResponse(response: Response<User>): GetUserResponse {
        return when (response.code()) {
            401 -> {
                GetUserResponse.Fail(error = AppConstant.TOKEN_INVALIDE)
            }

            500 -> {
                GetUserResponse.Fail(error = AppConstant.ERREUR_SERVER)
            }

            else -> {
                GetUserResponse.Fail(error = response.errorBody()?.toString())
            }
        }
    }

    suspend fun getUserAliasProfile(login: String, token: String): GetUserAliasResponse {
        return try {
            val response = apiService.getUserAliasProfile(userLogin = login, token = token)
            if (response.isSuccessful)
                GetUserAliasResponse.Success(userAliasResponse = response)
            else
                setFailUserAliasResponse(response = response)

        } catch (e: SocketTimeoutException) {
            GetUserAliasResponse.Error(exception = e, message = AppConstant.CONNECTION_TIMEOUT)
        } catch (e: IOException) {
            GetUserAliasResponse.Error(exception = e, message = AppConstant.CONNECTION_ERROR)
        } catch (e: Exception) {
            GetUserAliasResponse.Error(exception = e, message = "Une erreur est survenue: ${e.localizedMessage}")
        }
    }

    private fun setFailUserAliasResponse(response: Response<UserAlias>): GetUserAliasResponse {
        return when (response.code()) {
            401 -> {
                GetUserAliasResponse.Fail(error = AppConstant.TOKEN_INVALIDE)
            }

            500 -> {
                GetUserAliasResponse.Fail(error = AppConstant.ERREUR_SERVER)
            }

            else -> {
                GetUserAliasResponse.Fail(error = response.errorBody()?.string())
            }
        }
    }
}

sealed class GetUserResponse {
    class Success(val userResponse: Response<User>) : GetUserResponse()
    class Fail(val error: String?) : GetUserResponse()
    class Error(val exception: Exception, val message: String) : GetUserResponse()
}

sealed class GetUserAliasResponse {
    class Success(val userAliasResponse: Response<UserAlias>) : GetUserAliasResponse()
    class Fail(val error: String?) : GetUserAliasResponse()
    class Error(val exception: Exception, val message: String) : GetUserAliasResponse()
}