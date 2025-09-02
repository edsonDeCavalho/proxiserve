package com.proxiserve.proximobilite.modules.connexion.data.repository

import com.proxiserve.proximobilite.modules.connexion.data.data_source.db.UserDao
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.GetUserResponse
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserApiService
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserProfileRemoteDataSource
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import retrofit2.Response
import timber.log.Timber

/**
 * Created by dloriot on 27/06/2024.
 * Par rapport à UserRepository dans le domain layer, on va ici préciser où les data doivent
 * être récupérées (db, api, pref, etc...)
 *
 */
class UserRepositoryImpl(
    private val dao: UserDao,
    private val remote: UserProfileRemoteDataSource,
    private val userState: UserState
) : UserRepository {

    private val TAG = "[UserRepositoryImpl]"

    override suspend fun getUserState(): UserState {
        Timber.i("$TAG getUserState name %s", userState.username)
        return userState
    }

    /*
     * Il n'y a qu'un USER dans l'application. A chaque enregistrement de User
     * on efface la table donc getUser appelle getUsers et on récupère la seule
     * et unique ligne
     * */
    override suspend fun getUser(): User? {
        Timber.i("$TAG getUser")
        val result = dao.getUser()
        if (result.isNotEmpty())
            return dao.getUser()[0]
        Timber.e("$TAG getUser pas de User")
        return null
    }

    override suspend fun getUserById(id: String): User? {
        Timber.i("$TAG getRemoteUser | id => $id")
        return dao.getUserById(id = id)
    }

    override suspend fun getRemoteUser(id: String, token: String): GetUserResponse {
        Timber.i("$TAG getRemoteUser | id => $id | token => $token")
        return remote.getUserProfile(id = id, token = "Bearer $token")
    }

    override suspend fun getRemoteUserByLogin(login: String, token: String): GetUserResponse {
        Timber.i("$TAG getRemoteUser | login => $login | token => $token")
        return remote.getUserProfileByLogin(login = login, token = "Bearer $token")
    }

    override suspend fun insertUser(user: User) {
        Timber.i("$TAG insertUser user %s", user.login)
        return dao.insertUser(user)
    }

    /*
     * Il n'y a qu'un USER dans l'application donc delete appelle deleteAllUsers dans le dao
     * */
    override suspend fun deleteUser() {
        Timber.i("$TAG deleteUser")
        return dao.deleteUser()
    }


}