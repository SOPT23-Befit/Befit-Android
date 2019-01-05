package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimProductRecyclerViewAdapter
import com.sopt.befit.data.JjimProductData
import kotlinx.android.synthetic.main.activity_brand_main.*

class BrandMainActivity : AppCompatActivity() {

    lateinit var jjimProductRecyclerViewAdapter: JjimProductRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_main)

        setRecyclerView()

        setViewClickListener()

        tv_brand_main_b_name_kor.setText(intent.getStringExtra("b_name"))

        /*
        refresh_brand_main_act.setOnRefresh {
            toast("새로 고침!")
        }
         */
    }

    private fun setViewClickListener() {
        tv_brand_main_new.setOnClickListener {
            //리사이클러뷰 재통신
            if(tv_brand_main_popular.isChecked){
                tv_brand_main_popular.setChecked(false)
                tv_brand_main_new.setChecked(true)
            }
        }
        tv_brand_main_popular.setOnClickListener {
            //리사이클러 뷰 재통신
            if(tv_brand_main_new.isChecked){
                tv_brand_main_new.setChecked(false)
                tv_brand_main_popular.setChecked(true)
            }
        }
    }

    private fun setRecyclerView() {

        //통신 브랜드에 맞는 프로덕션들 출력

        var dataList: ArrayList<JjimProductData> = ArrayList()
        dataList.add(JjimProductData("유니클로", "후리스", "29800원", true))
        dataList.add(JjimProductData("유니클로", "집업", "35000원", false))
        dataList.add(JjimProductData("유니클로", "히트택", "19000원", true))
        dataList.add(JjimProductData("유니클로", "양말", "200원", true))
        dataList.add(JjimProductData("유니클로", "뽀글이", "25000원", false))
        dataList.add(JjimProductData("유니클로", "레깅스", "9000원", true))

        jjimProductRecyclerViewAdapter = JjimProductRecyclerViewAdapter(this, dataList)
        rv_brand_main_product_list.adapter = jjimProductRecyclerViewAdapter
        rv_brand_main_product_list.layoutManager = GridLayoutManager(this, 2)
    }
}