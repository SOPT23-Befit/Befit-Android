package com.sopt.befit.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.*

class SearchPBFragmentStatePagerAdapter(fm: FragmentManager, val FragmentCount: Int, var b: Bundle) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                EachProductFragment().arguments = b
                return EachProductFragment()
            }
            1
            -> {
                EachBrandFragment().arguments = b
                return EachBrandFragment()
            }

            else -> return null
        }
    }

    override fun getCount(): Int = FragmentCount

}
