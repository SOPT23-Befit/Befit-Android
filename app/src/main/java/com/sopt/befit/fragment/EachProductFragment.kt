package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sopt.befit.R
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EachProductFragment() : Fragment() {

    lateinit var searchProductListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    var token: String = ""
    var b_idx: Int = 0
    var search: String? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

        //Toast.makeText(activity!!, "eachproduct:" + savedInstanceState!!.getString("searchImage"), Toast.LENGTH_SHORT).show();

        arguments?.let {
            search = it.getString("search")
        }

        setViewClickListener()

        setRecyclerView()

        getSearchNewProductListResponse()
    }

    private fun setViewClickListener() {

        tv_search_product_new.setOnClickListener {
            if (tv_search_product_popular.isChecked) {
                tv_search_product_popular.setChecked(false)
                tv_search_product_new.setChecked(true)
                dataList.clear()
                getSearchNewProductListResponse()
            }
        }
        tv_search_product_popular.setOnClickListener {
            //리사이클러 뷰 재통신
            if (tv_search_product_new.isChecked) {
                tv_search_product_new.setChecked(false)
                tv_search_product_popular.setChecked(true)
                dataList.clear()
                getSearchPopularProductListResponse()
            }
        }
    }

    private fun setRecyclerView() {
        searchProductListRecyclerViewAdapter = ProductListRecyclerViewAdapter(activity!!, dataList)
        rv_frag_s_product_list.adapter = searchProductListRecyclerViewAdapter
        rv_frag_s_product_list.layoutManager = GridLayoutManager(activity!!, 2)
    }

    private fun getSearchNewProductListResponse() {
        val getSearchNewProductListResponse = networkService.getSearchNewProductResponse(token, search!!)
        getSearchNewProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("search new product fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = searchProductListRecyclerViewAdapter.itemCount
                            searchProductListRecyclerViewAdapter.dataList.addAll(temp)
                            searchProductListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun getSearchPopularProductListResponse() {
        val getSearchPopularProductListResponse = networkService.getSearchPopularProductResponse(token, search!!)
        getSearchPopularProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = searchProductListRecyclerViewAdapter.itemCount
                            searchProductListRecyclerViewAdapter.dataList.addAll(temp)
                            searchProductListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }
}