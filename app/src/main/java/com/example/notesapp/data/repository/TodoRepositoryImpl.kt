package com.example.notesapp.data.repository

import com.example.notesapp.domain.model.TodoItem
import com.example.notesapp.domain.repository.TodoRepository

class TodoRepositoryImpl : TodoRepository {

    private val todos = mutableListOf<TodoItem>()

    override fun getTodos(): List<TodoItem> {
        return todos
    }

    override fun addTodo(todo: TodoItem) {
        todos.add(todo)
    }
}