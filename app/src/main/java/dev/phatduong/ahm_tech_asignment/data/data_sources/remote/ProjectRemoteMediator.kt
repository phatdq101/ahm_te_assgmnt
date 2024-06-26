package dev.phatduong.ahm_tech_asignment.data.data_sources.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.phatduong.ahm_tech_asignment.data.data_sources.local.ProjectDatabase
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProjectRemoteMediator(
    private val database: ProjectDatabase,
    private val remoteAPI: ProjectRemoteAPI
) : RemoteMediator<Int, Project>() {

    private var currentPage = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Project>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        currentPage++
                    }
                }
            }

            val projects = remoteAPI.getProjects(
                page = loadKey,
                perPage = state.config.pageSize
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.projectDao.clearAll()
                }
                database.projectDao.insertProjects(projects)
            }

            MediatorResult.Success(
                endOfPaginationReached = projects.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}