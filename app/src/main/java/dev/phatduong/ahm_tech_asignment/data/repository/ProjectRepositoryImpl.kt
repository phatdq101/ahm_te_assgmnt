package dev.phatduong.ahm_tech_asignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.phatduong.ahm_tech_asignment.data.data_sources.remote.ProjectPagingSource
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.domain.repository.ProjectRepository
import dev.phatduong.ahm_tech_asignment.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectPagingSource: ProjectPagingSource
) : ProjectRepository {
    override suspend fun getProjects(): Flow<PagingData<Project>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = Constants.PAGE_SIZE
            )
        ) {
            projectPagingSource
        }.flow
    }
}