package com.todolist.com.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.todolist.com.todolist.model.NoteModel

@Database(entities = [(NoteModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listNoteDao(): NoteDao
}