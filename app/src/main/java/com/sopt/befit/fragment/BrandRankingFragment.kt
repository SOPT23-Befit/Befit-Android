package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.BrandRankingRecyclerViewAdapter
import com.sopt.befit.data.BrandRankingData
import kotlinx.android.synthetic.main.fragment_ranking.*

class BrandRankingFragment :Fragment(){
    lateinit var brandRankingRecyclerViewAdapter: BrandRankingRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandRankingFragmentView: View = inflater!!.inflate(R.layout.fragment_ranking, container, false)
        return brandRankingFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setRecyclerView()
    }

    private fun setRecyclerView() {

        //내가 좋아하는


        val dataList2 : ArrayList<BrandRankingData> = ArrayList()
        dataList2.add(BrandRankingData("1","","유니클로"))


        brandRankingRecyclerViewAdapter = BrandRankingRecyclerViewAdapter(activity!!, dataList2)
        rv_brand_ranking_list.adapter = brandRankingRecyclerViewAdapter
        rv_brand_ranking_list.layoutManager = LinearLayoutManager(activity)

    }
}