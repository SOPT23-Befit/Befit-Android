package com.sopt.befit.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.widget.Toast
import com.sopt.befit.fragment.*

class SearchPBFragmentStatePagerAdapter(fm: FragmentManager, val FragmentCount: Int, var b: Bundle) : FragmentStatePagerAdapter(fm) {

    lateinit var fragment: Fragment

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return EachProductFragment().apply {
                    arguments=Bundle().apply {
                        putString("search", b.getString("search"))
                    }
                }
            }
            1
            -> {
                return EachBrandFragment().apply {
                    arguments=Bundle().apply {
                        putString("search", b.getString("search"))
                    }
                }
            }
            else -> return null
        }
    }

    override fun getCount(): Int = FragmentCount

}
