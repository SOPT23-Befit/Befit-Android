package com.sopt.befit.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sopt.befit.R
import com.sopt.befit.activity.AAAAMainActivity
import com.sopt.befit.adapter.*
import com.sopt.befit.data.JjimProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_group.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(){
    lateinit var HomefragmentAdapter: JjimProductRecyclerViewAdapter

    val header :MutableList<String> = ArrayList()

    val body : MutableList<MutableList<String>> = ArrayList()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        setcategory()
        setRecyclerView()
        configureBannerNavigation()
        configureHomeBrandNavigation()

        ibtn_menu_open.setOnClickListener(){
            ibtn_menu_open.visibility=View.GONE
            tv_aaa_main_befit.visibility=View.GONE
            category_menu.visibility=View.VISIBLE
            lo_aaa_main_home_brand_rec.visibility=View.INVISIBLE
            lo_aaa_main_banner.visibility=View.INVISIBLE
            lo_aaa_main_my_racommend_item.visibility=View.GONE
            AAAAMainActivity.instance.tabvisible()
        }

        ibtn_menu_close.setOnClickListener(){
            ibtn_menu_open.visibility=View.VISIBLE
            category_menu.visibility=View.GONE
            tv_aaa_main_befit.visibility=View.VISIBLE
            lo_aaa_main_home_brand_rec.visibility=View.VISIBLE
            lo_aaa_main_banner.visibility=View.VISIBLE
            lo_aaa_main_my_racommend_item.visibility=View.VISIBLE
            AAAAMainActivity.instance.tabvisible()
        }

        if(body.isNotEmpty()){

            //iv_listview_arrow.visibility=View.INVISIBLE
        }

      //ㅂㅐ너 클릭시
        //브랜드상품 클릭시
    }


   fun setcategory(){

       tv_aaa_main_best_cate.setOnClickListener(){
           //
       }

       tv_aaa_main_new_cate.setOnClickListener(){
           //
       }



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


    header.add("Women")
    header.add("Men")




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

        var dataList: ArrayList<JjimProductData> = ArrayList()
        //dataList.add(JjimProductData("","","",false))

        HomefragmentAdapter = JjimProductRecyclerViewAdapter(activity!!, dataList)
        rv_my_rec_item_list.adapter=HomefragmentAdapter
        rv_my_rec_item_list.layoutManager= GridLayoutManager(activity,2)

    }


    private fun configureHomeBrandNavigation()
    {
        vp_aaa_main_home_fragment.adapter = HomeFragmentBrandPagerAdapter(childFragmentManager,3)
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
    private fun configureBannerNavigation()
    {
        vp_aaa_main_banner.adapter = HomeFragmentBannerPagerAdapter(childFragmentManager,3)
        vp_aaa_main_banner.offscreenPageLimit = 3

    }

}

