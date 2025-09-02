package com.proxiserve.proximobilite.modules.connexion.domain.use_case

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class UserAliasUseCases(
    val insertUserAlias: InsertUserAlias,
    val getUserAlias: GetUserAlias,
    val getUserAliasById: GetUserAliasById,
    val getRemoteUserAlias: GetRemoteUserAlias,
    val deleteUserAlias: DeleteUserAlias,
    val getUserAliasState: GetUserAliasState
)