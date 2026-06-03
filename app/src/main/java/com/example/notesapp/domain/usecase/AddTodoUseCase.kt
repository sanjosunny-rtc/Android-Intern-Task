package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.TodoItem
import com.example.notesapp.domain.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {

    operator fun invoke(
        id: Int,
        title: String
    ) {

        if (title.isNotBlank()) {

            repository.addTodo(
                TodoItem(
                    id = id,
                    title = title.trim()
                )
            )
        }
    }
}