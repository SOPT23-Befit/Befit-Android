package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.*

class IntroPagerAdapter(fm : FragmentManager, val FragmentCount : Int) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position)
        {
            0->return Intro1Fragment()
            1->return Intro2Fragment()//SearchFragment()
            2->return Intro3Fragment()
            3->return Intro4Fragment()

            else->return null
        }
    }

    override fun getCount(): Int = FragmentCount



}