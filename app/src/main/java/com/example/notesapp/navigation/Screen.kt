package com.example.notesapp.presentation.navigation
sealed class Screen(
    val route: String
) {

    object TodoList : Screen("list")

    object TodoDetail : Screen("detail/{todoId}") {

        fun createRoute(todoId: Int): String {
            return "detail/$todoId"
        }
    }
}