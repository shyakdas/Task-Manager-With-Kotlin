package com.todolist.com.todolist.addnote

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.todolist.com.todolist.R
import com.todolist.com.todolist.ToDoList
import com.todolist.com.todolist.database.AppDatabase
import com.todolist.com.todolist.database.NoteDao
import com.todolist.com.todolist.home.MainActivity
import com.todolist.com.todolist.model.NoteModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.addnote.*


class AddNoteFragment : Fragment(), View.OnClickListener {

    private lateinit var listCategoryDao: NoteDao
    private lateinit var appDatabase: AppDatabase

    companion object {
        private val TAG: String = AddNoteFragment.javaClass.name
        private var isForUpdate: Boolean = false
        private var noteId: Long = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.addnote, container, false)
        val mSaveNote: FloatingActionButton = view.findViewById(R.id.note_save)
        val mCrossButton: ImageView = view.findViewById(R.id.search_main)
        val mNoteBar: TextView = view.findViewById(R.id.create_note)
        val title: EditText = view.findViewById(R.id.note_title)
        val description: EditText = view.findViewById(R.id.note_description)
        mSaveNote.setOnClickListener(this)
        mCrossButton.setOnClickListener(this)
        appDatabase = ToDoList.database!!
        listCategoryDao = appDatabase.listNoteDao()
        if (arguments?.getBoolean("isToUpdateNote") != null) {
            isForUpdate = true
            mNoteBar.text = "Edit Note"
            val editNoteTitle: String = arguments?.getString("title", "")!!
            val editNoteDescription: String = arguments?.getString("description", "")!!
            val editNotePosition = arguments?.getInt("position", -1)
            noteId = editNotePosition!!.toLong()
            title.setText(editNoteTitle)
            description.setText(editNoteDescription)
        }
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.note_save -> saveNote()
            R.id.search_main -> activity?.onBackPressed()
        }
    }

    private fun saveNote() {
        val mTitle: String = note_title.text.toString()
        val mDescription: String = note_description.text.toString()
        if (mTitle.isNullOrEmpty()) Toast.makeText(context, getString(R.string.enter_title), Toast.LENGTH_SHORT).show()
        else if (mDescription.isNullOrEmpty()) Toast.makeText(context, getString(R.string.enter_description), Toast.LENGTH_SHORT).show()
        else if (mTitle.isNullOrEmpty() && mDescription.isNullOrEmpty())
            Toast.makeText(context, getString(R.string.message_empty_note), Toast.LENGTH_SHORT).show()
        else {
            val noteMode = NoteModel(noteId+1, mTitle, mDescription)
            if (!isForUpdate) {
                Single.fromCallable { listCategoryDao.insert(noteMode) }
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
            } else {
                Single.fromCallable { listCategoryDao.updateItem(noteMode) }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }
}