package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.adapter.MySizeLookupRecyclerViewAdapter
import com.sopt.befit.data.MySizeLookupData
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import kotlinx.android.synthetic.main.activity_my_size_lookup.*
import kotlinx.android.synthetic.main.rv_item_my_size_lookup.*
import kotlinx.android.synthetic.main.rv_item_my_size_lookup.view.*

class MySizeLookupActivity : AppCompatActivity() {

    lateinit var mySizeLookupRecyclerViewAdapter: MySizeLookupRecyclerViewAdapter
    lateinit var mySizeLookupDeleteRecyclerViewAdapter: MySizeLookupRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_lookup)

        setRecyclerView()

        setViewClickListener()
    }

    private fun setViewClickListener() {
        tv_my_size_add_edit.setOnClickListener {
            if (tv_my_size_add_edit.text == "편집") {
                tv_my_size_add_edit.setText("완료")

                //mySizeLookupRecyclerViewAdapter.notifyItemRangeChanged(0, mySizeLookupRecyclerViewAdapter.getItemCount());
                rv_my_size_lookup.adapter = mySizeLookupDeleteRecyclerViewAdapter
            } else {
                tv_my_size_add_edit.setText("편집")
                img_rv_item_my_size_lookup_delete.visibility = View.GONE
                rv_my_size_lookup.adapter = mySizeLookupRecyclerViewAdapter
            }
        }
    }

    private fun setRecyclerView() {
        var dataList: ArrayList<MySizeLookupData> = ArrayList()

        mySizeLookupRecyclerViewAdapter = MySizeLookupRecyclerViewAdapter(this, dataList, 0)
        mySizeLookupDeleteRecyclerViewAdapter = MySizeLookupRecyclerViewAdapter(this, dataList, 1)

        rv_my_size_lookup.adapter = mySizeLookupRecyclerViewAdapter
        rv_my_size_lookup.layoutManager = GridLayoutManager(this, 2)
    }
}
