package com.sopt.befit.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.CompareSizeFragment1
import com.sopt.befit.fragment.CompareSizeFragment2
import com.sopt.befit.fragment.CompareSizeFragment3

class CompareSizeAdapter(fm: FragmentManager, val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return CompareSizeFragment1()
            1 -> return CompareSizeFragment2()
            2 -> return CompareSizeFragment3()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount

}