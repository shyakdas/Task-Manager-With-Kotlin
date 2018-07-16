package com.todolist.com.todolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.todolist.com.todolist.model.NoteModel

@Database(entities = [(NoteModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listNoteDao(): NoteDao
}