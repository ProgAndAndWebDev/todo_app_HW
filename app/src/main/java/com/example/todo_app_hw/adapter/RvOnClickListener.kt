package com.example.todo_app_hw.adapter

import android.view.View
import com.example.todo_app_hw.data.Note

interface RvOnClickListener : BaseInteractionListener {
    fun onItmClick(binding: View? , note: Note?)
}