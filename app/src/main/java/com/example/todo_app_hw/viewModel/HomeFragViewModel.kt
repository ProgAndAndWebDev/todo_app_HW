package com.example.todo_app_hw.viewModel


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.example.todo_app_hw.R
import com.example.todo_app_hw.adapter.RvOnClickListener
import com.example.todo_app_hw.data.Note
import com.example.todo_app_hw.data.Priority
import com.example.todo_app_hw.repositries.NoteRepositry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class HomeFragViewModel : ViewModel() , RvOnClickListener {

    val repo = NoteRepositry()

    val searchText = MutableStateFlow("")
    private val _notes = MutableLiveData<List<Note>>()
    val notes = MutableLiveData<List<Note>>()


    init {
        loadNotes()
        filterNotes()

    }

    fun addNotes(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note?.let { repo.insertNewNote(it) }
        }
    }

    fun loadNotes(){
        viewModelScope.launch {
            repo.getAllNotes().collect {
                notes.postValue(it)
            }
        }
    }

    private fun filterNotes() {
        viewModelScope.launch {
            searchText.collect {
                    val result = repo.getFilteredNotes(it)
                    notes.value = result
            }
        }
    }

    fun deleteNotes(note: Note?){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(note!!)
        }
    }

    override fun onItmClick(view: View?, note: Note?) {
        if(note != null || view != null)
            Navigation.findNavController(view!!).navigate(R.id.taskFragment  , Bundle().apply {
                putParcelable("NOTE_CURRENT" , note)
            })
    }


}