package com.todolist.com.taskmanager

import android.app.Application
import androidx.room.Room
import com.todolist.com.taskmanager.database.AppDatabase

class TaskManagerApplication : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}