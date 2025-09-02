package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository

/**
 * GetAllPhotos Use case
 * @author Edson De Carvalho
 */
data class GetAllPhotos(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo) : List<Photo>? {
        return repository.getAllPhotos()
    }
}


