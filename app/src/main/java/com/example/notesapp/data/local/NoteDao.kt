package com.example.notesapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAll(): Flow<List<NoteEntity>>

    // REMOVED 'suspend' to fix the KSP compilation bug
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity): Long

    // REMOVED 'suspend' to fix the KSP compilation bug
    @Delete
    fun delete(note: NoteEntity): Int
}