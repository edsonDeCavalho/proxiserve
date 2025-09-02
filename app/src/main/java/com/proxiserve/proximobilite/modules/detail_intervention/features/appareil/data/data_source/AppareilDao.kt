package com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface AppareilDao {

    @Query("SELECT * FROM appareil WHERE appareilCode IN (:idList)")
    suspend fun getAppareils(idList: List<String>): List<Appareil>

    @Query("SELECT * FROM appareil WHERE appareilCode = :id")
    suspend fun getAppareilById(id: String): Appareil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppareil(appareil: Appareil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppareils(list: List<Appareil>)

    @Query("DELETE FROM appareil")
    suspend fun deleteAppareil()

}