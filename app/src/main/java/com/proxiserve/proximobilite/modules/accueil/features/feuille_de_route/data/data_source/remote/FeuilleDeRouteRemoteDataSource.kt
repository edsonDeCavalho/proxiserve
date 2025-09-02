package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote

import com.proxiserve.proximobilite.core.utils.AppConstant
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by dloriot on 20/09/2024.
 */
class FeuilleDeRouteRemoteDataSource @Inject constructor(
    private val apiService: FeuilleDeRouteApiService
) {
    val TAG = "[FeuilleDeRouteRemoteDataSource]"
    suspend fun getFeuilleDeRoute(id: String, token: String): GetFeuilleDeRouteResponse {
        Timber.i("$TAG getFeuilleDeRoute with id => $id")

        return try {
            val response = apiService.getFeuilleDeRoute(userId = id, token = token)
            if (response.isSuccessful)
                GetFeuilleDeRouteResponse.Success(response = response)
            else
                setFailResponse(response)

        } catch (e: SocketTimeoutException) {
            GetFeuilleDeRouteResponse.Error(exception = e, message = "Le délai d'attente est dépassé. veuillez réessayer.")
        } catch (e: IOException) {
            GetFeuilleDeRouteResponse.Error(exception = e, message = "Problème de connexion. Veuillez réessayer.")
        } catch (e: Exception) {
            GetFeuilleDeRouteResponse.Error(exception = e, message = "Une erreur est survenue: ${e.localizedMessage}")
        }
    }

    private fun setFailResponse(response: Response<FeuilleDeRoute>): GetFeuilleDeRouteResponse {
        Timber.i("$TAG setFailResponse ")
        return when (response.code()) {
            401 -> {
                GetFeuilleDeRouteResponse.Fail(error = AppConstant.TOKEN_INVALIDE)
            }

            500 -> {
                GetFeuilleDeRouteResponse.Fail(error = AppConstant.ERREUR_SERVER)
            }

            else -> {
                GetFeuilleDeRouteResponse.Fail(error = response.errorBody()?.string())
            }
        }
    }

}


sealed class GetFeuilleDeRouteResponse {
    class Success(val response: Response<FeuilleDeRoute>) : GetFeuilleDeRouteResponse()
    class Fail(val error: String?) : GetFeuilleDeRouteResponse()
    class Error(val exception: Exception, val message: String) : GetFeuilleDeRouteResponse()
}