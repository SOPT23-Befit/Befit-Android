package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.adapter.MySizeLookupRecyclerViewAdapter
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.get.GetClosetListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_my_size_lookup.*
import kotlinx.android.synthetic.main.rv_item_my_size_lookup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySizeLookupActivity : AppCompatActivity() {

    companion object {
        lateinit var instance : MySizeLookupActivity
    }

    val dataList: ArrayList<ClosetDetail> by lazy {
        ArrayList<ClosetDetail>()
    }

    var token: String = ""
    var c_idx: Int = 4

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var mySizeLookupRecyclerViewAdapter: MySizeLookupRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_lookup)

        instance = this

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6NSwiZXhwIjoxNTQ4OTg0MjMyfQ._IqFlm-FClS2Ur5MH9xeyt-SpURmqlbj47-vyUHrClI"

        setView()

        setRecyclerView()

        getClosetListResponse()
    }

    private fun setView(){
        tv_my_size_add_title.text = getCategoryString()
    }

    private fun setRecyclerView() {
        mySizeLookupRecyclerViewAdapter = MySizeLookupRecyclerViewAdapter(this, dataList)
        rv_my_size_lookup.adapter = mySizeLookupRecyclerViewAdapter
        rv_my_size_lookup.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getClosetListResponse() {
        val getClosetListResponse = networkService.getClosetListResponse(token, c_idx)
        getClosetListResponse.enqueue(object : Callback<GetClosetListResponse> {
            override fun onFailure(call: Call<GetClosetListResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetClosetListResponse>, response: Response<GetClosetListResponse>) {
                if (response.isSuccessful) {
                    var temp: ArrayList<ClosetDetail> = ArrayList<ClosetDetail>()
                    if (response.body()?.data != null) {
                        temp=response!!.body()!!.data
                        if (temp.size > 0) {

                        }
                    }
                    temp.add(ClosetDetail(0,"","","","","",0,""))
                    mySizeLookupRecyclerViewAdapter.dataList.addAll(temp)
                    mySizeLookupRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun getCategoryString() : String {
        when(c_idx){
            0 -> return "Outer"
            1 -> return "Jacket"
            2 -> return "Coat"
            3 -> return "Shirts"
            4 -> return "Knits"
            5 -> return "Hoody"
            6 -> return "Sweat Shirts"
            7 -> return "T-Shirts"
            8 -> return "Onepiece"
            9 -> return "Jeans"
            10 -> return "Pants"
            11 -> return "Slacks"
            12 -> return "Short-Pants"
            13 -> return "Skirts"
        }
        return ""
    }
}
