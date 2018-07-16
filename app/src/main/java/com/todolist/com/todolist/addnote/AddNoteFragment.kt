package com.todolist.com.todolist.addnote

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.todolist.com.todolist.R
import com.todolist.com.todolist.ToDoList
import com.todolist.com.todolist.database.AppDatabase
import com.todolist.com.todolist.database.NoteDao
import com.todolist.com.todolist.home.MainActivity
import com.todolist.com.todolist.model.NoteModel
import kotlinx.android.synthetic.main.addnote.*


class AddNoteFragment : Fragment(), View.OnClickListener {

    private lateinit var listCategoryDao: NoteDao
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.addnote, container, false)
        val mSaveNote: FloatingActionButton = view.findViewById(R.id.note_save)
        val mCrossButton: ImageView = view.findViewById(R.id.search_main)
        mSaveNote.setOnClickListener(this)
        mCrossButton.setOnClickListener(this)
        appDatabase = ToDoList.database!!
        listCategoryDao = appDatabase.listNoteDao()
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.note_save -> saveNote()
            R.id.search_main -> activity?.onBackPressed()
        }
    }

    private fun saveNote() {
        var mTitle: String = note_title.text.toString()
        var mDescription: String = note_description.text.toString()
        if (mTitle?.isNullOrEmpty()) Toast.makeText(context, getString(R.string.enter_title), Toast.LENGTH_SHORT).show()
        else if (mDescription?.isNullOrEmpty()) Toast.makeText(context, getString(R.string.enter_description), Toast.LENGTH_SHORT).show()
        else if (mTitle?.isNullOrEmpty() && mDescription?.isNullOrEmpty())
            Toast.makeText(context, getString(R.string.message_empty_note), Toast.LENGTH_SHORT).show()
        else {
            var noteMode = NoteModel(0, mTitle, mDescription)
            AsyncTask.execute {
                listCategoryDao.insert(noteMode)
                activity?.runOnUiThread {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity!!.finish()
                }
            }
        }
    }
}