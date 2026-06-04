package com.example.notesapp.data.repository

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext // <-- ADDED IMPORT

class NoteRepositoryImpl(
    private val dao: NoteDao
) : TodoRepository {

    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return dao.getAll()
    }

    override suspend fun insertNote(note: NoteEntity) {
        // Manually move the database work to a background thread
        withContext(Dispatchers.IO) {
            dao.insert(note)
        }
    }

    override suspend fun deleteNote(note: NoteEntity) {
        // Manually move the database work to a background thread
        withContext(Dispatchers.IO) {
            dao.delete(note)
        }
    }
}