package com.proxiserve.proximobilite.modules.intervention.domain.use_case

import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * InserPhoto Use case
 * @author Edson De Carvalho
 */
data class InserPhoto(
    private val repository: PhotoRepository
) {
     suspend operator fun invoke(photo: Photo) {
         repository.insertPhoto(photo)
     }
}