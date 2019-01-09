package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_woman.*
import kotlinx.android.synthetic.main.activity_check_my_brand_preference.*

class CheckMyBrandPreferenceActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_my_brand_preference)

        var gender = intent.getStringExtra("gender")

        var brand1 = intent.getStringExtra("brand1")
        var brand2 = intent.getStringExtra("brand2")

        if(brand1=="17"||brand2=="17"){
            ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)

        }
        if(brand1=="12"||brand2=="12"){
            ibtn_check_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown)
        }
        if(brand1=="2"||brand2=="2"){
            ibtn_check_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav)
        }
        if(brand1=="3"||brand2=="3"){
            ibtn_check_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore)
        }
        if(brand1=="32"||brand2=="32"){
            ibtn_check_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less)
        }
        if(brand1=="9"||brand2=="9"){
            ibtn_check_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell)
        }
        if(brand1=="37"||brand2=="37"){
            ibtn_check_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi)
        }
        if(brand1=="14"||brand2=="14"){
            ibtn_check_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic)
        }

        ibtn_check_brand_back.setOnClickListener(){
            finish()
        }
    }

}
