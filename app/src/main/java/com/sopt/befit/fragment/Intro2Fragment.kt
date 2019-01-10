package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.HomeFragmentBannerPagerAdapter
import com.sopt.befit.adapter.Utilities
import kotlinx.android.synthetic.main.fragment_home.*

class Intro2Fragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val brandFragmentView: View = inflater!!.inflate(R.layout.intro2, container, false)
            Utilities.setGlobalFont(brandFragmentView, activity!!);
            return brandFragmentView
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)



        }

    }
