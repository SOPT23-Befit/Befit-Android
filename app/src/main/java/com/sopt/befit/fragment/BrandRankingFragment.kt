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
import com.sopt.befit.get.GetBrandRankingResponse
import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.InitialBrand
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_ranking.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Callback

class BrandRankingFragment :Fragment(){
    lateinit var brandRankingRecyclerViewAdapter: BrandRankingRecyclerViewAdapter
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    val dataList : ArrayList<BrandRankingData> by lazy {
        ArrayList<BrandRankingData>()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandRankingFragmentView: View = inflater!!.inflate(R.layout.fragment_ranking, container, false)
        return brandRankingFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        setRecyclerView()

        getBrandRankingResponse()
    }

    private fun setRecyclerView() {

        //내가 좋아하는
        brandRankingRecyclerViewAdapter = BrandRankingRecyclerViewAdapter(activity!!, dataList)
        rv_brand_ranking_list.adapter = brandRankingRecyclerViewAdapter
        rv_brand_ranking_list.layoutManager = LinearLayoutManager(activity)

    }


   private fun getBrandRankingResponse(){

       //networkService = ApplicationController.instance!!.networkService
        val getBrandRankingResponse = networkService.getBrandRankingResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzUzNTUzfQ.g-nHgDAsxs6R21CqssW4Q-X8JpbL5H9tNwYYGHKtgXs")
        getBrandRankingResponse.enqueue(object : Callback<GetBrandRankingResponse> {
            override fun onFailure(call: retrofit2.Call<GetBrandRankingResponse>, t: Throwable) {
                Log.e("brandRanking", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetBrandRankingResponse>, response: retrofit2.Response<GetBrandRankingResponse>) {


                        if(response.isSuccessful){


                                Log.d("zzzzzz",response.body()!!.toString())
                            when (response.body()!!.status) {
                                200 -> {

                                    Log.v("success", response.message().toString())
                                    val temp : ArrayList<BrandRankingData> = response.body()!!.data
                                    Log.v("dddd",temp.size.toString())
                                        val position = brandRankingRecyclerViewAdapter.itemCount
                                        brandRankingRecyclerViewAdapter.dataList.addAll(temp)


                                    Log.v("dddd",dataList.size.toString())

                                        brandRankingRecyclerViewAdapter.notifyItemInserted(position)

                                }



                                401 -> {
                                    Log.v("fail",response.message())
                                    Log.v("fail",response.errorBody().toString())
                                    toast("인증 실패")
                                }

                               500 -> {

                                    Log.v("409 error",response.message())
                                    Log.v("server error",response.errorBody().toString())
                                    toast("서버 내부 에러")
                                }
                                600->{
                                    Log.v("600 error",response.message())
                                    Log.v("database error",response.errorBody().toString())
                                    toast("데이터베이스 에러")
                                }
                                else -> {
                                    toast("Error")
                                }
                            }
                        } else {
                            Log.d("status fail",response.code().toString())
                        }

                        // BrandsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(applicationContext, temp)
                        //rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(applicationContext)
                        //rv_add_my_size_brand_list.adapter = BrandsRecyclerViewAdapter

                    }


        })
    }
}