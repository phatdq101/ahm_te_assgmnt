package dev.phatduong.ahm_tech_asignment.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.phatduong.ahm_tech_asignment.BuildConfig
import dev.phatduong.ahm_tech_asignment.data.data_sources.local.ProjectDatabase
import dev.phatduong.ahm_tech_asignment.data.data_sources.remote.ProjectRemoteAPI
import dev.phatduong.ahm_tech_asignment.data.data_sources.remote.ProjectRemoteMediator
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.utils.Constants
import dev.phatduong.ahm_tech_asignment.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositoryRemote(retrofit: Retrofit): ProjectRemoteAPI {
        return retrofit.create(ProjectRemoteAPI::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideProjectRepository(projectPagingSource: ProjectPagingSource): ProjectRepository {
//        return ProjectRepositoryImpl(projectPagingSource)
//    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ProjectDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ProjectDatabase::class.java,
            ProjectDatabase.DATABASE_NAME
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideProjectPager(
        projectDatabase: ProjectDatabase,
        projectRemoteAPI: ProjectRemoteAPI
    ): Pager<Int, Project> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                initialLoadSize = Constants.PAGE_SIZE
            ),
            remoteMediator = ProjectRemoteMediator(
                database = projectDatabase,
                remoteAPI = projectRemoteAPI
            ),
            pagingSourceFactory = {
                projectDatabase.projectDao.pagingSource()
            }
        )
    }

}