package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.gson.JsonParser
import com.rd.PageIndicatorView


import com.sopt.befit.R
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.adapter.CompareSizeAdapter
import com.sopt.befit.get.ClosetDetail
import kotlinx.android.synthetic.main.dl_compare_size.*
import org.jetbrains.anko.support.v4.startActivity
import com.sopt.befit.R.id.pageIndicatorView
import android.support.v4.view.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.adapter.Utilities
import kotlinx.android.synthetic.main.fragment_compare_size.*


class CompareSizeDialog() : DialogFragment() {

    lateinit var closetList: ArrayList<ClosetDetail>
    var product_idx = -1
    lateinit var measure: String
    lateinit var closetSize: ArrayList<String>
    fun checkBtnClick() {
        btn_dl_compare_size_check.setOnClickListener {
            // startActivity<ProductContentViewActivity>()
            dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dl_compare_size, container, false)

        Glide.with(view.context)
                .load("https://s3.ap-northeast-2.amazonaws.com/befit-server/33.+jeansslackspants.png")
                .into(view!!.findViewById(R.id.iv_fragment_compare_size_my_size))
        Utilities.setGlobalFont(view, activity!!);
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        closetList = arguments!!.getSerializable("ClosetList") as ArrayList<ClosetDetail>

        //상품 정보 넘겨주기
//        product_idx = arguments!!.getInt("product_idx")
//        measure = arguments!!.getString("measure")

        var data = ProductContentViewActivity.instance.getCurrentProductData()
        var jsonString = data.measure.toString()
        var parser = JsonParser()
        var json = parser.parse(jsonString).asJsonObject

        closetSize = ArrayList<String>()

        for ((index, measure) in json.entrySet().withIndex()) {
            closetSize.add(measure.key)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureBottomNavigation()
        checkBtnClick()


    }

    private fun configureBottomNavigation() {
        // var count = 2
        vp_compare_size_view_pager.adapter = CompareSizeAdapter(childFragmentManager, closetSize.size, closetList)
        vp_compare_size_view_pager.offscreenPageLimit = closetSize.size
        pageIndicatorView.count = closetSize.size
        pageIndicatorView.selection = 1

        vp_compare_size_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView.selection = position
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })

        //      pageIndicatorView.setCount(closetSize.size) // specify total count of indicators
        //     pageIndicatorView.setSelection(1)       //vp_bottom_navi_act_frag_pager.offscreenPageLimit = 3
        // ViewPager와 Tablayout을 엮어줍니다!
//        tl_bottom_navi_act_bottom_menu.setupWithViewPager(vp_compare_size_view_pager)
//        //TabLayout에 붙일 layout을 찾아준 다음
//        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
//        //탭 하나하나 TabLayout에 연결시켜줍니다.
//        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number1) as RelativeLayout
//        tl_bottom_navi_act_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number2) as RelativeLayout
//        tl_bottom_navi_act_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number3) as RelativeLayout
    }


}