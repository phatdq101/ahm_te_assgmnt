package dev.phatduong.ahm_tech_asignment.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phatduong.ahm_tech_asignment.domain.model.Project
import dev.phatduong.ahm_tech_asignment.domain.usecase.GetProjectUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProjectUseCase: GetProjectUseCase
) : ViewModel() {
    private val _projects: MutableStateFlow<PagingData<Project>> =
        MutableStateFlow(PagingData.empty())
    val projects = _projects.asStateFlow()

    init {
        viewModelScope.launch {
            getProjectUseCase().cachedIn(viewModelScope).collect {
                _projects.value = it
            }
        }
    }
}