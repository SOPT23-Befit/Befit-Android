package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.R
import com.sopt.befit.adapter.JjimPBFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_jjim.*

class JjimFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val jjimFragmentView: View = inflater!!.inflate(R.layout.fragment_jjim, container, false)
        Utilities.setGlobalFont(jjimFragmentView, activity!!);

        return jjimFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        vp_product_brand_navi_act_frag_pager.adapter = JjimPBFragmentStatePagerAdapter(childFragmentManager, 2)
        vp_product_brand_navi_act_frag_pager.offscreenPageLimit = 2

        tl_product_brand_navi_act_menu.setupWithViewPager(vp_product_brand_navi_act_frag_pager)

        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.product_brand_tab_bar, null, false)

        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_product_brand_navi_act_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_navi_product_tap) as ImageView
        tl_product_brand_navi_act_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_navi_brand_tap) as ImageView
    }
}