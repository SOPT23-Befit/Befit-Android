package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimProductRecyclerViewAdapter
import com.sopt.befit.data.JjimProductData
import kotlinx.android.synthetic.main.activity_brand_main.*
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    lateinit var jjimProductRecyclerViewAdapter: JjimProductRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        var dataList: ArrayList<JjimProductData> = ArrayList()
        dataList.add(JjimProductData("유니클로", "후리스", "29800원", true))
        dataList.add(JjimProductData("유니클로", "집업", "35000원", false))
        dataList.add(JjimProductData("유니클로", "히트택", "19000원", true))
        dataList.add(JjimProductData("유니클로", "양말", "200원", true))
        dataList.add(JjimProductData("유니클로", "뽀글이", "25000원", false))
        dataList.add(JjimProductData("유니클로", "레깅스", "9000원", true))

        jjimProductRecyclerViewAdapter = JjimProductRecyclerViewAdapter(this, dataList)
        rv_category_product_list.adapter = jjimProductRecyclerViewAdapter
        rv_category_product_list.layoutManager = GridLayoutManager(this, 2)
    }
}