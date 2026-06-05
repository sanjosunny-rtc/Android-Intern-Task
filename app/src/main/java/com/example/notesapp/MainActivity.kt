package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notesapp.data.local.NoteDatabase
import com.example.notesapp.data.repository.NoteRepositoryImpl
import com.example.notesapp.presentation.navigation.AppNavGraph
import com.example.notesapp.presentation.viewmodel.TodoViewModel
import com.example.notesapp.presentation.viewmodel.TodoViewModelFactory
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        val repository = NoteRepositoryImpl(
            database.noteDao()
        )

        val factory = TodoViewModelFactory(
            repository
        )

        val viewModel: TodoViewModel by viewModels {
            factory
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // SQL command to add a new column to the existing 'notes' table
                db.execSQL("ALTER TABLE notes ADD COLUMN category TEXT NOT NULL DEFAULT ''")
            }
        }

        setContent {
            NotesAppTheme {
                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}