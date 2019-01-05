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
    var toggle : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_man)

        var gender : String = intent.getStringExtra("gender")


        ibtn_thisisne.setOnClickListener() {
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "thisisneverthat"
                    toggle = 1
                    ibtn_thisisne.setImageResource(R.drawable.man_select_thisisneverthat)
                } else if (brand2.isEmpty()) {
                    brand2 = "thisisneverthat"
                    toggle = 1
                    ibtn_thisisne.setImageResource(R.drawable.man_select_thisisneverthat)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_thisisne.setImageResource(R.drawable.man_thisisneverthat_brand_select)
            }
        }

        ibtn_andersson_bell_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "andersonbell"
                    toggle = 1
                    ibtn_andersson_bell_man.setImageResource(R.drawable.man_select_anderssonbell)
                } else if (brand2.isEmpty()) {
                    brand2 = "andersonbell"
                    toggle = 1
                    ibtn_andersson_bell_man.setImageResource(R.drawable.man_select_anderssonbell)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_andersson_bell_man.setImageResource(R.drawable.man_anderssonbell_brand_select)
            }
        }


        ibtn_critic_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "critic"
                    toggle = 1
                    ibtn_critic_man.setImageResource(R.drawable.man_select_critic)
                } else if (brand2.isEmpty()) {
                    brand2 = "critic"
                    toggle = 1
                    ibtn_critic_man.setImageResource(R.drawable.man_select_critic)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_critic_man.setImageResource(R.drawable.man_critic_brand_select)
            }
        }

        ibtn_insilence_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "insilence"
                    toggle = 1
                    ibtn_insilence_man.setImageResource(R.drawable.man_select_insilence)
                } else if (brand2.isEmpty()) {
                    brand2 = "insilence"
                    toggle = 1
                    ibtn_insilence_man.setImageResource(R.drawable.man_select_insilence)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_insilence_man.setImageResource(R.drawable.man_insilence_brand_select)
            }
        }

        ibtn_covernat_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "covernat"
                    toggle = 1
                    ibtn_covernat_man.setImageResource(R.drawable.man_select_covernat)
                } else if (brand2.isEmpty()) {
                    brand2 = "covernat"
                    toggle = 1
                    ibtn_covernat_man.setImageResource(R.drawable.man_select_covernat)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_covernat_man.setImageResource(R.drawable.man_covernat_brand_select)
            }
        }


        ibtn_istkunst_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "istkunst"
                    toggle = 1
                    ibtn_istkunst_man.setImageResource(R.drawable.man_select_istkunst)
                } else if (brand2.isEmpty()) {
                    brand2 = "istkunst"
                    toggle = 1
                    ibtn_istkunst_man.setImageResource(R.drawable.man_select_istkunst)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_istkunst_man.setImageResource(R.drawable.man_istkunst_brand_secet)
            }
        }

        ibtn_liberteng_man.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "liberteng"
                    toggle = 1
                    ibtn_liberteng_man.setImageResource(R.drawable.man_select_liberteng)
                } else if (brand2.isEmpty()) {
                    brand2 = "liberteng"
                    toggle = 1
                    ibtn_liberteng_man.setImageResource(R.drawable.man_select_liberteng)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_liberteng_man.setImageResource(R.drawable.man_liberteng_brand_select)
            }
        }

        ibtn_romantic_crown.setOnClickListener(){
            if (toggle == 0) {
                if (brand1.isEmpty() && brand2.isEmpty()) {
                    brand1 = "romanticcrown"
                    toggle = 1
                    ibtn_romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
                } else if (brand2.isEmpty()) {
                    brand2 = "andersonbell"
                    toggle = 1
                    ibtn_romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
                } else
                    toast("최대 두개까지만 선택 가능합니다.")
            }
            else if (toggle == 1) {
                ibtn_romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)
            }
        }






        ibtn_brand_prefer_next.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
        }
    }


}
