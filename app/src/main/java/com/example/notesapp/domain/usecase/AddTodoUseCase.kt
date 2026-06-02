package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.TodoItem
import com.example.notesapp.domain.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {

    operator fun invoke(title: String) {

        if (title.isNotBlank()) {
            repository.addTodo(
                TodoItem(title.trim())
            )
        }
    }
}