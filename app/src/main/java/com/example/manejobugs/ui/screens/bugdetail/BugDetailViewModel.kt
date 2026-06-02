package com.example.manejobugs.ui.screens.bugdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manejobugs.data.repository.BugRepository
import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BugDetailViewModel(
    private val repository: BugRepository,
    private val bugId: String
) : ViewModel() {

    private val _bug = MutableStateFlow<Bug?>(null)
    val bug = _bug.asStateFlow()

    init {
        viewModelScope.launch {
            _bug.value = repository.getBugById(bugId)
        }
    }

    fun toggleStatus() {
        val current = _bug.value ?: return
        viewModelScope.launch {
            val nextStatus = when (current.status) {
                BugStatus.OPEN -> BugStatus.IN_PROGRESS
                BugStatus.IN_PROGRESS -> BugStatus.RESOLVED
                BugStatus.RESOLVED -> BugStatus.OPEN
                BugStatus.CLOSED -> BugStatus.OPEN
            }
            _bug.value = repository.updateBugStatus(current.id, nextStatus, current.priority)
        }
    }

    fun cyclePriority() {
        val current = _bug.value ?: return
        val nextPriority = when (current.priority) {
            Priority.URGENT -> Priority.HIGH
            Priority.HIGH -> Priority.MEDIUM
            Priority.MEDIUM -> Priority.LOW
            Priority.LOW -> Priority.URGENT
        }
        viewModelScope.launch {
            _bug.value = repository.updateBugStatus(current.id, current.status, nextPriority)
        }
    }
}
