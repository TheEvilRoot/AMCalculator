package com.theevilroot.amcalculator.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.theevilroot.amcalculator.TheHolder

class FragmentAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    override fun getItem(position: Int): Fragment =
            TheHolder.fragments[position]
    override fun getCount(): Int =
            TheHolder.fragments.count()
}