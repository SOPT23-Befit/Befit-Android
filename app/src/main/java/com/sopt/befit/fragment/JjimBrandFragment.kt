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
import com.sopt.befit.adapter.JjimBrandRecyclerViewAdapter
import com.sopt.befit.data.BrandData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetBrandListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_brand.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JjimBrandFragment : Fragment() {

    lateinit var jjimBrandRecyclerViewAdapter: JjimBrandRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var token: String = ""
    var b_idx: Int = 0

    val dataList: ArrayList<BrandData> by lazy {
        ArrayList<BrandData>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_brand, container, false)
        Utilities.setGlobalFont(brandFragmentView, activity!!);

        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = SharedPreferenceController.getAuthorization(activity!!)

        setRecyclerView()

        setViewClickListener()
    }

    override fun onResume() {
        super.onResume()
        getJjimBrandListResponse()
    }

    private fun setViewClickListener() {
        refresh_brand_main.setOnRefreshListener {
            getJjimBrandListResponse()
            refresh_brand_main.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        jjimBrandRecyclerViewAdapter = JjimBrandRecyclerViewAdapter(activity!!, dataList)
        rv_frag_brand_list.adapter = jjimBrandRecyclerViewAdapter
        rv_frag_brand_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun getJjimBrandListResponse() {
        val getJjimBrandListResponse = networkService.getJjimBrandListResponse(token)
        getJjimBrandListResponse.enqueue(object : Callback<GetBrandListResponse> {
            override fun onFailure(call: Call<GetBrandListResponse>, t: Throwable) {
                Log.e("jjim brand list fail", t.toString())
            }

            override fun onResponse(call: Call<GetBrandListResponse>, response: Response<GetBrandListResponse>) {
                if (response.isSuccessful) {
                    dataList.clear()
                    if (response.body()?.data != null) {
                        val temp: ArrayList<BrandData> = response.body()!!.data
                        if (temp.size > 0) {
                            tv_fragment_jjim_brand_count.text = "찜한 브랜드 " + temp.size.toString()
                            jjimBrandRecyclerViewAdapter.dataList.addAll(temp)
                        }
                    } else {
                        tv_fragment_jjim_brand_count.text = "찜한 상품 " + 0
                    }

                    jjimBrandRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}