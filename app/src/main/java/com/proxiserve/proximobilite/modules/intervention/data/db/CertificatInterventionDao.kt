package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention

/**
 * CertificatInterventionDao
 * @author Edson De Carvalho
 */
@Dao
@Entity(tableName = "certificatIntervention")
interface CertificatInterventionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(certificatIntervention: CertificatIntervention)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(certificatIntervention: List<CertificatIntervention>)
    @Query("SELECT * FROM certificatIntervention")
    suspend fun getAllCertificatIntervention(): List<CertificatIntervention>
    @Query("SELECT * FROM certificatIntervention WHERE id =:id")
    suspend fun getCertificatInterventionCiById(id: String): CertificatIntervention?
    @Update
    suspend fun updateCertificatIntervention(certificatIntervention: CertificatIntervention)
    @Delete
    suspend fun deleteCertificatIntervention(certificatIntervention: CertificatIntervention)
    @Query("DELETE FROM certificatIntervention")
    suspend fun deleteAllCertificatIntervention()
}