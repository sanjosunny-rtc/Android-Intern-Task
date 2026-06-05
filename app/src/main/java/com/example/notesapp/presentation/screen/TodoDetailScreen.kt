package com.example.notesapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // <-- ADDED IMPORT
import androidx.compose.runtime.getValue // <-- ADDED IMPORT
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: Int,
    navController: NavController,
    viewModel: TodoViewModel
) {
    // 1. Collect the live list of notes from the database
    val todoList by viewModel.todoItems.collectAsState()

    // 2. Find the specific note that matches the ID passed to this screen
    val todo = todoList.find { it.id == todoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Todo Detail")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Task",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                // NoteEntity uses 'title', so this works perfectly!
                text = todo?.title ?: "Todo not found",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}