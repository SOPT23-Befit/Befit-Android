package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimBrandRecyclerViewAdapter
import com.sopt.befit.data.JjimBrandData
import com.sopt.befit.get.GetJjimBrandListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_brand.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JjimBrandFragment : Fragment() {

    lateinit var jjimBrandRecyclerViewAdapter: JjimBrandRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    val dataList: ArrayList<JjimBrandData> by lazy {
        ArrayList<JjimBrandData>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_brand, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()

        getJjimBrandListResponse()
    }


    override fun onStart() {
        super.onStart()
        //getJjimBrandListResponse()
    }

    private fun setRecyclerView() {
        jjimBrandRecyclerViewAdapter = JjimBrandRecyclerViewAdapter(activity!!, dataList)
        rv_frag_brand_list.adapter = jjimBrandRecyclerViewAdapter
        rv_frag_brand_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun getJjimBrandListResponse() {
        val getJjimBrandListResponse = networkService.getJjimBrandListResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80")
        getJjimBrandListResponse.enqueue(object : Callback<GetJjimBrandListResponse> {
            override fun onFailure(call: Call<GetJjimBrandListResponse>, t: Throwable) {
                Log.e("jjim brand list fail", t.toString())
            }

            override fun onResponse(call: Call<GetJjimBrandListResponse>, response: Response<GetJjimBrandListResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!=null){
                        val temp: ArrayList<JjimBrandData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = jjimBrandRecyclerViewAdapter.itemCount
                            jjimBrandRecyclerViewAdapter.dataList.addAll(temp)
                            jjimBrandRecyclerViewAdapter.notifyItemInserted(position)
                        }
                    }
                }
            }
        })
    }
}