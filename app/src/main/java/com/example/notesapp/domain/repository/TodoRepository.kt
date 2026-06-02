package com.example.notesapp.domain.repository

import com.example.notesapp.domain.model.TodoItem

interface TodoRepository {

    fun getTodos(): List<TodoItem>

    fun addTodo(todo: TodoItem)
}