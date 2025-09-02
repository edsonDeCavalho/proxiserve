package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case

/**
 * Created by dloriot on 13/08/2024.
 */

data class ModeAdminUseCases(
    val loginAlias: LoginAliasUseCase,
    val loginRemoteAlias: LoginRemoteAliasUseCase,
    val modeAlias: ModeAliasUseCase,
    val validerAlias: ValiderAliasUseCase
)