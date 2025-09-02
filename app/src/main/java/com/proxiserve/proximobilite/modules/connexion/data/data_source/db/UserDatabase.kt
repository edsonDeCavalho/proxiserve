package com.proxiserve.proximobilite.modules.connexion.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias

/**
 * Created by dloriot on 27/06/2024.
 *
 */

@Database(
    entities = [User::class, UserAlias::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val userAliasDao: UserAliasDao

    companion object {
        const val DATABASE_NAME = "users_db"

    }
}