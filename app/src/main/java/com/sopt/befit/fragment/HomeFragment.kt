package com.sopt.befit.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sopt.befit.R
import com.sopt.befit.activity.AAAAMainActivity
import com.sopt.befit.activity.CategoryActivity
import com.sopt.befit.adapter.*
import com.sopt.befit.data.*

import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetBrandListResponse
import com.sopt.befit.get.GetBrandRecommendResponse
import com.sopt.befit.get.GetMyRecommendProduct
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_brand.*


import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_main_brand_1.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.layout_group.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var myRecommendProductRecyclerViewAdapter: MyRecommendProductRecyclerViewAdapter

    val header: MutableList<String> = ArrayList()

    val body: MutableList<MutableList<String>> = ArrayList()

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    var flag = 0

    var flag_men = 0

    lateinit var networkService: NetworkService


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val homeFragmentView: View = inflater!!.inflate(R.layout.fragment_home, container, false)
        Utilities.setGlobalFont(homeFragmentView, activity!!);
        return homeFragmentView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //setcategory()
        setRecyclerView()

        configureBannerNavigation()

        //통신
        getBrandRecommendResponse()

        getMyReccomendProduct()



        tv_main_category_man_outer.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 0)
        }
        tv_main_category_man_jacket.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 1)
        }
        tv_main_category_man_coat.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 2)
        }
        tv_main_category_man_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 3)
        }
        tv_main_category_man_knits.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 4)
        }
        tv_main_category_man_hoody.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 5)
        }
        tv_main_category_man_seat_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 6)
        }
        tv_main_category_man_t_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 7)
        }
        tv_main_category_man_jeans.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 9)
        }
        tv_main_category_man_pants.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 10)
        }
        tv_main_category_man_slacks.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 11)
        }
        tv_main_category_man_short_pants.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 12)
        }

        tv_main_category_woman_outer.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 0)
        }
        tv_main_category_woman_jacket.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 1)
        }
        tv_main_category_woman_coat.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 2)
        }
        tv_main_category_woman_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 3)
        }
        tv_main_category_woman_knits.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 4)
        }
        tv_main_category_woman_hoody.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 5)
        }

        tv_main_category_woman_sweat_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 6)
        }
        tv_main_category_woman_t_shirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 7)
        }
        tv_main_category_woman_onepiece.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 8)
        }
        tv_main_category_woman_Jean.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 9)
        }
        tv_main_category_woman_pants.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 10)
        }
        tv_main_category_woman_slacks.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 11)
        }
        tv_main_category_woman_short_pants.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 12)
        }
        tv_main_category_woman_skirts.setOnClickListener(){
            startActivity<CategoryActivity>("category_idx" to 14)
        }








        ibtn_menu_open.setOnClickListener(){
            ibtn_menu_open.visibility=View.INVISIBLE
            tv_aaa_main_befit.visibility=View.INVISIBLE
            category_menu.visibility=View.VISIBLE

            //lo_aaa_main_home_brand_rec.visibility = View.GONE
            //lo_aaa_main_banner.visibility=View.GONE
            //lo_aaa_main_my_racommend_item.visibility=View.GONE
            iv_home_fragment_background.visibility = View.VISIBLE
            AAAAMainActivity.instance.tabvisible()
        }

        ibtn_menu_close.setOnClickListener() {
            ibtn_menu_open.visibility = View.VISIBLE
            tv_aaa_main_befit.visibility = View.VISIBLE
            category_menu.visibility = View.GONE
            //lo_aaa_main_home_brand_rec.visibility = View.VISIBLE
            //lo_aaa_main_banner.visibility=View.VISIBLE
            //lo_aaa_main_my_racommend_item.visibility=View.VISIBLE
            iv_home_fragment_background.visibility = View.GONE

            AAAAMainActivity.instance.tabvisible()
        }

        lo_aaa_main_women_category.setOnClickListener() {
            if (flag == 0) {
                if (flag_men == 1) {
                    flag_men = 0
                    tv_aaa_main_men_cate.setTextColor(Color.parseColor("#000000"))

                    lo_men_category.visibility = View.GONE

                    iv_home_arrowdown_men.setImageResource(R.drawable.arrow_down)
                }
                flag = 1
                tv_aaa_main_women_cate.setTextColor(Color.parseColor("#7a36e4"))

                lo_women_category.visibility = View.VISIBLE
                iv_home_arrowdown_women.setImageResource(R.drawable.arrow_up)


            } else if (flag == 1) {

                flag = 0
                tv_aaa_main_women_cate.setTextColor(Color.parseColor("#000000"))

                lo_women_category.visibility = View.GONE

                iv_home_arrowdown_women.setImageResource(R.drawable.arrow_down)

            }
        }


        lo_aaa_main_men_category.setOnClickListener() {
            if (flag_men == 0) {
                if (flag == 1) {
                    flag = 0
                    tv_aaa_main_women_cate.setTextColor(Color.parseColor("#000000"))

                    lo_women_category.visibility = View.GONE

                    iv_home_arrowdown_women.setImageResource(R.drawable.arrow_down)
                }

                flag_men = 1
                tv_aaa_main_men_cate.setTextColor(Color.parseColor("#7a36e4"))

                lo_men_category.visibility = View.VISIBLE

                iv_home_arrowdown_men.setImageResource(R.drawable.arrow_up)

            } else if (flag_men == 1) {

                flag_men = 0
                tv_aaa_main_men_cate.setTextColor(Color.parseColor("#000000"))

                lo_men_category.visibility = View.GONE

                iv_home_arrowdown_men.setImageResource(R.drawable.arrow_down)

            }

        }
    }


    fun setcategory() {


        val women: MutableList<String> = ArrayList()

        women.add("Outer")
        women.add("Jacket")
        women.add("Coat")

        women.add("Shirts")
        women.add("Knits")

        women.add("Hoody")

        women.add("Sweat Shirts")

        women.add("T-Shirts")

        women.add("Jeans")

        women.add("Pants")
        women.add("Slacks")

        women.add("Short-Pants")
        women.add("Onepiece")
        women.add("Skirts")

        val men: MutableList<String> = ArrayList()
        men.add("Outer")
        men.add("Jacket")
        men.add("Coat")

        men.add("Shirts")
        men.add("Knits")

        men.add("Hoody")

        men.add("Sweat Shirts")

        men.add("T-Shirts")

        men.add("Jeans")

        men.add("Pants")
        men.add("Slacks")

        men.add("Short-Pants")

        header.add("Women")
        header.add("Men")

        body.add(women)
        body.add(men)


//        nav_list.setAdapter(Expandable(activity!!, header, body))
//
//
//        nav_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
//            tv_listview_title.setTextColor(Color.parseColor("#7a36e4"))
//
//            Log.e("child click", "groupPosition $groupPosition, childPosition $childPosition, id $id")
//
//            var gpos = groupPosition
//            var cpos = childPosition
//
//            if (gpos == 0 && cpos == 0) {
//                startActivity<CategoryActivity>("idx" to 0)
//            }
//
//            if (gpos == 0 && cpos == 1) {
//                startActivity<CategoryActivity>("idx" to 1)
//            }
//
//            if (gpos == 0 && cpos == 2) {
//                startActivity<CategoryActivity>("idx" to 2)
//            }
//
//            if (gpos == 0 && cpos == 3) {
//                startActivity<CategoryActivity>("idx" to 3)
//            }
//
//            if (gpos == 0 && cpos == 4) {
//                startActivity<CategoryActivity>("idx" to 4)
//            }
//
//            if (gpos == 0 && cpos == 5) {
//                startActivity<CategoryActivity>("idx" to 5)
//            }
//            if (gpos == 0 && cpos == 6) {
//                startActivity<CategoryActivity>("idx" to 6)
//            }
//
//            if (gpos == 0 && cpos == 7) {
//                startActivity<CategoryActivity>("idx" to 7)
//            }
//            if (gpos == 0 && cpos == 8) {
//                startActivity<CategoryActivity>("idx" to 9)
//            }
//            if (gpos == 0 && cpos == 9) {
//                startActivity<CategoryActivity>("idx" to 10)
//            }
//            if (gpos == 0 && cpos == 10) {
//                startActivity<CategoryActivity>("idx" to 11)
//            }
//            if (gpos == 0 && cpos == 11) {
//                startActivity<CategoryActivity>("idx" to 12)
//            }
//            if (gpos == 0 && cpos == 12) {
//                startActivity<CategoryActivity>("idx" to 8)
//            }
//
//            if (gpos == 0 && cpos == 13) {
//                startActivity<CategoryActivity>("idx" to 13)
//            }
//
//
//            if (gpos == 1 && cpos == 0) {
//                startActivity<CategoryActivity>("idx" to 0)
//            }
//
//            if (gpos == 1 && cpos == 1) {
//                startActivity<CategoryActivity>("idx" to 1)
//            }
//            if (gpos == 1 && cpos == 2) {
//                startActivity<CategoryActivity>("idx" to 2)
//            }
//
//
//            if (gpos == 1 && cpos == 3) {
//                startActivity<CategoryActivity>("idx" to 3)
//            }
//
//
//            if (gpos == 1 && cpos == 4) {
//                startActivity<CategoryActivity>("idx" to 4)
//            }
//
//            if (gpos == 1 && cpos == 5) {
//                startActivity<CategoryActivity>("idx" to 5)
//            }
//            if (gpos == 1 && cpos == 6) {
//                startActivity<CategoryActivity>("idx" to 6)
//            }
//            if (gpos == 1 && cpos == 7) {
//                startActivity<CategoryActivity>("idx" to 7)
//            }
//
//            if (gpos == 1 && cpos == 8) {
//                startActivity<CategoryActivity>("idx" to 9)
//            }
//            if (gpos == 1 && cpos == 9) {
//                startActivity<CategoryActivity>("idx" to 10)
//            }
//            if (gpos == 1 && cpos == 10) {
//                startActivity<CategoryActivity>("idx" to 11)
//            }
//            if (gpos == 1 && cpos == 11) {
//                startActivity<CategoryActivity>("idx" to 12)
//            }
//            return@setOnChildClickListener false
//
//        }

    }

    private fun setRecyclerView() {
        myRecommendProductRecyclerViewAdapter = MyRecommendProductRecyclerViewAdapter(activity!!, dataList)
        rv_my_rec_item_list.adapter = myRecommendProductRecyclerViewAdapter
        rv_my_rec_item_list.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun configureHomeBrandNavigation(dataList: ArrayList<BrandRecommendData>) {

        vp_aaa_main_home_fragment.adapter = HomeFragmentBrandPagerAdapter(childFragmentManager, 3, dataList)

        vp_aaa_main_home_fragment.offscreenPageLimit = 3
        lo_tab_aaa_main_home_fragment.setupWithViewPager(vp_aaa_main_home_fragment)
//TabLayout에 붙일 layout을 찾아준 다음
        val brandNaviLayout: View = this.layoutInflater.inflate(R.layout.main_brand_tab_bar, null, false)
//탭 하나하나 TabLayout에 연결시켜줍니다.
        lo_tab_aaa_main_home_fragment.getTabAt(0)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_first) as ImageView
        lo_tab_aaa_main_home_fragment.getTabAt(1)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_second) as ImageView

        lo_tab_aaa_main_home_fragment.getTabAt(2)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_third) as ImageView

        lo_tab_aaa_main_home_fragment.getTabAt(0)!!.select()

    }

    private fun configureBannerNavigation() {

//        vp_aaa_main_banner.adapter = HomeFragmentBannerPagerAdapter(childFragmentManager, 3)
//        vp_aaa_main_banner.offscreenPageLimit = 3
//        tabDots.setupWithViewPager(vp_aaa_main_banner)

    }

    private fun getBrandRecommendResponse() {
        Log.d("aaaaaaa", "aaaaaa")
        networkService = ApplicationController.instance!!.networkService
        val token = SharedPreferenceController.getAuthorization(activity!!)
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        val getBrandRecommendResponse = networkService.getBrandRecommendResponse(token)
        getBrandRecommendResponse.enqueue(object : Callback<GetBrandRecommendResponse> {
            override fun onFailure(call: Call<GetBrandRecommendResponse>, t: Throwable) {
                Log.d("board list fail", t.toString())
            }

            override fun onResponse(call: Call<GetBrandRecommendResponse>, response: Response<GetBrandRecommendResponse>) {
                response?.let {
                    Log.d("zzzzzzzz", response.code().toString())
                    if (response.isSuccessful) {
                        Log.d("zzzzzz", response.body()!!.toString())
                        when (response.body()!!.status) {
                            200 -> {
                                Log.v("success", response.message().toString())

                                configureHomeBrandNavigation(response.body()!!.data)

                            }

                            400 -> {
                                Log.v("400 fail", response.message())
                                Log.v("fail", response.errorBody().toString())
                                toast("랜덤 3개 브랜드 별 인기 상품 리스트 조회 실패")
                            }

                            401 -> {
                                Log.v("401 fail", response.message())
                                Log.v("fail", response.errorBody().toString())
                                toast("인증 실패")
                            }

                            500 -> {

                                Log.v("409 error", response.message())
                                Log.v("server error", response.errorBody().toString())
                                toast("서버 내부 에러")
                            }
                            600 -> {
                                Log.v("600 error", response.message())
                                Log.v("database error", response.errorBody().toString())
                                toast("데이터베이스 에러")
                            }
                            else -> {
                                toast("Error")
                            }
                        }
                    } else {
                        Log.d("status fail", response.code().toString())
                    }
                }
            }
        })
    }

    private fun getMyReccomendProduct() {
        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

        val getMyRecommendProduct = networkService.getMyRecommendProduct(token)
        getMyRecommendProduct.enqueue(object : Callback<GetMyRecommendProduct> {
            override fun onFailure(call: Call<GetMyRecommendProduct>, t: Throwable) {
                Log.e(" fail", t.toString())
            }

            override fun onResponse(call: Call<GetMyRecommendProduct>, response: Response<GetMyRecommendProduct>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = myRecommendProductRecyclerViewAdapter.itemCount
                            myRecommendProductRecyclerViewAdapter.dataList.addAll(temp)
                            myRecommendProductRecyclerViewAdapter.notifyItemInserted(position)


                        }
                    }
                }
            }
        })
    }

}

