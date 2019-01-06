package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.data.ProductData
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    lateinit var productListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setRecyclerView()

        setViewClickListener()
    }

    private fun setViewClickListener() {
        tv_category_new.setOnClickListener {
            //리사이클러뷰 재통신
            if(tv_category_popular.isChecked){
                tv_category_popular.setChecked(false)
                tv_category_new.setChecked(true)
            }
        }
        tv_category_popular.setOnClickListener {
            //리사이클러 뷰 재통신
            if(tv_category_new.isChecked){
                tv_category_new.setChecked(false)
                tv_category_popular.setChecked(true)
            }
        }
    }

    private fun setRecyclerView() {

        var dataList: ArrayList<ProductData> = ArrayList()

        productListRecyclerViewAdapter = ProductListRecyclerViewAdapter(this, dataList)
        rv_category_product_list.adapter = productListRecyclerViewAdapter
        rv_category_product_list.layoutManager = GridLayoutManager(this, 2)
    }
}
