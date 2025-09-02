package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository

/**
 * Created by dloriot on 13/08/2024.
 */
class ValiderAliasUseCase(
    private val repository: UserAliasRepository
) {
    suspend operator fun invoke(id: String) {
        repository.getUserAliasByLogin(id)
    }
}