package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sopt.befit.R
import com.sopt.befit.data.BrandRecommendData
import kotlinx.android.synthetic.main.fragment_main_brand_1.*
import kotlinx.android.synthetic.main.fragment_main_brand_2.*
import kotlinx.android.synthetic.main.fragment_main_brand_3.*

class MainBrandFragment3: Fragment(){
//    companion object {
//        private var instance: HomeFragment? = null
//        @Synchronized
//        fun getInstance(mainfeed_url3: String,
//                        name_english3: String,
//                        image_url3_1 :String,image_url3_2: String, image_url3_3: String): HomeFragment {
//
//            if (instance == null) {
//                instance = HomeFragment().apply {
//                    arguments = Bundle().apply {
//                        putString("mainfeed_url3", mainfeed_url3)
//                        putString("name_english3", name_english3)
//                        putString("image_url3_1", image_url3_1)
//                        putString("image_url3_2", image_url3_2)
//                        putString("image_url3_3", image_url3_3)
//
//
//
//                    }
//                }
//            }
//            return instance!!
//        }
//    }

    lateinit var brandData : BrandRecommendData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
               brandData = it.getSerializable("BrandRecommendData") as BrandRecommendData
        }
        Log.d("cccc",brandData.mainfeed_url)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_brand_3, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Glide.with(activity!!).load(brandData.mainfeed_url).into(iv_main_brand_3)
        tv_main_brand_3.text=brandData.name_english
        Glide.with(activity!!).load(brandData.products[0].image_url).into(iv_main_brand_3_product_1)
        Glide.with(activity!!).load(brandData.products[1].image_url).into(iv_main_brand_3_product_2)
        Glide.with(activity!!).load(brandData.products[2].image_url).into(iv_main_brand_3_product_3)
        tv_aaa_main_brand3_recommend_p_name1.text=brandData.products[0].name
        tv_aaa_main_brand3_recommend_p_name2.text=brandData.products[1].name
        tv_aaa_main_brand3_recommend_p_name3.text=brandData.products[2].name



    }
}