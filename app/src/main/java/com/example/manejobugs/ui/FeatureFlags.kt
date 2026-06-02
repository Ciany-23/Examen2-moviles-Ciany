package com.example.manejobugs.ui

/**
 * Feature Flags for ButterTech Bug Tracker.
 *
 * Feature flags allow the team to enable or disable functionality without
 * modifying multiple parts of the codebase. Especially useful during
 * internal testing phases or when rolling out features incrementally.
 *
 * Current flags:
 * - ENABLE_CREATE_BUG: controls whether users can submit new bug reports.
 * - ENABLE_STATUS_UPDATE: controls whether users can change a bug's status/priority.
 *
 * To evolve this in production:
 * - Replace the hardcoded values with a remote config call (e.g. Firebase Remote Config).
 * - Keep the same flag names — no other code needs to change.
 */
object FeatureFlags {

    /**
     * When true, the "Report Bug" button is shown and the create form is accessible.
     * Set to false to hide the feature during a restricted testing phase.
     */
    val ENABLE_CREATE_BUG: Boolean = true

    /**
     * When true, users can change the status and priority of a bug from the detail screen.
     * Set to false to make the detail screen read-only (e.g. for non-admin roles).
     */
    val ENABLE_STATUS_UPDATE: Boolean = true
}