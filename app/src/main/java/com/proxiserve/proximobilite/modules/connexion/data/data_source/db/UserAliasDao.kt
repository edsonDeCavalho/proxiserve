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
 * Created by dloriot on 13/08/2024.
 */
@Dao
interface UserAliasDao {

    @Query("SELECT * FROM user")
    fun getUsersAlias(): Flow<List<UserAlias>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserAliasById(id: String): UserAlias?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAlias(user: UserAlias)

    @Query("DELETE FROM useralias")
    suspend fun deleteUserAlias()
}