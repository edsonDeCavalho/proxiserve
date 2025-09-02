package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PieceRepository

/**
 * Created by dloriot on 26/08/2024.
 */
data class InsertPiece(
    private val repository: PieceRepository
) {
    suspend operator fun invoke(piece: Piece) {
        return repository.insertPiece(piece = piece)
    }
}
