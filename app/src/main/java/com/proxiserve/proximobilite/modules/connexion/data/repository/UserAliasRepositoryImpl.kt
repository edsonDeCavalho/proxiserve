package com.proxiserve.proximobilite.modules.connexion.data.repository

import com.proxiserve.proximobilite.modules.connexion.data.data_source.db.UserAliasDao
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserAliasResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserProfileRemoteDataSource
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserAliasState
import timber.log.Timber

/**
 * Created by dloriot on 13/08/2024.
 * Par rapport à UserRepository dans le domain layer, on va ici préciser où les data doivent
 * être récupérées (db, api, pref, etc...)
 */
class UserAliasRepositoryImpl(
    private val dao: UserAliasDao,
    private val remote: UserProfileRemoteDataSource,
    private val userAliasState: UserAliasState,

    ) : UserAliasRepository {

    private val TAG = "[UserRepositoryImpl]"

    override suspend fun getUserAliasState(): UserAliasState {
        Timber.i("$TAG getUserState name %s", userAliasState.userAliasname)
        return userAliasState
    }

    override suspend fun getUserAliasByLogin(login: String): UserAlias? {
        Timber.i("$TAG getUserAliasByLogin login %s", login)

        return dao.getUserAliasById(login)
    }

    override suspend fun getUserAliasById(id: String): UserAlias? {
        return dao.getUserAliasById(id = id)
    }

    override suspend fun getRemoteUserAlias(login: String, token: String): GetUserAliasResponse {
        Timber.i("$TAG getRemoteUserAlias | login => $login | token => $token")
        return remote.getUserAliasProfile(login = login, token = "Bearer $token")
    }

    override suspend fun insertUserAlias(userAlias: UserAlias) {
        Timber.i("$TAG insertUserAlias user %s", userAlias.login)
        return dao.insertUserAlias(userAlias)
    }

    override suspend fun deleteUserAlias() {
        Timber.i("$TAG deleteUser user ")
        return dao.deleteUserAlias()
    }


}