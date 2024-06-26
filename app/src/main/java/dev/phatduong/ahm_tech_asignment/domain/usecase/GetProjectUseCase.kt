package dev.phatduong.ahm_tech_asignment.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
    private val pager: Pager<Int, Project>
) {

    operator fun invoke(): Flow<PagingData<Project>> {
        return pager.flow
    }
}