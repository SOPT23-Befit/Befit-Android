package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.data.ProductData
import kotlinx.android.synthetic.main.fragment_s_product.*

class SProductFragment : Fragment() {

    lateinit var productListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_s_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        //내가 선호하는 프로덕션만 출력

        var dataList : ArrayList<ProductData> = ArrayList()

        /*

        dataList.add(ProductData("유니클로", "후리스", "29800원", true))
        dataList.add(ProductData("후아유", "집업", "35000원", false))
        dataList.add(ProductData("오아이오아이", "핸드폰케이스", "19000원", true))
        dataList.add(ProductData("휠라", "신발", "29800원", true))
        dataList.add(ProductData("아디다스", "패딩", "35000원", false))
        dataList.add(ProductData("나이키", "양말", "19000원", true))

         */

        productListRecyclerViewAdapter = ProductListRecyclerViewAdapter(activity!!, dataList)
        rv_frag_s_product_list.adapter = productListRecyclerViewAdapter
        rv_frag_s_product_list.layoutManager = GridLayoutManager(activity!!, 2)
    }
}