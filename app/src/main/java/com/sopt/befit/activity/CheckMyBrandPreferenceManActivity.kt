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

        if(brand1==17||brand2==17){
            ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)
        }
        else if(brand1==12||brand2==12){
            ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
        }
        else if(brand1==18||brand2==18){
            ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)
        }
        else if(brand1==10||brand2==10){
            ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)
        }
        else if(brand1==7||brand2==7){
            ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)
        }
        else if(brand1==9||brand2==9){
            ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)
        }
        else if(brand1==22||brand2==22){
            ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)
        }
        else if(brand1==14||brand2==14){
            ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)
        }

        ibtn_check_brand_man_back.setOnClickListener(){
            finish()
        }
    }
}
