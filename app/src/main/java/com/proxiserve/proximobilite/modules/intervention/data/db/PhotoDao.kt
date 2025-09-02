package com.proxiserve.proximobilite.modules.intervention.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo


@Dao
@Entity(tableName = "photo")
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photo: List<Photo>)
    @Query("SELECT * FROM photo")
    suspend fun getAllPhotos(): List<Photo>
    @Query("SELECT * FROM photo WHERE id =:id")
    suspend fun getPhotoById(id: String): Photo?
    @Query("SELECT * FROM photo WHERE id_certificat =:id_certificat")
    suspend fun getPhotosFromCetificatIntervention(id_certificat: String): List<Photo>?
    @Update
    suspend fun updatePhoto(photo: Photo)
    @Delete
    suspend fun deletePhoto(photo: Photo)
    @Query("DELETE FROM photo")
    suspend fun deleteAllPhotos()
}

