package com.proxiserve.proximobilite.modules.connexion.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 27/06/2024.
 *
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id : String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}