package com.example.todo_app_hw.adapter

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtil<T>(
    private val oldItems: List<T>?,
    private val newItems: List<T>?,
    private val checkIfSameItem: (oldItem: T, newItem: T) -> Boolean
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems!!.size

    override fun getNewListSize(): Int = newItems!!.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        checkIfSameItem(oldItems!![oldItemPosition] , newItems!![newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

}