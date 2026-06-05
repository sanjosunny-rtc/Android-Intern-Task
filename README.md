\# Notes App



\## Overview



Notes App is a simple Android application built using Jetpack Compose and Room Database. The application allows users to create, view, search, and delete notes while persisting data locally on the device. The app follows the MVVM (Model-View-ViewModel) architecture and uses Material 3 design components.



\## Features



\* Add new notes

\* View all saved notes

\* Search notes in real time

\* View note details

\* Swipe to delete notes

\* Undo deleted notes using Snackbar

\* Persistent local storage using Room Database

\* Material 3 UI

\* Dark Mode support

\* Dynamic Color support on Android 12+



\## Architecture



The application follows the MVVM architecture pattern.



\### Layers



\#### Presentation Layer



Responsible for UI and user interactions.



\* ToDoScreen

\* TodoDetailScreen

\* InputSection

\* TodoList

\* TodoCard

\* TodoViewModel



\#### Domain Layer



Contains repository contracts.



\* TodoRepository



\#### Data Layer



Responsible for data persistence.



\* NoteEntity

\* NoteDao

\* NoteDatabase

\* NoteRepositoryImpl



\### Data Flow



User Action → ViewModel → Repository → Room Database



Room Database → Repository → ViewModel → UI



\## Technologies Used



\* Kotlin

\* Jetpack Compose

\* Material 3

\* Room Database

\* Coroutines

\* Flow

\* Navigation Compose

\* MVVM Architecture



\## Project Structure



```text

app

├── data

│   ├── local

│   │   ├── NoteEntity

│   │   ├── NoteDao

│   │   └── NoteDatabase

│   └── repository

│       └── NoteRepositoryImpl

│

├── domain

│   └── repository

│

├── presentation

│   ├── components

│   ├── navigation

│   ├── screen

│   └── viewmodel

│

└── ui

&#x20;   └── theme

```



\## How to Build and Run



\### Prerequisites



\* Android Studio

\* Android SDK

\* JDK 17 or later



\### Steps



1\. Clone the repository.



```bash

git clone <repository-url>

```



2\. Open the project in Android Studio.



3\. Allow Gradle to sync.



4\. Run the application on an emulator or Android device.



\## Future Improvements



\* Edit existing notes

\* Categories and tags

\* Cloud synchronization

\* User authentication

\* Note sharing functionality



\## Author



Developed as part of an Android development internship project.



