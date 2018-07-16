package com.todolist.com.todolist.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.todolist.com.todolist.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<NoteModel>

    @Insert
    fun insert(vararg listCategories: NoteModel)
}