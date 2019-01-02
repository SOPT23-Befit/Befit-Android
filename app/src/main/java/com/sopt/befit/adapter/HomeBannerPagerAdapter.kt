package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.fragment.BrandRankingFragment
import com.sopt.befit.fragment.HomeFragment
import com.sopt.befit.fragment.MypageFragment

class HomeBannerPagerAdapter(fm : FragmentManager, val FragmentCount : Int) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position)
        {


            else->return null
        }
    }

    override fun getCount(): Int = FragmentCount



}