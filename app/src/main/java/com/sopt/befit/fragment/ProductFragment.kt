package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimProductRecyclerViewAdapter
import com.sopt.befit.data.JjimProductData
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment() {
    lateinit var jjimProductRecyclerViewAdapter: JjimProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val productFragmentView: View = inflater!!.inflate(R.layout.fragment_product, container, false)
        return productFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        //내가 선호하는 프로덕션만 출력

        var dataList : ArrayList<JjimProductData> = ArrayList()
        dataList.add(JjimProductData("유니클로", "후리스", "29800원", true))
        dataList.add(JjimProductData("후아유", "집업", "35000원", false))
        dataList.add(JjimProductData("오아이오아이", "핸드폰케이스", "19000원", true))
        dataList.add(JjimProductData("휠라", "신발", "29800원", true))
        dataList.add(JjimProductData("아디다스", "패딩", "35000원", false))
        dataList.add(JjimProductData("나이키", "양말", "19000원", true))

        jjimProductRecyclerViewAdapter = JjimProductRecyclerViewAdapter(activity!!, dataList)
        rv_frag_product_list.adapter = jjimProductRecyclerViewAdapter
        rv_frag_product_list.layoutManager = GridLayoutManager(activity!!, 2)
    }
}