package com.example.notesapp.domain.usecase

import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.domain.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {
    // Added 'suspend' because database operations happen in the background
    suspend operator fun invoke(
        id: Int = 0,
        title: String,
        body: String = "" // Added default empty body so NoteEntity has what it needs
    ) {
        if (title.isNotBlank()) {

            // Replaced 'addTodo(TodoItem)' with 'insertNote(NoteEntity)' to match your repository
            repository.insertNote(
                NoteEntity(
                    id = id,
                    title = title.trim(),
                    body = body,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}