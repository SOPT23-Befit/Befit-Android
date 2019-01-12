package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.db.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_main_banner1.*
import kotlinx.android.synthetic.main.fragment_main_banner3.*
import org.jetbrains.anko.support.v4.startActivity

class MainBannerFragment3: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_banner3, container, false)
        return brandFragmentView
    }

    //val token = SharedPreferenceController.getAuthorization(activity!!)

    //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"


    lateinit var token :String
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(activity!!).load(resources.getDrawable(R.drawable.banner3)).into(iv_home_fragment_banner_3)
        iv_home_fragment_banner_3.setOnClickListener(){

            token = SharedPreferenceController.getAuthorization(activity!!)
            startActivity<BrandMainActivity>("idx" to 21,"token" to token)
        }

    }
}