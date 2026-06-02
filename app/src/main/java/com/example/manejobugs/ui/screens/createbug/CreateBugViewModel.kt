package com.example.manejobugs.ui.screens.createbug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manejobugs.data.repository.BugRepository
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import com.example.manejobugs.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateBugViewModel(private val repository: BugRepository) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Unit>?>(null)
    val state = _state.asStateFlow()

    fun createBug() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repository.createBug(
                title = "New bug",
                description = "Created from the mobile PoC form.",
                severity = Severity.MEDIUM,
                priority = Priority.MEDIUM,
                affectedSystem = "General",
                category = BugCategory.UI
            )
            _state.value = UiState.Success(Unit)
        }
    }
}
