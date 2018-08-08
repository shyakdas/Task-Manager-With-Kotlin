package com.todolist.com.todolist.home

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
import com.todolist.com.todolist.listener.NoteItemListener
import com.todolist.com.todolist.model.NoteModel
import com.todolist.com.todolist.search.SearchFragment
import com.todolist.com.todolist.sort.SortFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, NoteItemListener {

    private lateinit var listCategoryDao: NoteDao
    private lateinit var appDatabase: AppDatabase
    private var itemList: List<NoteModel> = ArrayList()

    companion object {
        var TAG: String = MainActivity.javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_main.setOnClickListener(this)
        sort_main.setOnClickListener(this)
        new_note_main.setOnClickListener(this)
        appDatabase = ToDoList.database!!
        listCategoryDao = appDatabase.listNoteDao()
        recylerview.layoutManager = LinearLayoutManager(this)
        appDatabase.listNoteDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { itemList ->
                    listCategoryDao.getAll()
                    recylerview.adapter = NoteAdapter(itemList, this, this)
                }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_main -> {
                val searchFragment = SearchFragment()
                supportFragmentManager.inTransaction { add(R.id.top, searchFragment) }
            }
            R.id.sort_main -> {
                val sortFragment = SortFragment()
                supportFragmentManager.inTransaction { add(R.id.top, sortFragment) }
            }
            R.id.new_note_main -> {
                val addNoteFragment = AddNoteFragment()
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

    override fun onPostClick(position: Int, noteTitle: String, noteDescription: String) {
        Log.d(TAG, "position $position")
        val addNoteFragment = AddNoteFragment()
        val args = Bundle()
        args.putBoolean("isToUpdateNote", true)
        args.putInt("position", position)
        args.putString("title", noteTitle)
        args.putString("description", noteDescription)
        addNoteFragment.arguments = args
        supportFragmentManager.inTransaction { add(R.id.top, addNoteFragment) }
    }
}