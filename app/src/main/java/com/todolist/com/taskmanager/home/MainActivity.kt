package com.todolist.com.taskmanager.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.todolist.com.taskmanager.R
import com.todolist.com.taskmanager.TaskManagerApplication
import com.todolist.com.taskmanager.adapter.NoteAdapter
import com.todolist.com.taskmanager.addnote.AddNoteFragment
import com.todolist.com.taskmanager.database.AppDatabase
import com.todolist.com.taskmanager.database.NoteDao
import com.todolist.com.taskmanager.listener.NoteItemListener
import com.todolist.com.taskmanager.search.SearchFragment
import com.todolist.com.taskmanager.sort.SortFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, NoteItemListener {

    private lateinit var listCategoryDao: NoteDao
    private lateinit var appDatabase: AppDatabase
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_main.setOnClickListener(this)
        sort_main.setOnClickListener(this)
        new_note_main.setOnClickListener(this)
        appDatabase = TaskManagerApplication.database!!
        listCategoryDao = appDatabase.listNoteDao()
        recylerview.layoutManager = LinearLayoutManager(this)
        compositeDisposable.addAll(appDatabase.listNoteDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { itemList ->
                    listCategoryDao.getAll()
                    recylerview.adapter = NoteAdapter(itemList, this, this)
                }
        )
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

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onPostClick(position: Int, title: String, description: String) {
        val addNoteFragment = AddNoteFragment()
        val args = Bundle()
        args.putBoolean("isToUpdateNote", true)
        args.putInt("position", position)
        args.putString("title", title)
        args.putString("description", description)
        addNoteFragment.arguments = args
        supportFragmentManager.inTransaction { add(R.id.top, addNoteFragment) }
    }
}