package com.sopt.befit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.sopt.befit.fragment.*


class MyFragmentStatePagerAdapter(fm: FragmentManager, val FragmentCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
            1 -> return SearchFragment()
            2 -> return BrandRankingFragment()
            3 -> return JjimFragment()
            4 -> return MypageFragment()

            else -> return null
        }
    }

    override fun getCount(): Int = FragmentCount

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
