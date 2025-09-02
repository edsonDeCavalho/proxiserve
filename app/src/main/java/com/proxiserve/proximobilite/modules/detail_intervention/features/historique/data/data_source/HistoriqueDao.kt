package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface HistoriqueDao {

    @Query("SELECT * FROM historique")
    fun getHistoriques(): Flow<List<Historique>>

    @Query("SELECT * FROM historique WHERE lieuCode = :lieuCode")
    suspend fun getHistoriqueByLieuCode(lieuCode: String): List<Historique>?

    @Query("SELECT * FROM historique WHERE interventionCode = :interventionCode")
    suspend fun getHistoriqueByInterventionCode(interventionCode: String): Historique?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistorique(historique: Historique)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistoriques(list: List<Historique>)

    @Query("DELETE FROM historique")
    suspend fun deleteHistorique()

}