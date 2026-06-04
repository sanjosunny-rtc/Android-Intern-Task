package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.repository.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository
) {

    operator fun invoke() =
        repository.getAllNotes()
}