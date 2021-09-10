package com.example.todo_app_hw.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app_hw.BR
import com.example.todo_app_hw.data.Note


abstract class BaseAdapter<T> (private var items: List<T>?,
                              private var listener: RvOnClickListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {


    fun getItem(position: Int) = items?.get(position)

    abstract val layoutId: Int

    fun setItem(newItems: List<T>?) {
        val diffResult = DiffUtil.calculateDiff(SimpleDiffUtil(items,newItems){ oldItem, newItem ->
            areItemsSame(oldItem,newItem)
        })
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    fun areItemsSame(oldItem: T, newItem: T) :Boolean {
        return oldItem?.equals(newItem) == true
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                layoutId , parent,false) , listener )
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder, position: Int) {
        var currentItem  = items?.get(position)
        when(holder){
            is ItemViewHolder  -> {
                holder.bind(currentItem!!)
            } else -> {
                Log.i("rrrrrr" , "xxx"+ currentItem.toString())
            }
        }
    }

    override fun getItemCount(): Int = items!!.size

    class ItemViewHolder(val binding: ViewDataBinding , private val onClickListener: RvOnClickListener )
        : BaseViewHolder(binding) , View.OnClickListener   {

        lateinit var item:Note
        fun bind(currentItem: Any ) {
            item = currentItem as Note
            binding.setVariable(BR.itemXML, currentItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener(this)
        }

         override fun onClick(v: View?) {
             onClickListener.onItmClick(v!! , item)
         }

     }

    abstract class BaseViewHolder(binding: ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root)

}

interface BaseInteractionListener {
}




