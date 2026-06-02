package com.example.manejobugs.ui.screens.buglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manejobugs.ui.UiState
import com.example.manejobugs.data.repository.BugRepository
import com.example.manejobugs.domain.model.Bug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for the Bug List screen.
 *
 * EVENT-BASED REACTIVITY:
 * This ViewModel collects the Flow exposed by the repository. Every time a bug is
 * created or updated, the repository emits a new sorted list through the Flow.
 * The ViewModel receives it and updates _bugsState, which triggers recomposition
 * in BugListScreen. No manual refresh or screen reload is needed.
 */
class BugListViewModel(private val repository: BugRepository) : ViewModel() {

    private val _bugsState = MutableStateFlow<UiState<List<Bug>>>(UiState.Loading)
    val bugsState = _bugsState.asStateFlow()

    init {
        observeBugs()
    }

    private fun observeBugs() {
        viewModelScope.launch {
            repository.observeBugs()
                .catch { e -> _bugsState.value = UiState.Error(e.message ?: "Unknown error") }
                .collect { bugs -> _bugsState.value = UiState.Success(bugs) }
        }
    }
}