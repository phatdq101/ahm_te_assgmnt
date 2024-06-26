package dev.phatduong.ahm_tech_asignment.data.data_sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.phatduong.ahm_tech_asignment.domain.model.Project

@Database(
    entities = [Project::class],
    version = 1
)
abstract class ProjectDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "project_database"
    }

    abstract val projectDao: ProjectDao
}