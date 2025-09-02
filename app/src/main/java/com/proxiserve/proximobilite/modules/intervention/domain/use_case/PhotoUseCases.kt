package com.proxiserve.proximobilite.modules.intervention.domain.use_case
/**
 * PhotoUseCases
 * @author Edson De Carvalho
 */
class PhotoUseCases (
    val deletePhoto: DeletePhoto,
    val getAllPhotos: GetAllPhotos,
    val getPhotById: GetPhotoById,
    val getPhotoFromCertificatIntervention: GetPhotoFromCertificatIntervention,
    val insertPhoto: InserPhoto,
    val updatePhoto: UpdatePhoto,
)