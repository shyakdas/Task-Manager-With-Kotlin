package com.todolist.com.todolist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todolist.com.todolist.R
import com.todolist.com.todolist.model.NoteModel
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val items: List<NoteModel>, private val context: Context) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder?.mTitle?.text = items.get(position).title
        holder?.mDescription?.text = items.get(position).description
    }
}

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val mTitle = view.item_title!!
    val mDescription = view.item_description!!
}