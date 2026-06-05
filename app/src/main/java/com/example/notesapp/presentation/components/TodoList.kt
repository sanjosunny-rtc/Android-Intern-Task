package com.example.notesapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.local.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoList(
    items: List<NoteEntity>,
    onTodoClick: (Int) -> Unit,
    onDelete: (NoteEntity) -> Unit // <-- NEW PARAMETER
) {
    if (items.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "📝",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "No Notes Yet",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Add your first note to get started",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Use key = { it.id } so Compose knows exactly which item is swiped/deleted
            items(items, key = { it.id }) { item ->

                // 1. Setup the state for swiping
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { dismissValue ->
                        if (dismissValue == SwipeToDismissBoxValue.EndToStart ||
                            dismissValue == SwipeToDismissBoxValue.StartToEnd) {
                            onDelete(item)
                            true // Confirm the swipe
                        } else {
                            false // Reset the swipe
                        }
                    }
                )

                // 2. Wrap your card in the SwipeToDismissBox
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        // The red background that shows when swiping
                        val color = if (dismissState.dismissDirection != SwipeToDismissBoxValue.Settled) {
                            MaterialTheme.colorScheme.errorContainer
                        } else {
                            Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color, MaterialTheme.shapes.medium)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    },
                    content = {
                        // Your actual card
                        TodoCard(
                            todo = item,
                            onTodoClick = onTodoClick
                        )
                    }
                )
            }
        }
    }
}