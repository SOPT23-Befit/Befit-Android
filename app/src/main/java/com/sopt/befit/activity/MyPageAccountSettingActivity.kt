package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.ToggleButton
import com.sopt.befit.R
import com.sopt.befit.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import java.lang.Boolean.TRUE

class MyPageAccountSettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_account_setting)


        setSwitchClick()
        logOutOnClick()

    }

    fun setSwitchClick(){
        sw_activity_my_page_account_setting_turn.setOnCheckedChangeListener { switch, isChecked ->
            if (isChecked) {

            }

            else {
                //OFF일 때
                SharedPreferenceController.clearUserSharedPreferences(this)
            }
        }
    }
    fun logOutOnClick(){
        btn_activity_my_page_account_setting_log_out.setOnClickListener {
            SharedPreferenceController.clearUserSharedPreferences(this)
            startActivity<LogInActivity>()
            finish()
        }
    }
}
