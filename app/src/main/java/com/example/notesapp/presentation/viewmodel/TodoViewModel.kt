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

    var inputText by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    val todoItems = repository
        .getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onInputChange(
        text: String
    ) {
        inputText = text
    }

    fun addTodo() {
        if (inputText.isBlank()) return


        viewModelScope.launch {
            try {
                repository.insertNote(
                    NoteEntity(
                        title = inputText.trim(),
                        body = "",
                        timestamp = System.currentTimeMillis()
                    )
                )
                inputText = ""
            } catch (e: Exception) {

            errorMessage = "Database error occurred"
        }
        }
    }

    fun deleteNote(
        note: NoteEntity
    ) {
        viewModelScope.launch {
            try{
            repository.deleteNote(note)
            } catch (e: Exception) {

                errorMessage = "Database error occurred"
            }
        }
    }

    fun restoreNote(
        note: NoteEntity
    ) {
        viewModelScope.launch {
            try{
            repository.insertNote(note)
            } catch (e: Exception) {

                errorMessage = "Database error occurred"
            }
        }
    }
    var searchQuery by mutableStateOf("")
        private set

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

}