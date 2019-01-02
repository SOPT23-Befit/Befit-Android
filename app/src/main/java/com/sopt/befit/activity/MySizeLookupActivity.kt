package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.MySizeLookupRecyclerViewAdapter
import com.sopt.befit.data.MySizeLookupData
import kotlinx.android.synthetic.main.activity_my_size_lookup.*

class MySizeLookupActivity : AppCompatActivity() {

    lateinit var mySizeLookupRecyclerViewAdapter: MySizeLookupRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_lookup)

        setRecyclerView()

        setViewClickListener()
    }

    private fun setViewClickListener() {
        tv_my_size_add_edit.setOnClickListener{
            if(tv_my_size_add_edit.text=="편집") {
                tv_my_size_add_edit.setText("제거")
            }else{
                tv_my_size_add_edit.setText("편집")
            }
            //편집 페이지로 가기
            //모든 리사이클러뷰가 delete 사진이 활성화
            //누르면 바로 삭제
        }
    }

    private fun setRecyclerView() {
        var dataList: ArrayList<MySizeLookupData> = ArrayList()
        dataList.add(MySizeLookupData("후아유", "양털뽀글이", "53000", false))
        dataList.add(MySizeLookupData("후아유", "양털뽀글이", "53000", false))
        dataList.add(MySizeLookupData("후아유", "양털뽀글이", "53000", false))
        dataList.add(MySizeLookupData("후아유", "양털뽀글이", "53000", false))


        mySizeLookupRecyclerViewAdapter = MySizeLookupRecyclerViewAdapter(this, dataList)
        rv_my_size_lookup.adapter = mySizeLookupRecyclerViewAdapter
        rv_my_size_lookup.layoutManager = GridLayoutManager(this, 2)

    }
}
