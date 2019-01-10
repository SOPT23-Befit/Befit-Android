package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.R
import com.sopt.befit.adapter.EachBrandRecyclerViewAdapter
import com.sopt.befit.data.BrandData
import com.sopt.befit.get.GetBrandListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_search_brand.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EachBrandFragment : Fragment() {

    lateinit var eachBrandRecyclerViewAdapter: EachBrandRecyclerViewAdapter

    var token: String = ""
    var b_idx: Int = 0
    var search: String? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    val dataList: ArrayList<BrandData> by lazy {
        ArrayList<BrandData>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val eachBrandFragmentView: View = inflater!!.inflate(R.layout.fragment_search_brand, container, false)
        Utilities.setGlobalFont(eachBrandFragmentView, activity!!);
        return eachBrandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

        arguments?.let {
            search = it.getString("search")
        }

        setRecyclerView()

        getSearchBrandResponse()
    }

    private fun setRecyclerView() {
        eachBrandRecyclerViewAdapter = EachBrandRecyclerViewAdapter(activity!!, dataList, token)
        rv_frag_s_brand_list.adapter = eachBrandRecyclerViewAdapter
        rv_frag_s_brand_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun getSearchBrandResponse() {
        val getSearchBrandResponse = networkService.getSearchBrandResponse(token, search!!)
        getSearchBrandResponse.enqueue(object : Callback<GetBrandListResponse> {
            override fun onFailure(call: Call<GetBrandListResponse>, t: Throwable) {
                Log.e("search brand list fail", t.toString())
            }

            override fun onResponse(call: Call<GetBrandListResponse>, response: Response<GetBrandListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<BrandData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = eachBrandRecyclerViewAdapter.itemCount
                            eachBrandRecyclerViewAdapter.dataList.addAll(temp)
                            eachBrandRecyclerViewAdapter.notifyItemInserted(position)
                        }
                    }
                }
            }
        })
    }
}