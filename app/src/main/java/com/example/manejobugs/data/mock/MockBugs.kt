package com.example.manejobugs.data.mock

import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity

/**
 * Realistic mock data representing ButterTech's internal bug reports.
 * These scenarios reflect enterprise issues such as auth failures, sync problems,
 * integration breakdowns, and performance regressions.
 */
val mockBugs = listOf(
    Bug(
        id = "bug-001",
        title = "JWT token expires without user notification",
        description = "Users are silently logged out when the JWT expires mid-session. " +
                "No toast or dialog is displayed, leading to data loss on unsaved forms. " +
                "Reproducible on all Android versions after exactly 60 minutes of inactivity.",
        severity = Severity.CRITICAL,
        priority = Priority.URGENT,
        status = BugStatus.IN_PROGRESS,
        affectedSystem = "Auth Service",
        category = BugCategory.AUTHENTICATION,
        reportedBy = "Sofia Herrera",
        createdAt = "2026-05-20T09:15:00Z",
        updatedAt = "2026-05-21T14:30:00Z"
    ),
    Bug(
        id = "bug-002",
        title = "Inventory sync fails after offline period",
        description = "When the device reconnects after being offline for more than 5 minutes, " +
                "the inventory sync process throws a ConcurrentModificationException and halts. " +
                "Affected module: StockSyncWorker. Last successful sync timestamp is not persisted.",
        severity = Severity.HIGH,
        priority = Priority.HIGH,
        status = BugStatus.OPEN,
        affectedSystem = "Inventory Module",
        category = BugCategory.SYNC,
        reportedBy = "Carlos Méndez",
        createdAt = "2026-05-21T11:00:00Z",
        updatedAt = "2026-05-21T11:00:00Z"
    ),
    Bug(
        id = "bug-003",
        title = "Analytics dashboard crashes on empty dataset",
        description = "The analytics screen throws a NullPointerException when the metrics " +
                "endpoint returns an empty array. The crash occurs in MetricsMapper.kt line 47. " +
                "Affects accounts with no activity in the last 30 days.",
        severity = Severity.HIGH,
        priority = Priority.HIGH,
        status = BugStatus.OPEN,
        affectedSystem = "Analytics Dashboard",
        category = BugCategory.DATA_LOADING,
        reportedBy = "Ana Quirós",
        createdAt = "2026-05-22T08:45:00Z",
        updatedAt = "2026-05-22T08:45:00Z"
    ),
    Bug(
        id = "bug-004",
        title = "Report list scroll lags with 200+ items",
        description = "Noticeable frame drops (below 30fps) when scrolling through the report " +
                "list with more than 200 items loaded. Profiler shows excessive recomposition " +
                "in ReportCard. Images are not cached between scroll events.",
        severity = Severity.MEDIUM,
        priority = Priority.MEDIUM,
        status = BugStatus.OPEN,
        affectedSystem = "Reports Module",
        category = BugCategory.PERFORMANCE,
        reportedBy = "Luis Vargas",
        createdAt = "2026-05-22T10:20:00Z",
        updatedAt = "2026-05-22T10:20:00Z"
    ),
    Bug(
        id = "bug-005",
        title = "Payment gateway integration returns 500 on retry",
        description = "After a failed payment attempt, retrying the transaction returns HTTP 500 " +
                "from the payment gateway adapter. The idempotency key is not being sent on " +
                "subsequent requests, causing duplicate charge attempts on the gateway side.",
        severity = Severity.CRITICAL,
        priority = Priority.URGENT,
        status = BugStatus.OPEN,
        affectedSystem = "Payment Gateway",
        category = BugCategory.INTEGRATION,
        reportedBy = "Marco Solano",
        createdAt = "2026-05-23T07:30:00Z",
        updatedAt = "2026-05-23T07:30:00Z"
    ),
    Bug(
        id = "bug-006",
        title = "Form validation error messages overlap on small screens",
        description = "On devices with screen width below 360dp, validation error messages " +
                "overlap the input field labels in the new user registration form. " +
                "The layout uses hardcoded dp values instead of responsive constraints.",
        severity = Severity.LOW,
        priority = Priority.LOW,
        status = BugStatus.OPEN,
        affectedSystem = "User Registration",
        category = BugCategory.UI,
        reportedBy = "Diana Castro",
        createdAt = "2026-05-23T14:10:00Z",
        updatedAt = "2026-05-23T14:10:00Z"
    ),
    Bug(
        id = "bug-007",
        title = "Push notifications not delivered after token refresh",
        description = "FCM push notifications stop being delivered after the device refreshes " +
                "its FCM registration token. The new token is not being sent to the backend " +
                "notification service, so the old (invalid) token remains registered.",
        severity = Severity.HIGH,
        priority = Priority.HIGH,
        status = BugStatus.RESOLVED,
        affectedSystem = "Notification Service",
        category = BugCategory.INTEGRATION,
        reportedBy = "Andrés Mora",
        createdAt = "2026-05-18T09:00:00Z",
        updatedAt = "2026-05-24T16:45:00Z"
    ),
    Bug(
        id = "bug-008",
        title = "User role permissions not enforced on admin screens",
        description = "Users with the 'developer' role can navigate to admin-only screens " +
                "by deep-linking directly to the route. The NavGraph guards are only applied " +
                "on the bottom navigation bar, not on direct navigation calls.",
        severity = Severity.CRITICAL,
        priority = Priority.URGENT,
        status = BugStatus.IN_PROGRESS,
        affectedSystem = "Auth Service",
        category = BugCategory.AUTHENTICATION,
        reportedBy = "Sofia Herrera",
        createdAt = "2026-05-24T08:00:00Z",
        updatedAt = "2026-05-24T15:00:00Z"
    )
)
