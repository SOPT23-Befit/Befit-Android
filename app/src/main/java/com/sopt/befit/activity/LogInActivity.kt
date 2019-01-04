package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import com.sopt.befit.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        setOnBtnClickListener()

        if (SharedPreferenceController.getUserID(this).isNotEmpty()) {      //저장된 아이디가 있으면 자동로그인 메인으로 바로 이동
            startActivity<AAAAMainActivity>()
            finish()
        }

        btn_log_in_join.setOnClickListener(){
            startActivity<SelectGenderActivity>()
        }
        ibtn_log_in_confirm.setOnClickListener(){


            startActivity<MainActivity>()//
        }


    }
    private fun setOnBtnClickListener() {                          //로그인 버튼
        ibtn_log_in_confirm.setOnClickListener {
            val input_id: String = et_log_in_email.text.toString()
            val input_pw: String = et_log_in_pw.text.toString()

            //만약 서버가 존재한다면 여기서 로그인 성공 여부를 체크하겠죠?!
            // 지금은 서버가 없으므로 공백인지 아닌지만 체크해줍니다!
            if (input_id.isNotEmpty() && input_pw.isNotEmpty()) {
                    SharedPreferenceController.setUserID(this, input_id)
                    SharedPreferenceController.setUserPW(this, input_pw)
                startActivity<AAAAMainActivity>()
                finish()
            }
        }
    }

}
