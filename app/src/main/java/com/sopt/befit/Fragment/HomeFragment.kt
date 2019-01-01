package com.sopt.befit.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimProductRecyclerViewAdapter
import com.sopt.befit.data.JjimProductData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(){
    lateinit var HomefragmentAdapter: JjimProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }
    private fun setRecyclerView(){

        var dataList: ArrayList<JjimProductData> = ArrayList()
        dataList.add(JjimProductData("","","",false))

        HomefragmentAdapter = JjimProductRecyclerViewAdapter(activity!!, dataList)
        rv_my_rec_item_list.adapter=HomefragmentAdapter
        rv_my_rec_item_list.layoutManager= GridLayoutManager(activity,2)

    }
}

