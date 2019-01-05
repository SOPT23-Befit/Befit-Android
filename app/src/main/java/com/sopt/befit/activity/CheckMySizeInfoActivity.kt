package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.BrandRankingRecyclerViewAdapter
import com.sopt.befit.adapter.MySizeInfoRecyclerViewAdapter
import com.sopt.befit.data.BrandRankingData
import com.sopt.befit.data.CheckMySizeData
import kotlinx.android.synthetic.main.activity_check_my_size_info.*
import kotlinx.android.synthetic.main.fragment_ranking.*
import org.jetbrains.anko.startActivity

class CheckMySizeInfoActivity : AppCompatActivity() {
    lateinit var mySizeInfoRecyclerViewAdapter: MySizeInfoRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_my_size_info)
        setRecyclerView()
        ibtn_back.setOnClickListener(){

            finish()

        }
    }

    private fun setRecyclerView() {

        //내가 좋아하는

        val dataList2 : ArrayList<CheckMySizeData> = ArrayList()
        dataList2.add(CheckMySizeData("","Outer"))


        mySizeInfoRecyclerViewAdapter = MySizeInfoRecyclerViewAdapter(this!!, dataList2)
        rv_my_size_info_list.adapter = mySizeInfoRecyclerViewAdapter
        rv_my_size_info_list.layoutManager = LinearLayoutManager(this)
    }
}
