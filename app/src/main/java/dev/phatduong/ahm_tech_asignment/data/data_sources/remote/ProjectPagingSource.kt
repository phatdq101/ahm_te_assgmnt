package dev.phatduong.ahm_tech_asignment.data.data_sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProjectPagingSource @Inject constructor(
    private val service: ProjectRemoteAPI
) : PagingSource<Int, Project>() {
    override fun getRefreshKey(state: PagingState<Int, Project>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Project> {
        val page = params.key ?: 0
        return try {
            val response = service.getProjects(page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}