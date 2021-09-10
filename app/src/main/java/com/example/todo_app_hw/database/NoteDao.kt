package com.example.todo_app_hw.database

import androidx.room.*
import com.example.todo_app_hw.data.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTE_TABLE WHERE title LIKE :searchTerm ORDER BY id DESC")
    suspend fun getFilterNotes(searchTerm: String): List<Note>
}