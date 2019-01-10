package com.sopt.befit.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import com.sopt.befit.adapter.MyFragmentStatePagerAdapter
import com.sopt.befit.data.BrandRecommendData
import com.sopt.befit.data.UserData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.fragment.MainBannerFragment2
import com.sopt.befit.fragment.MainBrandFragment1
import com.sopt.befit.fragment.MainBrandFragment2
import com.sopt.befit.fragment.MainBrandFragment3
import com.sopt.befit.get.GetBrandRecommendResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_aaaamain.*
import kotlinx.android.synthetic.main.tab_bar.*
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Color.parseColor
import android.R.attr.fragment
import android.support.design.widget.TabLayout.OnTabSelectedListener



class AAAAMainActivity :BaseActivity() {

    var time : Long = 0

    companion object {
        lateinit var instance: AAAAMainActivity
    }

    lateinit var networkService: NetworkService
    lateinit var temp: ArrayList<BrandRecommendData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aaaamain)

        configureBottomNavigation()

        Log.d("aaaaaa", "onCreate")

        instance = this



        ibtn_AAAA_main_act_mypage.setOnClickListener(){
            toast("mypage clisked")
        }
        ibtn_AAAA_main_act_search.setOnClickListener(){
            toast("search clisked")
        }
        ibtn_AAAA_main_act_jjim.setOnClickListener(){
            toast("jjim clicked")
        }
        ibtn_AAAA_main_act_home.setOnClickListener(){
            toast("homeclicked")
        }

        ibtn_AAAA_main_act_ranking.setOnClickListener(){
            toast("ranking clicked")
        }

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

    private fun addFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.vp_aaa_main_home_fragment, fragment)
        transaction.commit()
    }




    override fun onBackPressed() {
        if (System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis()
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish()
        }
    }

}
