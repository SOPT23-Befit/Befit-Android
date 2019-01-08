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

class AAAAMainActivity : AppCompatActivity() {


    companion object {
        lateinit var instance: AAAAMainActivity
    }

    lateinit var networkService: NetworkService
    lateinit var temp : ArrayList<BrandRecommendData>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aaaamain)

//        getUserDataResponse()
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

    private fun addFragment(fragment : Fragment){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.vp_aaa_main_home_fragment, fragment)
        transaction.commit()
    }



    private fun getUserDataResponse(){
        Log.d("aaaaaaa","aaaaaa")
        networkService = ApplicationController.instance!!.networkService
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        val getBrandRecommendResponse = networkService.getBrandRecommendResponse(token)
        getBrandRecommendResponse.enqueue(object : Callback<GetBrandRecommendResponse> {
            override fun onFailure(call: Call<GetBrandRecommendResponse>, t: Throwable) { Log.e("board list fail", t.toString())
            }
            override fun onResponse(call: Call<GetBrandRecommendResponse>, response: Response<GetBrandRecommendResponse>) {
                response?.let {
                    when (it.body()!!.status) {
                        200 -> {
                            Log.v("success", response.message().toString())
                            temp  = response.body()!!.data


//                            addFragment(MainBrandFragment1.getInstance(mainfeed_url1,name_english1,image_url1_1,image_url1_2,image_url1_3))
//
//                            addFragment(MainBrandFragment2.getInstance(mainfeed_url2,name_english2,image_url2_1,image_url2_2,image_url2_3))
//                            addFragment(MainBrandFragment3.getInstance(mainfeed_url3,name_english3,image_url3_1,image_url3_2,image_url3_3))









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
                }
            } })
    }
}
