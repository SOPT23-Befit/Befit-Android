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
import com.sopt.befit.adapter.*

import com.sopt.befit.data.BrandRecommendData
import com.sopt.befit.data.LoginData
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetBrandRecommendResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService


import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.layout_group.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(){
    lateinit var homefragmentAdapterList: ProductListRecyclerViewAdapter

    val header :MutableList<String> = ArrayList()

    val body : MutableList<MutableList<String>> = ArrayList()


    lateinit var networkService: NetworkService


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false) }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setcategory()
        setRecyclerView()

        configureBannerNavigation()

        //통신
        getBrandRecommendResponse()



        ibtn_menu_open.setOnClickListener(){
            ibtn_menu_open.visibility=View.INVISIBLE
            tv_aaa_main_befit.visibility=View.INVISIBLE
            category_menu.visibility=View.VISIBLE
            AAAAMainActivity.instance.tabvisible()
        }

        ibtn_menu_close.setOnClickListener(){
            ibtn_menu_open.visibility=View.VISIBLE
            category_menu.visibility=View.INVISIBLE
            AAAAMainActivity.instance.tabvisible()
        }


      //ㅂㅐ너 클릭시
        //브랜드상품 클릭시
    }


   fun setcategory(){




    val new:MutableList<String> = ArrayList()
    val best : MutableList<String> = ArrayList()
    val women : MutableList<String> = ArrayList()

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




    val men : MutableList<String> = ArrayList()
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
    men.add("Onepiece")
    men.add("Skirts")
    men.add("Leggings")

    header.add("New")
    header.add("Best")
    header.add("Women")
    header.add("Men")



    body.add(new)
    body.add(best)
    body.add(women)
    body.add(men)






    nav_list.setAdapter(Expandable(activity!!,header,body))



    nav_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->



        tv_listview_title.setTextColor(Color.parseColor("#7a36e4"))


        Log.e("child click", "groupPosition $groupPosition, childPosition $childPosition, id $id")


        var gpos = groupPosition
        var cpos = childPosition




            toast("성공,$gpos,$cpos")



        return@setOnChildClickListener false

    }
    }
    private fun setRecyclerView(){

        var dataList: ArrayList<ProductData> = ArrayList()
        //dataList.add(ProductData("","","",false))

        homefragmentAdapterList = ProductListRecyclerViewAdapter(activity!!, dataList)
        rv_my_rec_item_list.adapter=homefragmentAdapterList
        rv_my_rec_item_list.layoutManager= GridLayoutManager(activity,2)

    }


    private fun configureHomeBrandNavigation(dataList : ArrayList<BrandRecommendData>)
    {

        vp_aaa_main_home_fragment.adapter = HomeFragmentBrandPagerAdapter(childFragmentManager,3,dataList)

        vp_aaa_main_home_fragment.offscreenPageLimit = 3
        lo_tab_aaa_main_home_fragment.setupWithViewPager(vp_aaa_main_home_fragment)
//TabLayout에 붙일 layout을 찾아준 다음
        val brandNaviLayout : View = this.layoutInflater.inflate(R.layout.main_brand_tab_bar, null, false)
//탭 하나하나 TabLayout에 연결시켜줍니다.
        lo_tab_aaa_main_home_fragment.getTabAt(0)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_first) as ImageView
        lo_tab_aaa_main_home_fragment.getTabAt(1)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_second) as ImageView

        lo_tab_aaa_main_home_fragment.getTabAt(2)!!.customView = brandNaviLayout.findViewById(R.id.iv_home_fragment_third) as ImageView

        lo_tab_aaa_main_home_fragment.getTabAt(0)!!.select()

    }
    private fun configureBannerNavigation() {
        vp_aaa_main_banner.adapter = HomeFragmentBannerPagerAdapter(childFragmentManager, 3)
        vp_aaa_main_banner.offscreenPageLimit = 3

        }

    private fun getBrandRecommendResponse(){
        Log.d("aaaaaaa","aaaaaa")
        networkService = ApplicationController.instance!!.networkService
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        val getBrandRecommendResponse = networkService.getBrandRecommendResponse(token)
        getBrandRecommendResponse.enqueue(object : Callback<GetBrandRecommendResponse> {
            override fun onFailure(call: Call<GetBrandRecommendResponse>, t: Throwable) {
                Log.d("board list fail", t.toString())
            }
            override fun onResponse(call: Call<GetBrandRecommendResponse>, response: Response<GetBrandRecommendResponse>) {
                response?.let {
                    Log.d("zzzzzzzz",response.code().toString())
                    if(response.isSuccessful){
                        Log.d("zzzzzz",response.body()!!.toString())
                        when (response.body()!!.status) {
                            200 -> {
                                Log.v("success", response.message().toString())

                                configureHomeBrandNavigation(response.body()!!.data)
                            }

                            400 -> {
                                Log.v("fail",response.message())
                                Log.v("fail",response.errorBody().toString())
                                toast("랜덤 3개 브랜드 별 인기 상품 리스트 조회 실패")
                            }

                            401 -> {
                                Log.v("fail",response.message())
                                Log.v("fail",response.errorBody().toString())
                                toast("인증 실")
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
                }
            } })
    }



}

