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

        var brand1 = intent.getIntExtra("brand1",0)
        var brand2 = intent.getIntExtra("brand2",0)

        if(brand1==1||brand2==1){
            ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)

        }
        else if(brand1==2||brand2==2){
            ibtn_check_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown)
        }
        else if(brand1==3||brand2==3){
            ibtn_check_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav)
        }
        else if(brand1==4||brand2==4){
            ibtn_check_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore)
        }
        else if(brand1==5||brand2==5){
            ibtn_check_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less)
        }
        else if(brand1==6||brand2==6){
            ibtn_check_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell)
        }
        else if(brand1==7||brand2==7){
            ibtn_check_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi)
        }
        else if(brand1==8||brand2==8){
            ibtn_check_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic)
        }

        ibtn_check_brand_back.setOnClickListener(){
            finish()
        }
    }

}
