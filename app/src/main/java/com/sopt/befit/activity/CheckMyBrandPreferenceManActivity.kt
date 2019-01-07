package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_woman.*
import kotlinx.android.synthetic.main.activity_check_my_brand_preference.*
import kotlinx.android.synthetic.main.activity_check_my_brand_preference_man.*

class CheckMyBrandPreferenceManActivity : AppCompatActivity() {

    var gender = intent.getStringExtra("gender")
    var brand1 = intent.getIntExtra("brand1",0)
    var brand2 = intent.getIntExtra("brand2",0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_my_brand_preference_man)

        if(brand1==1||brand2==1){
            ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)
        }
        else if(brand1==2||brand2==2){
            ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
        }
        else if(brand1==3||brand2==3){
            ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)
        }
        else if(brand1==4||brand2==4){
            ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)
        }
        else if(brand1==5||brand2==5){
            ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)
        }
        else if(brand1==6||brand2==6){
            ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)
        }
        else if(brand1==7||brand2==7){
            ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)
        }
        else if(brand1==8||brand2==8){
            ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)
        }

        ibtn_check_brand_man_back.setOnClickListener(){
            finish()
        }
    }
}
