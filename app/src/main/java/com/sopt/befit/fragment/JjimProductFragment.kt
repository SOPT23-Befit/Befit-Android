package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.adapter.Utilities
//import com.sopt.befit.adapter.Utilities
import com.sopt.befit.data.ProductData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_jjim.*
import kotlinx.android.synthetic.main.fragment_product.*
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.support.v4.ctx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JjimProductFragment : Fragment() {

    val JJIM_PRODUCT_FRAGMENT_REQUEST_CODE = 2210

    lateinit var productListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    var token: String = ""
    var b_idx: Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val productFragmentView: View = inflater!!.inflate(R.layout.fragment_product, container, false)
        Utilities.setGlobalFont(productFragmentView, activity!!);
        return productFragmentView
    }

    override fun onResume() {
        super.onResume()
        getJjimProductListResponse()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = SharedPreferenceController.getAuthorization(ctx)

        setRecyclerView()

        setViewClickListener()
    }

    private fun setViewClickListener(){
        refresh_product_main.setOnRefreshListener {
            getJjimProductListResponse()
            refresh_product_main.isRefreshing=false
        }
    }

    private fun setRecyclerView() {
        productListRecyclerViewAdapter = ProductListRecyclerViewAdapter(activity!!, dataList)
        rv_frag_product_list.adapter = productListRecyclerViewAdapter
        rv_frag_product_list.layoutManager = GridLayoutManager(activity!!, 2)
    }

    private fun getJjimProductListResponse() {
        val getJjimProductListResponse = networkService.getJjimProductListResponse(token)
        getJjimProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("jjim product list fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    dataList.clear()
                    if(response.body()?.data!=null){
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            tv_fragment_jjim_product_count.text= "찜한 상품 " + temp.size.toString()
                            val position = productListRecyclerViewAdapter.itemCount
                            productListRecyclerViewAdapter.dataList.addAll(temp)
                        }
                    }else{
                        tv_fragment_jjim_product_count.text= "찜한 상품 " + 0
                    }
                    productListRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}