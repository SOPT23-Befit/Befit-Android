package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.*


class MyFragmentStatePagerAdapter(fm : FragmentManager,val FragmentCount : Int) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position)
        {
            0->return HomeFragment()
            1->return HomeFragment()//SearchFragment()
            2->return BrandRankingFragment()
            3->return JJimFragment()
            4->return MypageFragment()

            else->return null
        }
    }

    override fun getCount(): Int = FragmentCount



}
