package com.todolist.com.todolist

import android.app.Application
import androidx.room.Room
import com.todolist.com.todolist.database.AppDatabase

class ToDoList : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java,
                "list-master-db").build()
    }
}