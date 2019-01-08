package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.BrandRankingRecyclerViewAdapter
import com.sopt.befit.data.BrandRankingData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.InitialBrand
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_ranking.*
import retrofit2.Callback

class BrandRankingFragment :Fragment(){
    lateinit var brandRankingRecyclerViewAdapter: BrandRankingRecyclerViewAdapter
    lateinit var networkService: NetworkService
    lateinit var temp : BrandRankingData


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


    /*private fun getBrandRankingResponse(){

        val getBrandRankingResponse = networkService.getBrandRankingResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", initial)
        getBrandRankingResponse.enqueue(object : Callback<GetInitialBrandResponse> {
            override fun onFailure(call: retrofit2.Call<GetInitialBrandResponse>, t: Throwable) {
                Log.e("brandInitial", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetInitialBrandResponse>, response: retrofit2.Response<GetInitialBrandResponse>) {
                if (response.isSuccessful) {
                    val temp: ArrayList<InitialBrand> = response.body()!!.data
                    if (temp.size > 0) {
                        val position = BrandsRecyclerViewAdapter.itemCount
                        BrandsRecyclerViewAdapter.dataList.addAll(temp)
                        BrandsRecyclerViewAdapter.notifyItemInserted(position)


                        // BrandsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(applicationContext, temp)
                        //rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(applicationContext)
                        //rv_add_my_size_brand_list.adapter = BrandsRecyclerViewAdapter

                    }
                }
            }
        })
    }*/
}