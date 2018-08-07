package com.todolist.com.todolist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todolist.com.todolist.R
import com.todolist.com.todolist.listener.NoteItemListener
import com.todolist.com.todolist.model.NoteModel
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val items: List<NoteModel>, private val context: Context, var noteItemListener: NoteItemListener) :
        RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { noteItemListener.onPostClick(position) }
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitle = view.item_title!!
        private val mDescription = view.item_description!!

        fun bind(noteModel: NoteModel) {
            mTitle.text = noteModel.title
            mDescription.text = noteModel.description
        }
    }
}