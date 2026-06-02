package com.example.notesapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputSection(
    text: String,
    onTextChange: (String) -> Unit,
    onAdd: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = {
                Text("Add a task...")
            },
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        Button(
            onClick = onAdd
        ) {
            Text("Add")
        }
    }
}