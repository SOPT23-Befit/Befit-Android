package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class BrandPreferenceManActivity : AppCompatActivity() {

    var preferenceCnt = 0
    lateinit var brand1 : String
    lateinit var brand2 : String
    //thisis=1,romantic=2,ist=3,liber=4,cover=5,andersson=6,isilense=7,critic=8
    var toggle : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_man)

        var gender : String = intent.getStringExtra("gender")


        ibtn_activity_brand_preference_man_thisisneverthat.setOnClickListener() {
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "1"
                    toggle = 1
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)
                } else if (brand2.isEmpty()) {
                    brand2 = "1"
                    toggle = 1
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select)
            }
        }

        ibtn_activity_brand_preference_man_Andersson_Bell.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "6"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)
                } else if (brand2.isEmpty()) {
                    brand2 = "6"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)
            }
        }


        ibtn_activity_brand_preference_man_Critic.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "8"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)
                } else if (brand2.isEmpty()) {
                    brand2 = "8"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select)
            }
        }

        ibtn_activity_brand_preference_man_Insilence.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "7"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)
                } else if (brand2.isEmpty()) {
                    brand2 = "7"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select)
            }
        }

        ibtn_activity_brand_preference_man_Covernat.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "5"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)
                } else if (brand2.isEmpty()) {
                    brand2 = "5"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select)
            }
        }


        ibtn_activity_brand_preference_man_Ist_Kunst.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "3"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)
                } else if (brand2.isEmpty()) {
                    brand2 = "3"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet)
            }
        }

        ibtn_activity_brand_preference_man_Liberteng.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "4"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)
                } else if (brand2.isEmpty()) {
                    brand2 = "4"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select)
            }
        }

        ibtn_activity_brand_preference_man_Romantic_crown.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "2"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
                } else if (brand2.isEmpty()) {
                    brand2 = "2"
                    toggle = 1
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                toggle=0

                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)
            }
        }






        ibtn_brand_prefer_next.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
        }
    }


}
