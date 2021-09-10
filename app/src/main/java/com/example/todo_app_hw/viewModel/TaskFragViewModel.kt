package com.example.todo_app_hw.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_hw.data.Note
import com.example.todo_app_hw.data.Priority
import com.example.todo_app_hw.repositries.NoteRepositry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskFragViewModel : ViewModel() {

    val repo = NoteRepositry()

    val idNote = MutableLiveData<Long>(0)
    val titleNote = MutableLiveData<String>()
    val contentNote = MutableLiveData<String>()
    val priorityNote = MutableLiveData<Int>()
    val statusBtn = MutableLiveData<Int?>()
    val dateNote = MutableLiveData<String>()
    val timeNote = MutableLiveData<String>()


    fun addNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertNewNote(
                Note(0 , titleNote.value.toString(),
                    contentNote.value.toString(),  dateNote.value.toString() , timeNote.value.toString() ,
                    parsePriority(priorityNote.value) , false ,"gg")
            )
        }
    }


    fun getCurrentNote(note: Any?){
        idNote.value = (note as Note?)?.id
        titleNote.value = note?.title
        contentNote.value = note?.content
        priorityNote.value = note?.priority?.index()
        dateNote.value = note?.date
        timeNote.value = note?.time

    }

    fun updateNote(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateNote(
                Note(idNote.value!!, titleNote.value.toString(),
                    contentNote.value!!,  dateNote.value!! , timeNote.value!! ,
                    parsePriority(priorityNote.value) , false ,"gg")
            )
        }
    }

    fun parsePriority(priority: Int?): Priority {
        return when(priority){
            0 -> { Priority.HIGH }
            1 -> { Priority.MEDIUM }
            2 -> { Priority.LOW }
            else -> Priority.LOW
        }
    }




}