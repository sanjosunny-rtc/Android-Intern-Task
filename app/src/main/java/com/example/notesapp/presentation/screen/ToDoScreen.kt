package com.example.notesapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.* // <-- Make sure to import Material3 components
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch // <-- For the Snackbar coroutine

import com.example.notesapp.presentation.components.CounterSection
import com.example.notesapp.presentation.components.InputSection
import com.example.notesapp.presentation.components.TodoList
import com.example.notesapp.presentation.viewmodel.TodoViewModel

@Composable
fun ToDoScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {
    val todoList by viewModel.todoItems.collectAsState()

    // 1. Create state for the Snackbar and Coroutine Scope
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 2. Wrap your layout in a Scaffold to show the Snackbar at the bottom
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply Scaffold padding
                .padding(16.dp)
        ) {

            CounterSection(
                count = viewModel.count,
                onIncrement = viewModel::increment,
                onDecrement = viewModel::decrement
            )

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            InputSection(
                text = viewModel.inputText,
                onTextChange = viewModel::onInputChange,
                onAdd = viewModel::addTodo
            )

            Spacer(modifier = Modifier.height(16.dp))

            TodoList(
                items = todoList,
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