package com.todolist.com.todolist.home

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.todolist.com.todolist.R
import com.todolist.com.todolist.ToDoList
import com.todolist.com.todolist.adapter.NoteAdapter
import com.todolist.com.todolist.addnote.AddNoteFragment
import com.todolist.com.todolist.database.AppDatabase
import com.todolist.com.todolist.database.NoteDao
import com.todolist.com.todolist.search.SearchFragment
import com.todolist.com.todolist.sort.SortFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var listCategoryDao: NoteDao
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_main.setOnClickListener(this)
        sort_main.setOnClickListener(this)
        new_note_main.setOnClickListener(this)
        appDatabase = ToDoList.database!!
        listCategoryDao = appDatabase.listNoteDao()
        recylerview.layoutManager = LinearLayoutManager(this)
        AsyncTask.execute {
            recylerview.adapter = NoteAdapter(listCategoryDao.getAll(), this)
            Log.d("TAG", "listSize==" + listCategoryDao.getAll().size)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_main -> {
                var searchFragment = SearchFragment()
                supportFragmentManager.inTransaction { add(R.id.top, searchFragment) }
            }
            R.id.sort_main -> {
                var sortFragment = SortFragment()
                supportFragmentManager.inTransaction { add(R.id.top, sortFragment) }
            }
            R.id.new_note_main -> {
                var addNoteFragment = AddNoteFragment()
                supportFragmentManager.inTransaction { add(R.id.top, addNoteFragment) }
            }
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}