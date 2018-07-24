package com.theevilroot.amcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.theevilroot.amcalculator.fragments.FragmentAdapter
import com.theevilroot.amcalculator.fragments.FragmentCalculator
import com.theevilroot.amcalculator.fragments.FragmentHistory

class MainActivity : AppCompatActivity() {

    private val viewPager by lazy { findViewById<ViewPager>(R.id.view_pager) }

    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TheHolder.fragments = arrayOf(FragmentCalculator.create(this), FragmentHistory.create(this))
        adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}
