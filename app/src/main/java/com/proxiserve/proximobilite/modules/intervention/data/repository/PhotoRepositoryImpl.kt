package com.proxiserve.proximobilite.modules.intervention.data.repository

import com.proxiserve.proximobilite.modules.intervention.data.db.CertificatInterventionDao
import com.proxiserve.proximobilite.modules.intervention.data.db.PhotoDao
import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository
import timber.log.Timber

/**
 * PhotoImplementation
 * @Edson De Carvalho
 */
class PhotoRepositoryImpl(
    private val dao: PhotoDao,
): PhotoRepository{
    private val TAG = "[PhotoImplementation]"
    override suspend fun getAllPhotos(): List<Photo>? {
        Timber.i("$TAG getAllPhotos")
        val result = dao.getAllPhotos()
        if(result.isNotEmpty()) Timber.e("$TAG getAllPhotos pas de photos")
        return result
    }
    override suspend fun getPhotoById(id: String): Photo? {
        Timber.i("$TAG getPhotoById")
        return dao.getPhotoById(id = id)
    }

    override suspend fun getPhotoFromCertificatIntervention(id: String): List<Photo>? {
        Timber.i("$TAG getPhotoFromCertificatIntervention")
        return dao.getPhotosFromCetificatIntervention(id_certificat = id)
    }

    override suspend fun updatePhoto(photo: Photo) {
        Timber.i("$TAG updatePhoto")
        dao.updatePhoto(photo)
    }

    override suspend fun insertPhoto(photo: Photo) {
        Timber.i("$TAG insertPhoto")
        dao.insert(photo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        Timber.i("$TAG deletePhoto")
        dao.deletePhoto(photo)
    }

}