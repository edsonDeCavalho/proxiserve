package com.proxiserve.proximobilite.modules.connexion.domain.use_case

/**
 * Created by dloriot on 11/07/2024.
 *
 */
data class UserUseCases(
    val insertUser: InsertUser,
    val getUser: GetUser,
    val getUserById: GetUserById,
    val getRemoteUser: GetRemoteUser,
    val getRemoteUserByLogin: GetRemoteUserByLogin,
    val deleteUser: DeleteUser,
    val getUserState: GetUserState,
)