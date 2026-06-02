# ButterTech Bug Tracker

## Architecture

- `MVVM` for UI state and separation of concerns.
- `Repository` pattern for all bug operations.
- `MockBugRepository` is the current source of truth.
- `BugApiService` and DTOs prepare the app for a future backend.

## Event-driven updates

- The bug list subscribes to a `StateFlow<List<Bug>>`.
- Creating or updating a bug mutates the repository state.
- The repository emits a new sorted list immediately, so the UI recomposes without manual refresh.

## Feature flags

- `ENABLE_CREATE_BUG` controls whether the create action is shown.
- `ENABLE_STATUS_UPDATE` can be used to gate status/priority changes in the detail screen.

## Scope notes

- The app includes login, list, detail, create, and update flows.
- The current create screen uses a simple PoC form pattern and mock values, which is acceptable for the exam scope but can be expanded later.
