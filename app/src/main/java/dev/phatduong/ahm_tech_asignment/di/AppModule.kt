package dev.phatduong.ahm_tech_asignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.phatduong.ahm_tech_asignment.BuildConfig
import dev.phatduong.ahm_tech_asignment.data.data_sources.remote.ProjectPagingSource
import dev.phatduong.ahm_tech_asignment.data.data_sources.remote.ProjectRemoteAPI
import dev.phatduong.ahm_tech_asignment.data.repository.ProjectRepositoryImpl
import dev.phatduong.ahm_tech_asignment.domain.repository.ProjectRepository
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

    @Provides
    @Singleton
    fun provideProjectRepository(projectPagingSource: ProjectPagingSource): ProjectRepository {
        return ProjectRepositoryImpl(projectPagingSource)
    }



}