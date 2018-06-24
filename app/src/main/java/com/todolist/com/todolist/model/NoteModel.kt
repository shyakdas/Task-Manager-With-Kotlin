package com.todolist.com.todolist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class NoteModel(@PrimaryKey(autoGenerate = true) val id: Int, val title: String,
                     @ColumnInfo(name = "note_content") val description: String)