package com.example.notesapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    var count by mutableStateOf(0)
        private set

    var inputText by mutableStateOf("")
        private set

    var todoItems = mutableStateListOf<String>()
        private set

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

        todoItems.add(inputText.trim())

        Log.d("ToDoApp", "Added item: $inputText")

        inputText = ""
    }
}