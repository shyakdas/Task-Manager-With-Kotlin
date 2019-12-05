package com.todolist.com.taskmanager.database

import androidx.room.*
import com.todolist.com.taskmanager.model.NoteModel
import io.reactivex.Flowable

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<NoteModel>>

    @Insert
    fun insert(vararg listCategories: NoteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(item: NoteModel)
}