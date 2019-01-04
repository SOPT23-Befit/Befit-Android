package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.*

class HomeFragmentBrandPagerAdapter(fm : FragmentManager, val FragmentCount : Int) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position)
        {
            0->return MainBrandFragment1()
            1->return MainBrandFragment2()//SearchFragment()
            2->return MainBrandFragment3()

            else->return null
        }
    }

    override fun getCount(): Int = FragmentCount



}
