package com.example.notesapp.domain.repository

import com.example.notesapp.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllNotes(): Flow<List<NoteEntity>>

    suspend fun insertNote(
        note: NoteEntity
    )

    suspend fun deleteNote(
        note: NoteEntity
    )
}