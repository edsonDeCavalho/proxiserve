package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface InterventionDao {

    @Query("SELECT * FROM intervention")
    fun getInterventions(): Flow<List<Intervention>>

    @Query("SELECT * FROM intervention WHERE interventionCode = :interventionCode")
    suspend fun getInterventionById(interventionCode: String): Intervention?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntervention(intervention: Intervention)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInterventions(list: List<Intervention>)

    @Delete
    suspend fun deleteIntervention(intervention: Intervention)

    @Query("DELETE FROM intervention")
    suspend fun deleteAllIntervention()

}