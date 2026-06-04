package com.example.notesapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    var count by mutableStateOf(0)
        private set

    var inputText by mutableStateOf("")
        private set


    val todoItems = repository
        .getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun increment() {
        count++
    }

    fun decrement() {
        count--
    }

    fun onInputChange(
        text: String
    ) {
        inputText = text
    }

    fun addTodo() {
        if (inputText.isBlank()) return

        viewModelScope.launch {
            repository.insertNote(
                NoteEntity(
                    title = inputText.trim(),
                    body = "",
                    timestamp = System.currentTimeMillis()
                )
            )
            inputText = ""
        }
    }

    fun deleteNote(
        note: NoteEntity
    ) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun restoreNote(
        note: NoteEntity
    ) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }
}