package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.*

class JjimPBFragmentStatePagerAdapter(fm: FragmentManager, val FragmentCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return JjimProductFragment()
            1 -> return JjimBrandFragment()

            else -> return null
        }
    }

    override fun getCount(): Int = FragmentCount

}
