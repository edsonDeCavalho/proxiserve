package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface OperationDao {

    @Query("SELECT * FROM operation")
    fun getOperations(): Flow<List<Operation>>

    @Query("SELECT * FROM operation WHERE id = :id")
    suspend fun getOperationById(id: String): Operation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: Operation)

    @Query("DELETE FROM operation")
    suspend fun deleteOperation()

}