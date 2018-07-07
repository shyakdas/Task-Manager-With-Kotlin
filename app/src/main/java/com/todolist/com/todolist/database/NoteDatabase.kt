package com.todolist.com.todolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.todolist.com.todolist.model.NoteModel

@Database(entities = arrayOf(NoteModel::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun taskDao(): NoteDao
}