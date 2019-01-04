package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import com.sopt.befit.fragment.MypageFragment
import kotlinx.android.synthetic.main.activity_my_page_total_user_info_manage.*
import org.jetbrains.anko.startActivity

class MyPageTotalUserInfoManage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_total_user_info_manage)


        btn_activity_total_user_back.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }
        btn_activity_total_user_complete.setOnClickListener {
            //완료 버튼 클릭시 통신을 통해 데이터 값 전달

        }

        btn_activity_total_user_search_address.setOnClickListener {
            //우편번호 찾기 버튼 누르면
            startActivity<KakaoAddressActivity>()
        }
    }
}
