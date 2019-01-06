package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.EachBrandRecyclerViewAdapter
import com.sopt.befit.data.BrandData
import kotlinx.android.synthetic.main.fragment_s_brand.*

class SBrandFragment : Fragment() {

    lateinit var eachBrandRecyclerViewAdapter: EachBrandRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_s_brand, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        val dataList : ArrayList<BrandData> = ArrayList()

        eachBrandRecyclerViewAdapter = EachBrandRecyclerViewAdapter(activity!!, dataList)
        rv_frag_s_brand_list.adapter = eachBrandRecyclerViewAdapter
        rv_frag_s_brand_list.layoutManager = LinearLayoutManager(activity)
    }
}