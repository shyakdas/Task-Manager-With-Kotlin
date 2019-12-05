package com.todolist.com.taskmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todolist.com.taskmanager.R
import com.todolist.com.taskmanager.listener.NoteItemListener
import com.todolist.com.taskmanager.model.NoteModel
import com.todolist.com.taskmanager.viewholder.NoteViewHolder

class NoteAdapter(private val items: List<NoteModel>, private val context: Context, private var noteItemListener: NoteItemListener) :
        RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            noteItemListener.onPostClick(position,
                    items[position].title, items.get(position).description)
        }
    }
}