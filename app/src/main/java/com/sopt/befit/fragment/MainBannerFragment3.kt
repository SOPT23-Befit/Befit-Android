package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import kotlinx.android.synthetic.main.fragment_main_banner3.*
import org.jetbrains.anko.support.v4.startActivity

class MainBannerFragment3: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_banner3, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iv_home_fragment_banner_3.setOnClickListener(){

            startActivity<BrandMainActivity>("idx" to 21)
        }

    }
}