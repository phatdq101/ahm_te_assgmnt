package dev.phatduong.ahm_tech_asignment.domain.usecase

import androidx.paging.PagingData
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Project>> {
        return repository.getProjects()
    }
}