package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.R
import com.sopt.befit.adapter.SearchPBFragmentStatePagerAdapter
import com.sopt.befit.adapter.SearchProductImageRecyclerViewAdapter
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.SearchProductData
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(), TextView.OnEditorActionListener{

    lateinit var searchProductImageRecyclerViewAdapter: SearchProductImageRecyclerViewAdapter

    val dataList: ArrayList<SearchProductData> by lazy {
        ArrayList<SearchProductData>()
    }

    var token: String = ""
    var b_idx: Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val searchFragmentView: View = inflater!!.inflate(R.layout.fragment_search, container, false)
        Utilities.setGlobalFont(searchFragmentView, activity!!);

        return searchFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

        getSearchInitalListResponse()

        setRecyclerView()

        setViewClickListener()
    }

    private fun setViewClickListener() {

        tv_search_product_cancle.setOnClickListener {
            //취소버튼 눌렀을 때
            refresh_search_img.setVisibility(View.VISIBLE)
            tv_search_product_cancle.setVisibility(View.GONE)
            layout_search_product_bottom.setVisibility(View.GONE)
            et_search_product_write.setText("")
        }

        et_search_product_write.setOnEditorActionListener(this)

        et_search_product_write.setOnClickListener() {
            refresh_search_img.setVisibility(View.GONE)
            tv_search_product_cancle.setVisibility(View.VISIBLE)
        }

        refresh_search_img.setOnRefreshListener {
            getSearchInitalListResponse()
            refresh_search_img.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        searchProductImageRecyclerViewAdapter = SearchProductImageRecyclerViewAdapter(activity!!, dataList)
        rv_search_product_top.adapter = searchProductImageRecyclerViewAdapter
        rv_search_product_top.layoutManager = LinearLayoutManager(activity!!)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (et_search_product_write.text.toString() != "") {
                layout_search_product_bottom.setVisibility(View.VISIBLE)
                configureBottomNavigation()
            } else {
                Toast.makeText(activity!!, "검색란이 비워져 있습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        return false
    }

    private fun getSearchInitalListResponse() {
        val getSearchInitalListResponse = networkService.getSearchInitalListResponse(token)
        getSearchInitalListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("search product list fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    dataList.clear()
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            for (i in 0..2) {
                                dataList.add(SearchProductData(temp[0 + 8 * i].image_url, temp[0 + 8 * i].idx
                                        , temp[1 + 8 * i].image_url, temp[1 + 8 * i].idx
                                        , temp[2 + 8 * i].image_url, temp[2 + 8 * i].idx
                                        , temp[3 + 8 * i].image_url, temp[3 + 8 * i].idx
                                        , temp[4 + 8 * i].image_url, temp[4 + 8 * i].idx
                                        , temp[5 + 8 * i].image_url, temp[5 + 8 * i].idx
                                        , temp[6 + 8 * i].image_url, temp[6 + 8 * i].idx
                                        , temp[7 + 8 * i].image_url, temp[7 + 8 * i].idx))

                            }
                        }

                    }
                    searchProductImageRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        })
    }

    private fun configureBottomNavigation() {
        val b = Bundle()

        b.putString("search", et_search_product_write.text.toString())

        vp_search_product_brand_navi_act_frag_pager.adapter = SearchPBFragmentStatePagerAdapter(childFragmentManager, 2, b)
        vp_search_product_brand_navi_act_frag_pager.offscreenPageLimit = 2

        tl_search_product_brand_navi_act_top_menu.setupWithViewPager(vp_search_product_brand_navi_act_frag_pager)

        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.product_brand_tab_bar, null, false)

        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_search_product_brand_navi_act_top_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_navi_product_tap) as ImageView
        tl_search_product_brand_navi_act_top_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_navi_brand_tap) as ImageView

    }
}