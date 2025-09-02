package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository

/**
 * GetPhotoById Use case
 * @author Edson De Carvalho
 */
data class GetPhotoById(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: String) : Photo? {
        return repository.getPhotoById(id)
    }
}

