package com.todolist.com.todolist

import android.app.Application
import com.todolist.com.todolist.database.NoteDatabase

class ToDoList : Application() {

    companion object {
        var database: NoteDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        //ToDoList.database = Room.databaseBuilder(this, NoteDatabase::class.java, "we-need-db").build()
    }
}