package com.example.todo_app_hw.adapter

import android.view.View
import com.example.todo_app_hw.R
import com.example.todo_app_hw.adapter.BaseAdapter.ItemViewHolder
import com.example.todo_app_hw.data.Note


class MovieAdapter(items: List<Note>,
                   var listener: RvOnClickListener,
) : BaseAdapter<Note>(items,listener) {

    override val layoutId: Int = R.layout.item_note

}

