package com.example.manejobugs.data.repository

import com.example.manejobugs.data.mock.mockBugs
import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant

/**
 * Mock implementation of BugRepository.
 *
 * Holds all bugs in a MutableStateFlow so that any create or update operation
 * immediately emits a new list to all active collectors (BugListViewModel).
 * This is the event-based reactivity mechanism: no manual refresh needed.
 *
 * To switch to a real backend:
 * 1. Create RetrofitBugRepository implementing BugRepository.
 * 2. Inject BugApiService (from NetworkModule) into it.
 * 3. Replace MockBugRepository with RetrofitBugRepository in AppNavHost.
 * The ViewModel and UI require zero changes.
 */
class MockBugRepository : BugRepository {

    // Single source of truth for the bug list.
    // StateFlow ensures all observers are notified on every mutation.
    private val _bugs = MutableStateFlow(
        mockBugs.sortedWith(compareBy({ it.priority.ordinal }, { it.severity.ordinal }))
    )

    override fun observeBugs(): Flow<List<Bug>> = _bugs.asStateFlow()

    override suspend fun getBugById(id: String): Bug? {
        delay(300) // Simulates network latency
        return _bugs.value.find { it.id == id }
    }

    override suspend fun createBug(
        title: String,
        description: String,
        severity: Severity,
        priority: Priority,
        affectedSystem: String,
        category: BugCategory
    ): Bug {
        delay(500) // Simulates network latency
        val newBug = Bug(
            id = "bug-${System.currentTimeMillis()}",
            title = title,
            description = description,
            severity = severity,
            priority = priority,
            status = BugStatus.OPEN,
            affectedSystem = affectedSystem,
            category = category,
            reportedBy = "Current User",
            createdAt = Instant.now().toString(),
            updatedAt = Instant.now().toString()
        )
        // Emit a new sorted list — all Flow collectors are notified automatically.
        _bugs.update { current ->
            (current + newBug).sortedWith(
                compareBy({ it.priority.ordinal }, { it.severity.ordinal })
            )
        }
        return newBug
    }

    override suspend fun updateBugStatus(id: String, status: BugStatus, priority: Priority): Bug {
        delay(300)
        var updatedBug: Bug? = null
        _bugs.update { current ->
            current.map { bug ->
                if (bug.id == id) {
                    bug.copy(
                        status = status,
                        priority = priority,
                        updatedAt = Instant.now().toString()
                    ).also { updatedBug = it }
                } else bug
            }.sortedWith(compareBy({ it.priority.ordinal }, { it.severity.ordinal }))
        }
        return updatedBug ?: error("Bug $id not found")
    }
}
