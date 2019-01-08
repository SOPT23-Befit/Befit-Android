package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sopt.befit.Adapter.CompareSizeAdapter

import com.sopt.befit.R
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.get.ClosetDetail
import kotlinx.android.synthetic.main.dl_compare_size.*
import org.jetbrains.anko.support.v4.startActivity

class CompareSizeDialog() : DialogFragment() {

    lateinit var closetList : ArrayList<ClosetDetail>
    fun checkBtnClick(){
        btn_dl_compare_size_check.setOnClickListener{
           // startActivity<ProductContentViewActivity>()
            dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dl_compare_size, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        closetList = arguments!!.getSerializable("ClosetList") as ArrayList<ClosetDetail>
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureBottomNavigation()
        checkBtnClick()
    }

    private fun configureBottomNavigation() {
        var count = 2
        vp_compare_size_view_pager.adapter = CompareSizeAdapter(childFragmentManager,3,closetList)
        //vp_bottom_navi_act_frag_pager.offscreenPageLimit = 3
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