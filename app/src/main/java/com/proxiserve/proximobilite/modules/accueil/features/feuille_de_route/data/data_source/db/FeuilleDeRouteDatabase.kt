package com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.FeuilleDeRouteDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.data.data_source.HistoriqueDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.InterventionDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.OperationDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.data.data_source.AppareilDao
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.dao.PieceDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.data.data_source.db.dao.PrestationDao
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.model.Appareil
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.FeuilleDeRoute
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.model.Historique
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Intervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Operation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Piece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.model.Prestation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.AppareilConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.AppareilHistoriqueConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.HistoriqueConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.InterventionConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.OperationConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.PieceConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.PrestationConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.PrestationHistoriqueConverter
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.utils.converters.StringConverter
import com.proxiserve.proximobilite.modules.intervention.data.db.AppareilCiDao
import com.proxiserve.proximobilite.modules.intervention.data.db.CertificatInterventionDao
import com.proxiserve.proximobilite.modules.intervention.data.db.OperationCiDao
import com.proxiserve.proximobilite.modules.intervention.data.db.PhotoDao
import com.proxiserve.proximobilite.modules.intervention.data.db.PieceCiDao
import com.proxiserve.proximobilite.modules.intervention.data.db.PrestationCiDao
import com.proxiserve.proximobilite.modules.intervention.domain.model.AppareilCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.CertificatIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.model.OperationCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.Photo
import com.proxiserve.proximobilite.modules.intervention.domain.model.PieceCi
import com.proxiserve.proximobilite.modules.intervention.domain.model.PrestationCi
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.AppareilCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.OperationCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.PieceCiConverter
import com.proxiserve.proximobilite.modules.intervention.domain.utlis.converters.PrestationCiConverter

/**
 * Created by dloriot on 23/08/2024.
 */
@Database(
    entities = [
        FeuilleDeRoute::class,
        Intervention::class,
        Historique::class,
        Appareil::class,
        Operation::class,
        Piece::class,
        Prestation::class,
        AppareilCi::class,
        CertificatIntervention::class,
        OperationCi::class,
        PieceCi::class,
        PrestationCi::class,
        Photo::class,
    ],
    version = 2
)
@TypeConverters(
    InterventionConverter::class,
    HistoriqueConverter::class,
    OperationConverter::class,
    PieceConverter::class,
    PrestationConverter::class,
    PrestationHistoriqueConverter::class,
    AppareilConverter::class,
    AppareilHistoriqueConverter::class,
    StringConverter::class,
    AppareilCiConverter::class,
    OperationCiConverter::class,
    PieceCiConverter::class,
    PrestationCiConverter::class,
)
abstract class FeuilleDeRouteDatabase: RoomDatabase() {

    abstract val feuilleDeRouteDao: FeuilleDeRouteDao
    abstract val interventionDao: InterventionDao
    abstract val historiqueDao: HistoriqueDao
    abstract val appareilDao: AppareilDao
    abstract val operationDao: OperationDao
    abstract val pieceDao: PieceDao
    abstract val prestationDao: PrestationDao
    //Ci
    abstract val appareilCiDao : AppareilCiDao
    abstract val certificatInterventionDao : CertificatInterventionDao
    abstract val operationCiDao : OperationCiDao
    abstract val pieceCiDao : PieceCiDao
    abstract val prestationCiDao : PrestationCiDao
    abstract val photoDao : PhotoDao
    companion object {
        const val DATABASE_NAME = "feuilles_de_route_db"

    }
}