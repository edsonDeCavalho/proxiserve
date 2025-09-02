package com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases

/**
 * Created by dloriot on 26/08/2024.
 */
data class HistoriqueUseCases(
    val getHistoriques: GetHistoriques,
    val getHistoriqueByLieuCode: GetHistoriqueByLieuCode,
    val getHistoriqueByInterventionCode: GetHistoriqueByInterventionCode,
    val insertHistorique: InsertHistorique,
    val insertAllHistoriques: InsertAllHistoriques,
    val deleteHistorique: DeleteHistorique,
)
