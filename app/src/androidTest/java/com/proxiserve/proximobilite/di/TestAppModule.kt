package com.proxiserve.proximobilite.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.proxiserve.proximobilite.modules.connexion.data.repository.UserAliasRepositoryImpl
import com.proxiserve.proximobilite.modules.connexion.data.repository.UserRepositoryImpl
import com.proxiserve.proximobilite.modules.connexion.data.data_source.db.UserDatabase
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.db.FeuilleDeRouteDatabase
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.FeuilleDeRouteApiService
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.data_source.remote.FeuilleDeRouteRemoteDataSource
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserApiService
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.repository.AppareilRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.FeuilleDeRouteRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository.FeuilleDeRouteRepositoryImpl
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.repository.HistoriqueRepository
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.data.repository.HistoriqueRepositoryImpl
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.OperationRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository.OperationRepositoryImpl
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PieceRepository
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository.PieceRepositoryImpl
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.data.repository.PrestationRepositoryImpl
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.data.repository.AppareilRepositoryImpl
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.repository.PrestationRepository
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.AppareilUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.DeleteAppareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.GetAppareilById
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.GetAppareils
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.InsertAppareil
import com.proxiserve.proximobilite.modules.detail_intervention.features.appareil.domain.use_cases.InsertAppareils
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.DeleteFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.FeuilleDeRouteUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.GetFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.feuille_de_route.GetRemoteFeuilleDeRoute
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.InsertAllHistoriques
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.DeleteHistorique
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.GetHistoriqueByInterventionCode
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.GetHistoriqueByLieuCode
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.GetHistoriques
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.HistoriqueUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.historique.domain.use_cases.InsertHistorique
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.DeleteAllIntervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.DeleteIntervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.GetInterventionById
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.GetInterventions
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InsertAllInterventions
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InsertIntervention
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.intervention.InterventionUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.DeleteOperation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.GetOperationById
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.GetOperations
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.InsertOperation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.operation.OperationUseCases
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.DeletePiece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.GetPieceById
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.GetPieces
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.InsertPiece
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.domain.use_case.piece.PieceUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.DeletePrestation
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.GetPrestationById
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.GetPrestations
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.InsertPrestation
import com.proxiserve.proximobilite.modules.accueil.features.feuille_de_route.presentation.state.GetFakeFeuilleDeRoute
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.LoginAliasUseCase
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.LoginRemoteAliasUseCase
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.ModeAdminUseCases
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.ModeAliasUseCase
import com.proxiserve.proximobilite.modules.accueil.features.mode_admin.domain.use_case.ValiderAliasUseCase
import com.proxiserve.proximobilite.modules.intervention.domain.repository.CertificatInterventionRepository
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.CertificatInterventionUseCases
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.InsertCertificatIntervention
import com.proxiserve.proximobilite.modules.connexion.data.data_source.remote.UserProfileRemoteDataSource
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.DeleteUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.DeleteUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetRemoteUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetRemoteUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetRemoteUserByLogin
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAliasById
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserAliasState
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserById
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.GetUserState
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.InsertUser
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.InsertUserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserAliasUseCases
import com.proxiserve.proximobilite.modules.connexion.domain.use_case.UserUseCases
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserAliasState
import com.proxiserve.proximobilite.modules.connexion.presentation.state.UserState
import com.proxiserve.proximobilite.modules.detail_intervention.data.repository.InterventionRepositoryImpl
import com.proxiserve.proximobilite.modules.detail_intervention.domain.repository.InterventionRepository
import com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case.DetailInterventionUseCases
import com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case.SendCiAbsent
import com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case.SendCiInjustifie
import com.proxiserve.proximobilite.modules.detail_intervention.domain.use_case.SendCiRefus
import com.proxiserve.proximobilite.modules.detail_intervention.features.prestation.domain.use_cases.PrestationUseCases
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.DeleteCertificationIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.GetAllCertificationIntervention
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.GetCertificatInterventionById
import com.proxiserve.proximobilite.modules.intervention.domain.use_case.UpdateCetificatIntervention
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/**
 * Created by dloriot on 14/11/2024.
 */
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    // Database
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            UserDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideFeuilleDeRouteDatabase(app: Application): FeuilleDeRouteDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            FeuilleDeRouteDatabase::class.java
        ).build()
    }

    // Remote
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val client: OkHttpClient.Builder = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor = interceptor)
        }

        return Retrofit.Builder()
            .baseUrl("https://proximobiliterec.proxiserve.fr/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    fun provideFeuilleDeRouteApiService(retrofit: Retrofit): FeuilleDeRouteApiService {
        return retrofit.create(FeuilleDeRouteApiService::class.java)
    }

    // Repository
    @Provides
    @Singleton
    fun provideUserRepository(
        db: UserDatabase,
        remote: UserProfileRemoteDataSource,
        userState: UserState
    ): UserRepository {
        return UserRepositoryImpl(db.userDao, remote, userState)
    }

    @Provides
    @Singleton
    fun provideUserAliasRepository(
        db: UserDatabase,
        remote: UserProfileRemoteDataSource,
        userAliasState: UserAliasState
    ): UserAliasRepository {
        return UserAliasRepositoryImpl(db.userAliasDao, remote, userAliasState)
    }

    @Provides
    @Singleton
    fun provideFeuilleDeRouteRepository(
        db: FeuilleDeRouteDatabase,
        remote: FeuilleDeRouteRemoteDataSource
    ) : FeuilleDeRouteRepository {
        return FeuilleDeRouteRepositoryImpl(db.feuilleDeRouteDao, remote)
    }

    @Provides
    @Singleton
    fun provideInterventionRepository(
        db: FeuilleDeRouteDatabase
    ) : InterventionRepository {
        return InterventionRepositoryImpl(db.interventionDao)
    }

    @Provides
    @Singleton
    fun provideHistoriqueRepository(
        db: FeuilleDeRouteDatabase
    ) : HistoriqueRepository {
        return HistoriqueRepositoryImpl(db.historiqueDao)
    }

    @Provides
    @Singleton
    fun provideAppareilRepository(
        db: FeuilleDeRouteDatabase
    ) : AppareilRepository {
        return AppareilRepositoryImpl(db.appareilDao)
    }

    @Provides
    @Singleton
    fun provideOperationRepository(
        db: FeuilleDeRouteDatabase
    ) : OperationRepository {
        return OperationRepositoryImpl(db.operationDao)
    }

    @Provides
    @Singleton
    fun providePieceRepository(
        db: FeuilleDeRouteDatabase
    ) : PieceRepository {
        return PieceRepositoryImpl(db.pieceDao)
    }

    @Provides
    @Singleton
    fun providePrestationRepository(
        db: FeuilleDeRouteDatabase
    ) : PrestationRepository {
        return PrestationRepositoryImpl(db.prestationDao)
    }


    // UseCase
    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            insertUser = InsertUser(repository),
            getUser = GetUser(repository),
            getUserById = GetUserById(repository),
            getRemoteUser = GetRemoteUser(repository),
            getRemoteUserByLogin = GetRemoteUserByLogin(repository),
            deleteUser = DeleteUser(repository),
            getUserState = GetUserState(repository)
        )
    }
    @Provides
    @Singleton
    fun provideCertificatInterventionCases(repository: CertificatInterventionRepository): CertificatInterventionUseCases {
        return CertificatInterventionUseCases(
            insertCertificatIntervention = InsertCertificatIntervention(repository),
            getAllCertificationIntervention = GetAllCertificationIntervention(repository),
            getCertificatInterventionById =  GetCertificatInterventionById(repository),
            updateCetificatIntervention = UpdateCetificatIntervention(repository),
            deleteCertificationIntervention = DeleteCertificationIntervention(repository)
        )
    }
    @Provides
    @Singleton
    fun provideUserAliasUseCases(repository: UserAliasRepository): UserAliasUseCases {
        return UserAliasUseCases(
            insertUserAlias = InsertUserAlias(repository),
            getUserAlias = GetUserAlias(repository),
            getUserAliasById = GetUserAliasById(repository),
            getRemoteUserAlias = GetRemoteUserAlias(repository),
            deleteUserAlias = DeleteUserAlias(repository),
            getUserAliasState = GetUserAliasState(repository)
        )
    }

    @Provides
    @Singleton
    fun provideModeAdminUseCases(repository: UserAliasRepository): ModeAdminUseCases {
        return ModeAdminUseCases(
            loginAlias = LoginAliasUseCase(repository),
            loginRemoteAlias = LoginRemoteAliasUseCase(repository),
            modeAlias = ModeAliasUseCase(repository),
            validerAlias = ValiderAliasUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFeuilleDeRouteUseCases(
        @ApplicationContext context: Context,
        repository: FeuilleDeRouteRepository,
    ): FeuilleDeRouteUseCases {
        return FeuilleDeRouteUseCases(
            getFakeFeuilleDeRoute = GetFakeFeuilleDeRoute(context),
            getFeuilleDeRoute = GetFeuilleDeRoute(repository),
            getRemoteFeuilleDeRoute = GetRemoteFeuilleDeRoute(repository),
            deleteFeuilleDeRoute = DeleteFeuilleDeRoute(repository)
        )
    }

    @Provides
    @Singleton
    fun provideInterventionUseCases(
        @ApplicationContext context: Context,
        repository: InterventionRepository
    ) : InterventionUseCases {
        return InterventionUseCases(
            getInterventions = GetInterventions(repository),
            getInterventionById = GetInterventionById(repository),
            insertIntervention = InsertIntervention(repository),
            insertAllInterventions = InsertAllInterventions(repository),
            deleteIntervention = DeleteIntervention(repository),
            deleteAllIntervention = DeleteAllIntervention(repository),
        )
    }

    @Provides
    @Singleton
    fun provideHistoriqueUseCases(
        @ApplicationContext context: Context,
        repository: HistoriqueRepository
    ) : HistoriqueUseCases {
        return HistoriqueUseCases(
            getHistoriques = GetHistoriques(repository),
            getHistoriqueByLieuCode = GetHistoriqueByLieuCode(repository),
            getHistoriqueByInterventionCode = GetHistoriqueByInterventionCode(repository),
            insertHistorique = InsertHistorique(repository),
            insertAllHistoriques = InsertAllHistoriques(repository),
            deleteHistorique = DeleteHistorique(repository),
        )
    }

    @Provides
    @Singleton
    fun provideAppareilUseCases(
        @ApplicationContext context: Context,
        repository: AppareilRepository
    ) : AppareilUseCases {
        return AppareilUseCases(
            getAppareils = GetAppareils(repository),
            getAppareilById = GetAppareilById(repository),
            insertAppareil = InsertAppareil(repository),
            insertAppareils = InsertAppareils(repository),
            deleteAppareil = DeleteAppareil(repository),
        )
    }

    @Provides
    @Singleton
    fun provideOperationUseCases(
        @ApplicationContext context: Context,
        repository: OperationRepository
    ) : OperationUseCases {
        return OperationUseCases(
            getOperations = GetOperations(repository),
            getOperationById = GetOperationById(repository),
            insertOperation = InsertOperation(repository),
            deleteOperation= DeleteOperation(repository),
        )
    }

    @Provides
    @Singleton
    fun providePieceUseCases(
        @ApplicationContext context: Context,
        repository: PieceRepository
    ) : PieceUseCases {
        return PieceUseCases(
            getPieces = GetPieces(repository),
            getPieceById = GetPieceById(repository),
            insertPiece = InsertPiece(repository),
            deletePiece = DeletePiece(repository),
        )
    }

    @Provides
    @Singleton
    fun providePrestationUseCases(
        @ApplicationContext context: Context,
        repository: PrestationRepository
    ) : PrestationUseCases {
        return PrestationUseCases(
            getPrestations = GetPrestations(repository),
            getPrestationById = GetPrestationById(repository),
            insertPrestation = InsertPrestation(repository),
            deletePrestation = DeletePrestation(repository),
        )
    }

    @Provides
    @Singleton
    fun provideDetailInterventionUseCases(
        @ApplicationContext context: Context,
        repository: InterventionRepository
    ) : DetailInterventionUseCases {
        return DetailInterventionUseCases(
            sendCiAbsent = SendCiAbsent(repository),
            sendCiRefus = SendCiRefus(repository),
            sendCiInjustifie = SendCiInjustifie(repository)
        )
    }

    // State
    @Provides
    @Singleton
    fun provideUserState() = UserState()

    @Provides
    @Singleton
    fun provideUserAliasState() = UserAliasState()

}