package com.todolist.com.taskmanager.listener

interface NoteItemListener {

    fun onPostClick(position: Int, title: String, description: String)
}