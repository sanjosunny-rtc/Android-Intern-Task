package com.example.notesapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.notesapp.presentation.screen.ToDoScreen
import com.example.notesapp.presentation.screen.TodoDetailScreen
import com.example.notesapp.presentation.viewmodel.TodoViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: TodoViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.TodoList.route
    ) {

        composable(Screen.TodoList.route) {

            ToDoScreen(
                navController,
                viewModel
            )
        }

        composable(
            route = Screen.TodoDetail.route,
            arguments = listOf(
                navArgument("todoId") {
                    type = NavType.IntType
                }
            )
        ) {

            val id =
                it.arguments?.getInt("todoId") ?: 0

            TodoDetailScreen(
                id,
                navController,
                viewModel
            )
        }
    }
}