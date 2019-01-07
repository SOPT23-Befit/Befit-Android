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
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_jjim.*
import kotlinx.android.synthetic.main.fragment_product.*
import org.jetbrains.anko.db.INTEGER
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JjimProductFragment : Fragment() {

    val JJIM_PRODUCT_FRAGMENT_REQUEST_CODE = 2210

    lateinit var productListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val productFragmentView: View = inflater!!.inflate(R.layout.fragment_product, container, false)
        return productFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()

        getJjimProductListResponse()
    }

    override fun onStart() {
        super.onStart()
        //getJjimProductListResponse()
    }

    private fun setRecyclerView() {
        productListRecyclerViewAdapter = ProductListRecyclerViewAdapter(activity!!, dataList)
        rv_frag_product_list.adapter = productListRecyclerViewAdapter
        rv_frag_product_list.layoutManager = GridLayoutManager(activity!!, 2)
    }

    private fun getJjimProductListResponse() {
        val getJjimProductListResponse = networkService.getJjimProductListResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80")
        getJjimProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("jjim product list fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if(response.body()?.data!=null){
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            tv_fragment_jjim_product_count.text= "찜한 상품 " + temp.size.toString()
                            val position = productListRecyclerViewAdapter.itemCount
                            productListRecyclerViewAdapter.dataList.addAll(temp)
                            productListRecyclerViewAdapter.notifyItemInserted(position)
                        }
                    }
                }
            }
        })
    }
}