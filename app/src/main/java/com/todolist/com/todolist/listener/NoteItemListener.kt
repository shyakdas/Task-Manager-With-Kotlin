package com.todolist.com.todolist.listener

interface NoteItemListener {

    fun onPostClick(position: Int, title: String, description: String)
}