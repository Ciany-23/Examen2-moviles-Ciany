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
- `/docs` Justificación técnica del Manejo de Bugs
- `/video` demo link placeholder

## Notes

- The app currently runs on mock data.
- `MockBugRepository` is the active repository.
- The Retrofit layer is kept ready for a future backend swap.
- You can log in to the system with any email address and any password because it does not use a backend or a database.