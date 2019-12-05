package com.todolist.com.taskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
                     @ColumnInfo(name = "title") var title: String,
                     @ColumnInfo(name = "description") var description: String)