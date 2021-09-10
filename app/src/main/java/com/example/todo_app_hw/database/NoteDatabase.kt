package com.example.todo_app_hw.database

import android.content.Context
import androidx.room.*
import com.example.todo_app_hw.data.Note


@Database(entities = [Note::class] , version = 1)
@TypeConverters(Converter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{

        private const val DATABASE_NAME = "MyNoteDatabase"

        @Volatile private var instances: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase{
           return instances ?: synchronized(this) {
               buildDatabase(context)
                   .also { instances = it }
           }
        }

        fun getInstanceWithoutContext(): NoteDatabase {
            return instances!!
        }

        private fun buildDatabase(context: Context): NoteDatabase{
            return Room.databaseBuilder(context,
                NoteDatabase::class.java, DATABASE_NAME).build()
        }

    }

}