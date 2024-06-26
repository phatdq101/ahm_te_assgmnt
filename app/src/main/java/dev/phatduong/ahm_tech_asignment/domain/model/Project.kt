package dev.phatduong.ahm_tech_asignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Project(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String? = null,
    val description: String? = null,
    val visibility: String? = null,
    @SerializedName("stargazers_count")
    val starCount: Int? = null,
    @SerializedName("forks_count")
    val forkCount: Int? = null,
    val language: String? = null,
)
