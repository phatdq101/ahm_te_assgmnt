package dev.phatduong.ahm_tech_asignment.data.data_sources.remote

import dev.phatduong.ahm_tech_asignment.domain.model.Project
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectRemoteAPI {
    @GET("repos")
    suspend fun getProjects(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<Project>
}