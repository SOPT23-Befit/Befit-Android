package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.EachBrandRecyclerViewAdapter
import com.sopt.befit.adapter.MySizeInfoCategoryRecyclerViewAdapter
import com.sopt.befit.adapter.MySizeLookupRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_my_size_info_category.*
import kotlinx.android.synthetic.main.activity_my_size_lookup.*

class MySizeInfoCategoryActivity : BaseActivity() {

    lateinit var mySizeInfoCategoryRecyclerViewAdapter: MySizeInfoCategoryRecyclerViewAdapter

    val dataList: ArrayList<Int> by lazy {
        ArrayList<Int>()
    }

    val s: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_info_category)

        setViewClickListener()

        setRecyclerView()
    }

    private fun setViewClickListener() {
        img_my_size_info_category_back.setOnClickListener{
            finish()
        }
    }

    private fun setRecyclerView() {
        dataList.add(0)
        dataList.add(1)
        dataList.add(2)
        dataList.add(3)
        dataList.add(4)
        dataList.add(5)
        dataList.add(6)
        dataList.add(7)
        dataList.add(8)
        dataList.add(9)
        dataList.add(10)
        dataList.add(11)
        dataList.add(12)
        dataList.add(13)

        if(s==1){
            //남자일때
            dataList.removeAt(13)
            dataList.removeAt(8)
        }

        mySizeInfoCategoryRecyclerViewAdapter = MySizeInfoCategoryRecyclerViewAdapter(this, dataList)
        rv_item_size_info_category_list.adapter = mySizeInfoCategoryRecyclerViewAdapter
        rv_item_size_info_category_list.layoutManager = LinearLayoutManager(this)
    }


}
