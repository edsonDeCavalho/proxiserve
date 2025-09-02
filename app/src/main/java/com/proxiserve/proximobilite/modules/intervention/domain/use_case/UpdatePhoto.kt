package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository

/**
 * UpdatePhoto Use case
 * @author Edson De Carvalho
 */
data class UpdatePhoto(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo) {
        repository.updatePhoto(photo)
    }
}