package com.todolist.com.taskmanager.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.todolist.com.taskmanager.R
import kotlinx.android.synthetic.main.search.*

class SearchFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.search, container, false)
        val mBackPressed: ImageView = view.findViewById(R.id.search_main)
        mBackPressed.setOnClickListener(this)
        loadData()
        return view
    }

    private fun loadData() {
        search_note?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_main -> {
                activity?.onBackPressed()
            }
        }
    }
}