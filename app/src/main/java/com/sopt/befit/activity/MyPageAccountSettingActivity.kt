package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.ToggleButton
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import java.lang.Boolean.TRUE

class MyPageAccountSettingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_account_setting)


        setSwitchClick()
    }
    fun setSwitchClick(){
        sw_activity_my_page_account_setting_turn.setOnCheckedChangeListener { switch, isChecked ->
            if (isChecked) {
                //ON일 때 작동할 로직
            } else {
                //OFF일 때 작동할 로직
            }
        }
    }
}
