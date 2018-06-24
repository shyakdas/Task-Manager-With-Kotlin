package com.todolist.com.todolist.addnote

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.todolist.com.todolist.R
import kotlinx.android.synthetic.main.addnote.*

class AddNoteFragment : DialogFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.addnote, container, false)
        val mSaveNote: TextView = view.findViewById(R.id.note_save)
        val mCrossButton: ImageView = view.findViewById(R.id.imageView)
        mSaveNote.setOnClickListener(this)
        mCrossButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.note_save -> saveNote()
            R.id.imageView -> dismiss()
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
            Toast.makeText(context, getString(R.string.note_save), Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}