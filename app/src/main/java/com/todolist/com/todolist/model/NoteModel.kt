package com.todolist.com.todolist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
                     @ColumnInfo(name = "title") var title: String,
                     @ColumnInfo(name = "description") var description: String)