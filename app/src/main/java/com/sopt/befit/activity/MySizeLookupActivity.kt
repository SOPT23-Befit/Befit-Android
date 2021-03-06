package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.adapter.MySizeLookupRecyclerViewAdapter
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.get.GetClosetListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_my_size_lookup.*
import kotlinx.android.synthetic.main.rv_item_my_size_lookup.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySizeLookupActivity : BaseActivity() {

    companion object {
        lateinit var instance: MySizeLookupActivity
    }

    val dataList: ArrayList<ClosetDetail> by lazy {
        ArrayList<ClosetDetail>()
    }

    var token: String = ""
    var category_idx: Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var mySizeLookupRecyclerViewAdapter: MySizeLookupRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_lookup)

        instance = this

        token = SharedPreferenceController.getAuthorization(this)
        category_idx=intent.getIntExtra("category_idx", 0)

        setView()
        setOnButton()
        setRecyclerView()

    }

    override fun onResume() {
        super.onResume()

        getClosetListResponse()
    }

    private fun setView() {
        tv_my_size_add_title.text = getCategoryString()
    }

    private fun setOnButton() {
        tv_my_size_add_edit.setOnClickListener {
            when(mySizeLookupRecyclerViewAdapter.flag) {
                0 -> {
                    tv_my_size_add_edit.text = "완료"
                    mySizeLookupRecyclerViewAdapter.flag = 1
                }
                1 -> {
                    tv_my_size_add_edit.text = "편집"
                    mySizeLookupRecyclerViewAdapter.flag = 0
                }
            }
            mySizeLookupRecyclerViewAdapter.notifyDataSetChanged()
        }

        img_my_size_lookup_back.setOnClickListener{
            finish()
        }
    }

    private fun setRecyclerView() {
        mySizeLookupRecyclerViewAdapter = MySizeLookupRecyclerViewAdapter(this, dataList, category_idx)
        rv_my_size_lookup.adapter = mySizeLookupRecyclerViewAdapter
        rv_my_size_lookup.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getClosetListResponse() {
        val getClosetListResponse = networkService.getClosetListResponse(token, category_idx)
        getClosetListResponse.enqueue(object : Callback<GetClosetListResponse> {
            override fun onFailure(call: Call<GetClosetListResponse>, t: Throwable) {
                Log.e("my size look up fail", t.toString())
            }

            override fun onResponse(call: Call<GetClosetListResponse>, response: Response<GetClosetListResponse>) {
                if (response.isSuccessful) {
                    var temp: ArrayList<ClosetDetail> = ArrayList<ClosetDetail>()
                    dataList.clear()
                    if (response.body()?.data != null) {
                        temp = response!!.body()!!.data
                    }
                    temp.add(ClosetDetail(0, "", "", "", "", "", 0, ""))
                    mySizeLookupRecyclerViewAdapter.dataList.addAll(temp)
                    mySizeLookupRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun getCategoryString(): String {
        when (category_idx) {
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
