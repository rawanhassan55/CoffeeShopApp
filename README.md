# Coffee Shop App

A modern Android application for browsing coffee products, managing a shopping cart, and saving favorites—with email authentication and cloud-backed catalog data.

**What problem does it solve?**  
It demonstrates a complete e-commerce–style flow in a focused demo: discover products by category, search and filter, inspect details, favorite items synced to the cloud, check out locally, and maintain a simple user profile—while keeping the product list available offline after sync.

---
## Why This Project?

This project was built to practice real-world Android development concepts such as building scalable architectures, handling offline data efficiently, and integrating cloud services.

It reflects my journey in transitioning from basic applications to building production-ready Android apps using modern tools and best practices.


---

## Features

| Area                    | Description                                                                                       |
|-------------------------|---------------------------------------------------------------------------------------------------|
| **Authentication**      | Email/password sign-up and login with Firebase Auth; email verification gate; password reset flow |
| **Splash & onboarding** | Branded entry and first-time onboarding before sign-in                                            |
| **Home**                | Product grid by category, pulled from FireStore and cached locally                                |
| **Search**              | Live search by product name                                                                       |
| **Filter**              | Bottom sheet for price range and sort (price, rating)                                             |
| **Product details**     | Images, description, quantity selector, add to cart, favorite toggle                              |
| **Favorites**           | List of saved products with sync to FireStore per user                                            |
| **Cart**                | Persistent cart (Room), quantity controls, subtotal/tax/total                                     |
| **Checkout**            | Confirmation flow with success UI (demo—no real payment)                                          |
| **Profile & settings**  | View/edit profile fields stored locally (DataStore); sign out                                     |

---


## Tech Stack

| Technology                  | Usage                                                                            |
|-----------------------------|----------------------------------------------------------------------------------|
| **Kotlin**                  | 100% Kotlin source                                                               |
| **MVVM**                    | UI logic in ViewModels; Fragments observe state                                  |
| **Hilt**                    | Dependency injection (`@HiltAndroidApp`, `@AndroidEntryPoint`, `@HiltViewModel`) |
| **Room**                    | Local database for products cache and cart                                       |
| **Firebase Authentication** | Email/password accounts                                                          |
| **Cloud FireStore**         | Product catalog and per-user favorites                                           |
| **Coroutines & Flow**       | Async work and reactive streams                                                  |
| **Navigation Component**    | Single-activity, fragment navigation + Safe Args                                 |
| **ViewBinding**             | Type-safe view access                                                            |
| **Glide**                   | Image loading                                                                    |
| **Material Components**     | Bottom navigation, dialogs, bottom sheets                                        |
| **DataStore**               | User profile preferences                                                         |
| **Lottie / Konfetti**       | Empty-cart animation and checkout celebration (UI polish)                        |

---

## Architecture Overview

The app follows **MVVM** with a **repository** pattern and clear separation between UI, presentation, and data.

```
Fragment / Activity (UI)
        │
        ▼
    ViewModel (StateFlow, events, business rules)
        │
        ▼
    Repository (interfaces + implementations)
        │
        ├──► Remote data sources (Firebase Auth, Firestore)
        └──► Local data sources (Room DAOs, DataStore)
```

**Data flow (typical read path):**

1. **UI** collects `StateFlow` (or `SharedFlow` for one-shot events) from the **ViewModel**.
2. **ViewModel** calls a **repository** method or observes a **Flow** from the repository.
3. **Repository** coordinates **remote** (e.g. refresh products from FireStore) and **local** (Room as source of truth for the UI list after sync).
4. Updates propagate back through **Flow** → ViewModel → UI.

**Why this shape?**  
UI stays dumb (no Firebase/Room calls in Fragments), tests can target ViewModels and repositories, and data sources can be swapped or faked.

---

## Key Concepts

- **Offline-first catalog** — The home experience observes **Room**; FireStore is used to **refresh** cached products so the UI keeps working without a constant network connection after initial load.
- **State management** — `StateFlow` holds durable UI state (loading, lists, flags); `SharedFlow` (or similar) can carry one-time actions such as navigation or snackBar messages.
- **Separation of concerns** — Fragments handle views and user input; ViewModels handle scope-safe coroutines and state; repositories hide whether data came from disk or network.
- **Dependency injection** — Hilt provides singletons (database, Firebase clients) and wires ViewModels without manual service locators.

---

## Installation & Setup

### Requirements

- **Android Studio** Hedgehog (2023.1.1) or newer recommended (project uses AGP **8.7.x**)
- **JDK 11**
- **Android SDK** — `compileSdk 35`, `minSdk 26`, `targetSdk 34`
- A **Firebase** project with **Authentication** (Email/Password) and **FireStore** enabled


## Folder Structure

```
app/src/main/java/com/example/coffeeshopapp/
├── CoffeeShopApp.kt          # Application class (@HiltAndroidApp)
├── MainActivity.kt           # Host activity, bottom navigation
├── ui/                       # Fragments, adapters (feature-oriented)
│   ├── auth/
│   ├── splash/
│   ├── home/
│   ├── details/
│   ├── cart/
│   ├── favorite/
│   └── profile/
├── viewmodel/                # ViewModels + UI state/events per feature
├── data/
│   ├── model/                # Data classes (Product, User, cart UI models, …)
│   ├── mapper/               # Entity ↔ UI / domain mapping
│   ├── local/                # Room (entities, DAOs, database)
│   ├── repository/           # Repository interfaces & implementations
│   ├── auth/                 # Auth-related data helpers
│   └── source/               # Static / seed / Firestore helpers
├── di/                       # Hilt modules (Database, Firebase, Repository, DataStore)
└── utils/                    # Constants, DataStore wrapper, extensions, Result helpers

app/src/main/res/
├── layout/                   # XML layouts (ViewBinding)
├── navigation/               # Nav graph
├── menu/                     # Bottom navigation menu
└── values/                   # Themes, strings, colors
```

---

## Challenges & Improvements

### Current limitations

- **Checkout** is illustrative only (no payment gateway or server-side orders).
- **Product sync errors** may not always surface clearly in the UI; empty states depend on data and timing.
- **Room** uses destructive fallback migrations in development—**not** suitable for production user data without proper migrations.
- **Tests** are minimal (template samples); no broad CI pipeline described in-repo.
- **Profile** is largely local (DataStore); a full product would sync profile to FireStore or a backend.

### Future improvements

- Pagination or limits for FireStore queries; debounced search.
- Richer error handling and retry UX for network operations.
- Unit tests for ViewModels/repositories and CI (GitHub Actions).
- Proper Room **migrations**, release **minification** rules, and **Crashlytics** / analytics.
- Accessibility pass (content descriptions, TalkBack).

---

## Author

**Rawan Hassan **  
Android Developer

 [LinkedIn](https://www.linkedin.com/in/rawannhassan)

---
