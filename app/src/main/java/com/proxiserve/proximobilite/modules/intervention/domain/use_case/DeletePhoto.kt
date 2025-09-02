package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository

/**
 * GetPhotoById Use case
 * @author Edson De Carvalho
 */
data class DeletePhoto(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo) {
         repository.deletePhoto(photo)
    }
}
