package com.sopt.befit.activity

import android.os.Bundle
import android.support.design.widget.TabLayout


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import com.sopt.befit.adapter.MyFragmentStatePagerAdapter
import com.sopt.befit.data.UserData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_aaaamain.*
import kotlinx.android.synthetic.main.tab_bar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AAAAMainActivity : AppCompatActivity() {


    companion object {
        lateinit var instance: AAAAMainActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aaaamain)

        configureBottomNavigation()

        Log.d("aaaaaa", "onCreate")

        instance = this



    }

    override fun onResume() {
        super.onResume()

        Log.d("aaaaaa", "onResume")
    }

    private fun configureBottomNavigation() {
        vp_bottom_navi_act_frag_pager.adapter = MyFragmentStatePagerAdapter(supportFragmentManager, 5)
        vp_bottom_navi_act_frag_pager.offscreenPageLimit = 5
        tl_bottom_navi_act_bottom_menu.setupWithViewPager(vp_bottom_navi_act_frag_pager)
        //TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.tab_bar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_home) as ImageView
        tl_bottom_navi_act_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_search) as ImageView

        tl_bottom_navi_act_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_ranking) as ImageView
        tl_bottom_navi_act_bottom_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_jjim) as ImageView

        tl_bottom_navi_act_bottom_menu.getTabAt(4)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_mypage) as ImageView

        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.select()
    }


    fun tabvisible() {
        if (tl_bottom_navi_act_bottom_menu.visibility == View.GONE) {
            Log.d("aaaa", "toVisible")
            tl_bottom_navi_act_bottom_menu.visibility = View.VISIBLE
        } else if (tl_bottom_navi_act_bottom_menu.visibility == View.VISIBLE) {
            Log.d("aaaa", "toInvisible")
            tl_bottom_navi_act_bottom_menu.visibility = View.GONE
        }
    }
}
