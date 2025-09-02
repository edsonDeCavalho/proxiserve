package com.proxiserve.proximobilite.modules.intervention.domain.repository

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo

/**
 * PhotoRepository
 * @author Edson De CARVALHO
 */
interface PhotoRepository {
    suspend fun getAllPhotos(): List<Photo>?

    suspend fun getPhotoById(id: String): Photo?
    suspend fun getPhotoFromCertificatIntervention(id: String): List<Photo>?

    suspend fun updatePhoto(photo: Photo)

    suspend fun insertPhoto(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}