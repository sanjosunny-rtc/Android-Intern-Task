package com.example.notesapp.presentation.screen

import SearchBar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.* // <-- Make sure to import Material3 components
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch // <-- For the Snackbar coroutine

import com.example.notesapp.presentation.components.InputSection
import com.example.notesapp.presentation.components.TodoList
import com.example.notesapp.presentation.viewmodel.TodoViewModel
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToDoScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {
    val context = LocalContext.current
    val todoList by viewModel.todoItems.collectAsState()

    // 1. Create state for the Snackbar and Coroutine Scope
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val filteredNotes =
        if (viewModel.searchQuery.isBlank()) {
            todoList
        } else {
            todoList.filter {
                it.title.contains(
                    viewModel.searchQuery,
                    ignoreCase = true
                )
            }
        }

    LaunchedEffect(viewModel.errorMessage) {

        viewModel.errorMessage?.let {

            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply Scaffold padding
                .padding(16.dp)
        ) {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputSection(
                text = viewModel.inputText,
                onTextChange = viewModel::onInputChange,
                onAdd = viewModel::addTodo
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(
                query = viewModel.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))


            TodoList(
                items = filteredNotes,
                onTodoClick = { todoId ->
                    navController.navigate("detail/$todoId")
                },
                // 3. Add the onDelete logic with the Undo Snackbar
                onDelete = { noteToDelete ->

                    // Delete the note immediately
                    viewModel.deleteNote(noteToDelete)

                    // Show the snackbar
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Task deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )

                        // If the user clicks "Undo", restore the note!
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.restoreNote(noteToDelete)
                        }
                    }
                }
            )
        }
    }
}