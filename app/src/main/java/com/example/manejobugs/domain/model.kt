package com.example.manejobugs.domain.model

data class Bug(
    val id: String,
    val title: String,
    val description: String,
    val severity: Severity,
    val priority: Priority,
    val status: BugStatus,
    val affectedSystem: String,
    val category: BugCategory,
    val reportedBy: String,
    val createdAt: String,
    val updatedAt: String
)

enum class Severity { CRITICAL, HIGH, MEDIUM, LOW }
enum class Priority { URGENT, HIGH, MEDIUM, LOW }
enum class BugStatus { OPEN, IN_PROGRESS, RESOLVED, CLOSED }
enum class BugCategory { AUTHENTICATION, SYNC, DATA_LOADING, PERFORMANCE, UI, INTEGRATION }