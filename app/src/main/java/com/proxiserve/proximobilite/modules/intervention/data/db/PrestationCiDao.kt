package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.PrestationCi

/**
 * PrestationCiDao
 * @author Edson De Carvalho
 */
@Dao
@Entity(tableName = "prestationCi")
interface PrestationCiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestationCi: PrestationCi)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(prestations: List<PrestationCi>)
    @Query("SELECT * FROM prestationCi")
    suspend fun getAllPrestations(): List<PrestationCi>
    @Query("SELECT * FROM prestationCi WHERE id =:id")
    suspend fun getPrestationById(id: String): PrestationCi?
    @Update
    suspend fun updatePrestation(prestationCi: PrestationCi)
    @Delete
    suspend fun deletePrestation(prestationCi: PrestationCi)
    @Query("DELETE FROM prestationCi")
    suspend fun deleteAllPrestations()
}