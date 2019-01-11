package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.sopt.befit.R
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : BaseActivity() {

    lateinit var productListRecyclerViewAdapter: ProductListRecyclerViewAdapter
    lateinit var temp: UserTotalData

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    var gender : String = ""
    var token: String = ""
    var c_idx: Int = 0
    var search: String? = null

    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        token = SharedPreferenceController.getAuthorization(this)
        c_idx = intent.getIntExtra("category_idx", 0)
        gender = intent.getStringExtra("gender")
        Log.d("CategoryActivity",gender)

        setView()

        setRecyclerView()

        setViewClickListener()

        getCategoryNewProductListResponse()

    }

    private fun setView() {
        tv_category_title.text = getCategoryString()
    }

    private fun setViewClickListener() {
        tv_category_new.setOnClickListener {
            if (tv_category_popular.isChecked) {
                tv_category_popular.setChecked(false)
                tv_category_new.setChecked(true)
                tv_category_new.setTypeface(Utilities.boldTypeface);
                tv_category_popular.setTypeface(Utilities.mediumTypeface);
                dataList.clear()
                getCategoryNewProductListResponse()
            }
        }
        tv_category_popular.setOnClickListener {
            if (tv_category_new.isChecked) {
                tv_category_new.setChecked(false)
                tv_category_popular.setChecked(true)
                tv_category_popular.setTypeface(Utilities.boldTypeface);
                tv_category_new.setTypeface(Utilities.mediumTypeface);
                dataList.clear()
                getCategoryPopularProductListResponse()
            }
        }
        img_brand_main_back.setOnClickListener {
            finish()
        }
    }

    private fun setRecyclerView() {
        productListRecyclerViewAdapter = ProductListRecyclerViewAdapter(this, dataList)
        rv_category_product_list.adapter = productListRecyclerViewAdapter
        rv_category_product_list.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getCategoryNewProductListResponse() {
        networkService = ApplicationController.instance.networkService
        val getCategoryNewProductListResponse = networkService.getCategoryNewProductListResponse(token, c_idx, gender)
        getCategoryNewProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("category fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = productListRecyclerViewAdapter.itemCount
                            productListRecyclerViewAdapter.dataList.addAll(temp)
                            productListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun getCategoryPopularProductListResponse() {
        val getCategoryPopularProductListResponse = networkService.getCategoryPopularProductListResponse(token, c_idx, gender)
        getCategoryPopularProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = productListRecyclerViewAdapter.itemCount
                            productListRecyclerViewAdapter.dataList.addAll(temp)
                            productListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun getCategoryString(): String {
        when (c_idx) {
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
