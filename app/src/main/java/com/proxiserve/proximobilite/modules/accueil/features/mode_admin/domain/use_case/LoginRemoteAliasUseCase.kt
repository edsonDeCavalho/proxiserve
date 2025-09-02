package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository

/**
 * Created by dloriot on 23/09/2024.
 */
class LoginRemoteAliasUseCase(
    val repository: UserAliasRepository
) {
    val TAG = "[GetRemoteFeuilleDeRoute]"
    suspend operator fun invoke(login: String, token: String): GetUserAliasResponse {
        return repository.getRemoteUserAlias(login = login, token = token)
    }
}
