package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.connexion.domain.model.User

/**
 * Created by dloriot on 23/08/2024.
 */
@Dao
interface FeuilleDeRouteDao {
    @Query("SELECT * FROM feuilleDeRoute")
    suspend fun getFeuilleDeRoute(): FeuilleDeRoute

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeuilleDeRoute(feuilleDeRoute: FeuilleDeRoute)

    @Query("DELETE FROM feuillederoute")
    suspend fun deleteFeuilleDeRoute()
}