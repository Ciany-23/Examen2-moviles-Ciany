package com.example.manejobugs.data.dto

import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import com.google.gson.annotations.SerializedName


/**
 * Data Transfer Objects — these mirror the API contract defined in /contracts/bugs-api.yaml.
 * They are intentionally separate from the domain model so that API changes
 * don't propagate directly into the rest of the app.
 */
data class BugDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("severity") val severity: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("status") val status: String,
    @SerializedName("affectedSystem") val affectedSystem: String,
    @SerializedName("category") val category: String,
    @SerializedName("reportedBy") val reportedBy: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
) {
    /** Maps API response into the domain model used by the rest of the app. */
    fun toDomain(): Bug = Bug(
        id = id,
        title = title,
        description = description,
        severity = Severity.valueOf(severity),
        priority = Priority.valueOf(priority),
        status = BugStatus.valueOf(status),
        affectedSystem = affectedSystem,
        category = BugCategory.valueOf(category),
        reportedBy = reportedBy,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

data class LoginRequestDto(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class LoginResponseDto(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserDto
)

data class UserDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)

data class CreateBugRequestDto(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("severity") val severity: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("affectedSystem") val affectedSystem: String,
    @SerializedName("category") val category: String
)

data class UpdateBugStatusRequestDto(
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String
)
