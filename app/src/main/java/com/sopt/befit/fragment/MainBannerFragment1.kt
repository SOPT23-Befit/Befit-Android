package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import kotlinx.android.synthetic.main.fragment_main_banner1.*
import kotlinx.android.synthetic.main.fragment_main_banner2.*
import kotlinx.android.synthetic.main.fragment_main_banner3.*
import org.jetbrains.anko.support.v4.startActivity

class MainBannerFragment1: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_banner1, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        iv_home_fragment_banner_1.setOnClickListener(){
            startActivity<BrandMainActivity>("idx" to 33)//브랜드 인덱스 값 넘기기 매치글로브

        }

    }

}