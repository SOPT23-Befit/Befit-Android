package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_select_gender.*
import org.jetbrains.anko.startActivity

class SelectGenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_gender)

        var gender : Int = 0
        var status_m : Int = 0//1이면 눌림 0 안눌림
        var status_fm : Int = 0

        ibtn_select_gender_next.setOnClickListener(){



            if(gender==1){
                startActivity<BrandPreferenceWomanActivity>("gender" to "여")
            }
            if(gender==2){
                startActivity<BrandPreferenceManActivity>("gender" to "남")
            }

        }

        ibtn_select_gender_back.setOnClickListener(){

            startActivity<LogInActivity>()
        }

        lo_select_gender_female.setOnClickListener(){

            gender = 1
                    if(status_fm==0){
                        if(status_m==0) {
                            ibtn_select_gender_next.isClickable = true
                            status_fm = 1
                            iv_gender_fm.setImageResource(R.drawable.woman_clicked)
                            ibtn_select_gender_next.setImageResource(R.drawable.ic_purplearrow)
                        }
                        else if (status_m==1)
                        {
                            ibtn_select_gender_next.isClickable = true
                            status_fm = 1
                            iv_gender_fm.setImageResource(R.drawable.woman_clicked)
                            status_m=0
                            iv_gender_m.setImageResource(R.drawable.man)
                        }
                    }

            else if(status_fm==1){
                        ibtn_select_gender_next.isClickable=false
                        ibtn_select_gender_next.setImageResource(R.drawable.ic_grayarrow)
                        status_fm=0
                        iv_gender_fm.setImageResource(R.drawable.woman)
                    }



        }
        lo_select_gender_male.setOnClickListener(){

            gender = 2
            if(status_m==0){
                if(status_fm==0) {
                    ibtn_select_gender_next.isClickable = true
                    status_m = 1
                    iv_gender_m.setImageResource(R.drawable.man_clicked)
                    ibtn_select_gender_next.setImageResource(R.drawable.ic_purplearrow)
                }
                else if(status_fm==1){
                    ibtn_select_gender_next.isClickable = true
                    status_m = 1
                    iv_gender_m.setImageResource(R.drawable.man_clicked)
                    status_fm=0
                    iv_gender_fm.setImageResource(R.drawable.woman)
                }
            }
            else if(status_m==1){
                ibtn_select_gender_next.isClickable=false
                ibtn_select_gender_next.setImageResource(R.drawable.ic_grayarrow)
                status_m=0
                iv_gender_m.setImageResource(R.drawable.man)
            }

        }

    }
}
