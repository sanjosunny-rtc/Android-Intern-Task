package com.example.notesapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.TodoItem

class TodoViewModel : ViewModel() {

    var count by mutableStateOf(0)
        private set
    var inputText by mutableStateOf("")
        private set

    private var nextId = 1

    private val _todoItems = mutableStateListOf<TodoItem>()

    val todoItems: List<TodoItem>
        get() = _todoItems

    fun increment() {
        count++
    }

    fun decrement() {
        count--
    }

    fun onInputChange(text: String) {
        inputText = text
    }

    fun addTodo() {

        if (inputText.isBlank()) return

        val todo = TodoItem(
            id = nextId++,
            title = inputText.trim()
        )

        _todoItems.add(todo)

        Log.d("ToDoApp", "Added item: ${todo.title}")

        inputText = ""
    }

    fun getTodoById(id: Int): TodoItem? {
        return todoItems.find { it.id == id }
    }
}