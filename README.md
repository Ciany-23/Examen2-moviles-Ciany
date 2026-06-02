# ManejoBugs

ButterTech bug tracker proof of concept built with Jetpack Compose.

## What is included

- Simulated login flow
- Reactive bug list with `LazyColumn`
- Bug detail screen
- Create bug screen
- Status and priority updates
- Mock repository with realistic enterprise data
- Retrofit contracts and DTO layer prepared for a future backend

## Tech stack

- Kotlin
- Jetpack Compose
- MVVM
- Navigation Compose
- Retrofit
- StateFlow

## Project layout

- `/app` Android application
- `/contracts` API contracts in YAML
- `/docs` technical notes and handoff context
- `/video` demo link placeholder

## Notes

- The app currently runs on mock data.
- `MockBugRepository` is the active repository.
- The Retrofit layer is kept ready for a future backend swap.
