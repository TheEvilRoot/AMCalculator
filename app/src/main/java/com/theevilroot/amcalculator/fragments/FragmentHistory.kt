package com.theevilroot.amcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theevilroot.amcalculator.MainActivity
import com.theevilroot.amcalculator.R
import com.theevilroot.amcalculator.ResultViewAdapter
import com.theevilroot.amcalculator.TheHolder

class FragmentHistory: Fragment() {
    private lateinit var activity: MainActivity

    private val adapter = ResultViewAdapter()

    private lateinit var historyList: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_history, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        with(view) {
            toolbar = findViewById(R.id.toolbar)
            historyList = findViewById(R.id.history)
        }
        toolbar.title = "История"
        toolbar.setTitleTextColor(activity.resources.getColor(R.color.colorPrimary))
        historyList.layoutManager = LinearLayoutManager(activity)
        historyList.adapter = adapter
        updateUI()
    }

    fun updateUI() {
        adapter.items = TheHolder.history
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun create(activity: MainActivity): FragmentHistory{
            val fragment = FragmentHistory()
            fragment.activity = activity
            return fragment
        }
    }
}