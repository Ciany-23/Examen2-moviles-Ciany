package com.example.manejobugs.data.repository

import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for bug operations.
 * Located in the data layer as per course guidelines.
 */
interface BugRepository {

    /** Returns a reactive stream of the bug list. Emits a new list on every change. */
    fun observeBugs(): Flow<List<Bug>>

    suspend fun getBugById(id: String): Bug?

    suspend fun createBug(
        title: String,
        description: String,
        severity: Severity,
        priority: Priority,
        status: BugStatus,
        affectedSystem: String,
        category: BugCategory
    ): Bug

    suspend fun updateBugStatus(id: String, status: BugStatus, priority: Priority): Bug
}
