package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi
/**
 * OperationCiDao
 * @author Edson De Carvalho
 */
@Dao
@Entity(tableName = "operationCi")
interface OperationCiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operationCi: OperationCi)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(operationCi: List<OperationCi>)
    @Query("SELECT * FROM operationCi")
    suspend fun getAllOperationCi(): List<OperationCi>
    @Query("SELECT * FROM operationCi WHERE id =:id")
    suspend fun getOperationCiById(id: String): OperationCi?
    @Update
    suspend fun updateOperationCi(operationCi: OperationCi)
    @Delete
    suspend fun deleteOperationCi(operationCi: OperationCi)
    @Query("DELETE FROM operationCi")
    suspend fun deleteAllOperationCi()
}