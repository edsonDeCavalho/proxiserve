package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.PieceCi


/**
 * PieceCiDao
 * @author Edson De Carvalho
 */
@Dao
@Entity(tableName = "pieceCi")
interface PieceCiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pieceCi: PieceCi)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pieceCi: List<PieceCi>)
    @Query("SELECT * FROM pieceCi")
    suspend fun getAllPieceCi(): List<PieceCi>
    @Query("SELECT * FROM pieceCi WHERE id =:id")
    suspend fun getPieceCiById(id: String): PieceCi?
    @Update
    suspend fun updatePieceCi(pieceCi: PieceCi)
    @Delete
    suspend fun deletePieceCi(pieceCi: PieceCi)
    @Query("DELETE FROM pieceCi")
    suspend fun deleteAllPieceCi()
}