package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.data.BrandRecommendData
import com.sopt.befit.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_brand_main.*
import kotlinx.android.synthetic.main.fragment_main_brand_1.*
import kotlinx.android.synthetic.main.fragment_main_brand_3.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class MainBrandFragment1: Fragment(){


    lateinit var brandData : BrandRecommendData
    var flag : Int = 1//0일때 브랜드 클릭시
    //1일때는 하위상품 클릭시

    //var token : String = SharedPreferenceController.getAuthorization(activity!!)
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            brandData = it.getSerializable("BrandRecommendData") as BrandRecommendData
        }

        Log.d("zzzzzz",brandData.mainfeed_url)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_brand_1, container, false)
        Utilities.setGlobalFont(brandFragmentView, activity!!);
        return brandFragmentView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Glide.with(activity!!).load(brandData.mainfeed_url).into(iv_main_brand_1)
        tv_main_brand_1.text=brandData.name_english
        Glide.with(activity!!).load(brandData.products[0].image_url).into(iv_main_brand_1_product_1)
        Glide.with(activity!!).load(brandData.products[1].image_url).into(iv_main_brand_1_product_2)
        Glide.with(activity!!).load(brandData.products[2].image_url).into(iv_main_brand_1_product_3)

        tv_aaa_main_brand1_recommend_p_name1.text = brandData.products[0].name
        tv_aaa_main_brand1_recommend_p_name2.text = brandData.products[1].name
        tv_aaa_main_brand1_recommend_p_name3.text = brandData.products[2].name


        iv_main_brand_1.setOnClickListener(){
            flag=0
            startActivity<BrandMainActivity>("idx" to brandData.idx, "flag" to flag,"token" to token)
        }

        iv_main_brand_1_product_1.setOnClickListener(){
            flag=1
            startActivity<BrandMainActivity>("ProductData" to brandData.products[0],"flag" to flag,"token" to token)
        }
        iv_main_brand_1_product_2.setOnClickListener(){
            flag=1
            startActivity<BrandMainActivity>("ProductData" to brandData.products[1],"flag" to flag,"token" to token)
        }
        iv_main_brand_1_product_3.setOnClickListener(){
            flag=1
            startActivity<BrandMainActivity>("ProductData" to brandData.products[2],"flag" to flag,"token" to token)
        }



    }
}

//뷰페이저 프래그먼트가 똑같은 ㄹㅔ이아웃일때  하나로 어떻게ㅜ퉁칠지 물어보기