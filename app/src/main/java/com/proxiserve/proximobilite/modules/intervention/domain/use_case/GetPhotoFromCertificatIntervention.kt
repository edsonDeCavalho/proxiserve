package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository

/**
 * GetPhotoFromCertificatIntervention Use case
 * @author Edson De Carvalho
 */
data class GetPhotoFromCertificatIntervention(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: String) : List<Photo>? {
        return repository.getPhotoFromCertificatIntervention(id)
    }
}