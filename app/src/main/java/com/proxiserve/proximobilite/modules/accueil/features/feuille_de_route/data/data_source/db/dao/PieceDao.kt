package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 23/08/2024.
 *
 */
@Dao
interface PieceDao {

    @Query("SELECT * FROM piece")
    fun getPieces(): Flow<List<Piece>>

    @Query("SELECT * FROM piece WHERE pieceCode = :id")
    suspend fun getPieceById(id: String): Piece?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPiece(piece: Piece)

    @Query("DELETE FROM piece")
    suspend fun deletePiece()

}