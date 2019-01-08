package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import kotlinx.android.synthetic.main.activity_brand_preference_woman.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class BrandPreferenceWomanActivity : AppCompatActivity() {

    lateinit var brand1 : String
    lateinit var brand2 : String
    //thisisnevrthat =1 , romanticcrown=2, minave =3 , lafudgestore=4, moreorless=5 , andersson bell=6,oioi=7,critic=8
    var toggle : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_woman)

        var gender : String = intent.getStringExtra("gender")


        ibtn_brand_preference_woman_thisisneverthat.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "1"
                    toggle = 1
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)
                } else if (brand2.isEmpty()) {
                    brand2 = "1"
                    toggle = 1
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0
                ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_thisisneverthat)
            }
        }

        ibtn_activity_brand_preference_woman_Romantic_crown.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "2"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown)
                } else if (brand2.isEmpty()) {
                    brand2 = "2"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_romantic_crwon)
            }
        }
        ibtn_activity_brand_preference_woman_Minav.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "3"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav)
                } else if (brand2.isEmpty()) {
                    brand2 = "3"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_minav)
            }
        }

        ibtn_activity_brand_preference_woman_Lafudgestore.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "4"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore)
                } else if (brand2.isEmpty()) {
                    brand2 = "4"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_lafudgestore)
            }
        }
        ibtn_activity_brand_preference_woman_More_or_Less.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "5"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less)
                } else if (brand2.isEmpty()) {
                    brand2 = "5"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_more_or_less)
            }
        }

        ibtn_activity_brand_preference_woman_Anderson_Bell.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "6"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell)
                } else if (brand2.isEmpty()) {
                    brand2 = "6"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_andersson_bell)
            }
        }

        ibtn_activity_brand_preference_woman_OIOI.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "7"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi)
                } else if (brand2.isEmpty()) {
                    brand2 = "7"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_oioi)
            }
        }

        ibtn_activity_brand_preference_woman_Critic.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "8"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic)
                } else if (brand2.isEmpty()) {
                    brand2 = "8"
                    toggle = 1
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_critic)
            }
        }

        ibtn_brand_prefer_woman_back.setOnClickListener(){
            finish()
        }

        ibtn_brand_prefer_next_wm.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
        }

    }
}
