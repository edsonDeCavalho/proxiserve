package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */

interface PieceRepository {

    suspend fun getPieces(): Flow<List<Piece>>
    suspend fun getPieceById(id: String): Piece?
    suspend fun insertPiece(piece: Piece)
    suspend fun deletePiece()

}