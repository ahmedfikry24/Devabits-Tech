Aladhan Prayer Times App
A modern, offline-first Android application built with Jetpack Compose and Clean Architecture. This app provides accurate prayer timings based on location, featuring a robust caching system for seamless offline use.

Tech Stack & Architecture
This project follows the Clean Architecture principles to ensure scalability, testability, and maintainability.

Architecture: MVVM (Model-View-ViewModel) + UDF (Unidirectional Data Flow).

UI: Jetpack Compose (100% Kotlin).

Dependency Injection: Koin.

Networking: Retrofit + Kotlinx Serialization.

Local Database: Room (Single Source of Truth).

Concurrency: Kotlin Coroutines & Flow.

Jetpack Libraries: Navigation Compose, ViewModel, Lifecycle, and Room.

Project Structure
The project is organized into three main layers:

Data: Implementation of Repositories, Retrofit API Services, Room DAOs, and Mappers (DTO to Domain/Entity).

Domain: Business logic, including the Prayer models and Repository Interfaces.

UI/Presentation: Jetpack Compose screens, ViewModels, and State management using StateFlow.

Features & Patterns
Repository Pattern (Offline-First)
The app implements a "Cache-First" strategy. It checks the local Room database for stored timings before attempting a network call. If the device is offline and no cache exists, it gracefully handles the state via a custom Resource wrapper.

Single Source of Truth (SSOT)
All data displayed in the UI is sourced from the database. The network layer only acts as a feeder to the database, ensuring a consistent user experience regardless of connectivity.

Reactive UI
The UI observes a StateFlow emitted by the ViewModel, which in turn collects from the Repository. Any change in the database is automatically reflected on the screen.

Getting Started
This project is designed to be "Clone and Run." There are no complex environment requirements.

Clone the repository:

Bash
git clone https://github.com/yourusername/prayer-app.git
Open in Android Studio: Use Ladybug or newer (2024.3+).

Sync Gradle: Ensure you are using Java 17 or 21.

Run: Hit the Run button. The app uses the Aladhan public API and does not require an API Key for basic requests.