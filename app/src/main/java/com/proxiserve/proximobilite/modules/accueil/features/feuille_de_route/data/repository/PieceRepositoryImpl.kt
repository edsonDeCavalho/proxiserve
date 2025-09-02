package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.PieceDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PieceRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */

class PieceRepositoryImpl(
    private val dao: PieceDao
): PieceRepository {

    override suspend fun getPieces(): Flow<List<Piece>> {
        return dao.getPieces()
    }

    override suspend fun getPieceById(id: String): Piece? {
        return dao.getPieceById(id = id)
    }

    override suspend fun insertPiece(piece: Piece) {
        return dao.insertPiece(piece = piece)
    }

    override suspend fun deletePiece() {
        return dao.deletePiece()
    }

}