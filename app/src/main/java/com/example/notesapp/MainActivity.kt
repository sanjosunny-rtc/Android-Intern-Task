package com.example.notesapp

import com.example.notesapp.presentation.navigation.AppNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContent {

            NotesAppTheme {

                val navController =
                    rememberNavController()

                val viewModel: TodoViewModel =
                    viewModel()

                AppNavGraph(
                    navController,
                    viewModel
                )
            }
        }
    }
}
