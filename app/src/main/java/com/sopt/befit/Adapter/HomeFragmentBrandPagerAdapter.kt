package com.sopt.befit.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.befit.data.BrandRecommendData
import com.sopt.befit.fragment.*

class HomeFragmentBrandPagerAdapter(fm : FragmentManager, val FragmentCount : Int,var data : ArrayList<BrandRecommendData>) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position)
        {
            0->{
                var fragment = MainBrandFragment1()
                var bundle = Bundle()
                bundle.putSerializable("BrandRecommendData",data.get(0))
                fragment.arguments = bundle
                return fragment
            }
            1->{
                var fragment = MainBrandFragment2()
                var bundle = Bundle()
                bundle.putSerializable("BrandRecommendData",data.get(1))
                fragment.arguments = bundle
                return fragment

            }//SearchFragment()
            2->{
                var fragment = MainBrandFragment3()
                var bundle = Bundle()
                bundle.putSerializable("BrandRecommendData",data.get(2))
                fragment.arguments = bundle
                return fragment
            }

            else->return null
        }
    }

    override fun getCount(): Int = FragmentCount



}
