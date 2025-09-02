package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece

import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PieceRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import kotlinx.coroutines.flow.Flow

/**
 * Created by dloriot on 26/08/2024.
 */
data class GetPieces(
    private val repository: PieceRepository
) {
    suspend operator fun invoke(): Flow<List<Piece>> {
        return repository.getPieces()
    }
}
