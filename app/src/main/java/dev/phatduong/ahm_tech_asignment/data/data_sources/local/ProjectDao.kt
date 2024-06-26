package dev.phatduong.ahm_tech_asignment.data.data_sources.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.phatduong.ahm_tech_asignment.domain.model.Project

@Dao
interface ProjectDao {
    @Upsert
    suspend fun insertProjects(projects: List<Project>)

    @Query("SELECT * FROM project")
    fun pagingSource(): PagingSource<Int, Project>

    @Query("DELETE FROM project")
    suspend fun clearAll()
}