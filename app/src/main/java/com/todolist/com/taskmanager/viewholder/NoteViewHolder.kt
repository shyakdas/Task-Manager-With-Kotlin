package com.todolist.com.taskmanager.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.todolist.com.taskmanager.model.NoteModel
import kotlinx.android.synthetic.main.item_note.view.*

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mTitle = view.item_title!!
    private val mDescription = view.item_description!!

    fun bind(noteModel: NoteModel) {
        mTitle.text = noteModel.title
        mDescription.text = noteModel.description
    }
}