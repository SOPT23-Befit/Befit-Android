package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimBrandRecyclerViewAdapter
import com.sopt.befit.data.JjimBrandData
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandRankingFragment :Fragment(){
    lateinit var jjimBrandRecyclerViewAdapter: JjimBrandRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_brand, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        //내가 좋아하는

        val dataList : ArrayList<JjimBrandData> = ArrayList()
        dataList.add(JjimBrandData("유니클로"))
        dataList.add(JjimBrandData("서브웨이"))
        dataList.add(JjimBrandData("프링글스"))
        dataList.add(JjimBrandData("유니클로"))
        dataList.add(JjimBrandData("서브웨이"))
        dataList.add(JjimBrandData("프링글스"))

        jjimBrandRecyclerViewAdapter = JjimBrandRecyclerViewAdapter(activity!!, dataList)
        rv_frag_brand_list.adapter = jjimBrandRecyclerViewAdapter
        rv_frag_brand_list.layoutManager = LinearLayoutManager(activity)
    }
}