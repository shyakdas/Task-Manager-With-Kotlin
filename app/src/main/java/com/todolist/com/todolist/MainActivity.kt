package com.todolist.com.todolist

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.todolist.com.todolist.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search.setOnClickListener(this)
        sort.setOnClickListener(this)
        new_note.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search -> {
                var searchFragment = SearchFragment()
                supportFragmentManager.inTransaction { add(R.id.top, searchFragment) }
            }
            R.id.sort -> Toast.makeText(this, "Sort", Toast.LENGTH_LONG).show()
            R.id.new_note -> Toast.makeText(this, "New Note", Toast.LENGTH_LONG).show()
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}