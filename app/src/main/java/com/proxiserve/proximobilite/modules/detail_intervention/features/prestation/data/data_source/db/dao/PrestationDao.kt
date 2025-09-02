package com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.data.data_source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface PrestationDao {

    @Query("SELECT * FROM prestation")
    fun getPrestations(): Flow<List<Prestation>>

    @Query("SELECT * FROM prestation WHERE prestationCode = :id")
    suspend fun getPrestationById(id: String): Prestation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrestation(prestation: Prestation)

    @Query("DELETE FROM prestation")
    suspend fun deletePrestation()

}