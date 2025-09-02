package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.AppareilCi
/**
 * AppareilCiDao
 * @author Edson De Carvalho
 */
@Dao
@Entity(tableName = "appareilCi")
interface AppareilCiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appareilCi: AppareilCi)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appareilCi: List<AppareilCi>)
    @Query("SELECT * FROM appareilCi")
    suspend fun getAllAppareilCi(): List<AppareilCi>
    @Query("SELECT * FROM appareilCi WHERE  id = :id ")
    suspend fun getAppareilCiById(id: String): AppareilCi?
    @Update
    suspend fun updateAppareilCi(appareilCi: AppareilCi)
    @Delete
    suspend fun deleteAppareilCi(appareilCi: AppareilCi)
    @Query("DELETE FROM appareilCi")
    suspend fun deleteAllAppareilCi()
}