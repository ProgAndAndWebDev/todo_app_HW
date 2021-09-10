package com.example.todo_app_hw.repositries


import com.example.todo_app_hw.data.Note
import com.example.todo_app_hw.database.NoteDatabase

class NoteRepositry {

    val dao = NoteDatabase.getInstanceWithoutContext().noteDao()

    suspend fun insertNewNote(note: Note) {
        dao.insert(note)
    }

    fun getAllNotes()  = dao.getAllNotes()

    suspend fun getFilteredNotes(searchTerm: String)
    = dao.getFilterNotes("%$searchTerm%")

    suspend fun deleteNote(note: Note) = dao.delete(note)

    suspend fun updateNote(note: Note) = dao.update(note)

}