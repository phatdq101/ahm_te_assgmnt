package dev.phatduong.ahm_tech_asignment.domain.repository

import androidx.paging.PagingData
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun getProjects(): Flow<PagingData<Project>>
}