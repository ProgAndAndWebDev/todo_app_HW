package com.example.todo_app_hw.data

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@SuppressLint("ParcelCreator")
@Entity(tableName = "NOTE_TABLE")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    @ColumnInfo(name = "Content") val content: String,
    val date: String,
    val time: String,
    val priority: Priority,
    val isComplete: Boolean,
    val imagePath: String,

) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

}