package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece

/**
 * Created by dloriot on 26/08/2024.
 */
data class PieceUseCases(
    val getPieces: GetPieces,
    val getPieceById: GetPieceById,
    val insertPiece: InsertPiece,
    val deletePiece: DeletePiece
)
