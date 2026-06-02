package com.example.manejobugs.ui.screens.createbug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manejobugs.data.repository.BugRepository
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import com.example.manejobugs.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateBugViewModel(private val repository: BugRepository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Unit>?>(null)
    val state = _state.asStateFlow()

    fun createBug(
        title: String,
        description: String,
        severity: Severity,
        priority: Priority,
        status: BugStatus,
        affectedSystem: String,
        category: BugCategory
    ) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repository.createBug(
                title = title,
                description = description,
                severity = severity,
                priority = priority,
                status = status,
                affectedSystem = affectedSystem,
                category = category
            )
            _state.value = UiState.Success(Unit)
        }
    }
}
