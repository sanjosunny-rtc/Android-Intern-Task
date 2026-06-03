package com.example.notesapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.notesapp.presentation.components.CounterSection
import com.example.notesapp.presentation.components.InputSection
import com.example.notesapp.presentation.components.TodoList
import com.example.notesapp.presentation.viewmodel.TodoViewModel
@Composable
fun ToDoScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
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
            items = viewModel.todoItems,
            onTodoClick = { todoId ->

                navController.navigate(
                    "detail/$todoId"
                )
            }
        )
    }
}