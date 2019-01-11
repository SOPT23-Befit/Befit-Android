package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.befit.R
import com.sopt.befit.data.ModifyBrandData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.put.PutModifyBrandResponse
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import kotlinx.android.synthetic.main.activity_brand_preference_woman.*
import kotlinx.android.synthetic.main.activity_check_my_brand_preference.*
import kotlinx.android.synthetic.main.activity_check_my_brand_preference_man.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckMyBrandPreferenceManActivity : BaseActivity() {

   // var gender = intent.getStringExtra("gender")
  //  var brand1 = intent.getStringExtra("brand1")
   // var brand2 = intent.getStringExtra("brand2")

    var gender = "남성"

    var cnt = 2
    lateinit var modifyBrandData: ModifyBrandData
    lateinit var networkservice : NetworkService
    lateinit var brand1 : String
    lateinit var brand2 : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_my_brand_preference_man)



        brand1 = intent.getStringExtra("brand1")
        brand2 = intent.getStringExtra("brand2")

        iv_man_brand_preference_modify_ok.setOnClickListener(){
            putBrandModify(brand1,brand2)



        }


        if(brand1=="17"||brand2=="17"){
            ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)
        }
        if(brand1=="12"||brand2=="12"){
            ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)
        }
        if(brand1=="18"||brand2=="18"){
            ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)
        }
        if(brand1=="10"||brand2=="10"){
            ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)
        }
        if(brand1=="7"||brand2=="7"){
            ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)
        }
        if(brand1=="9"||brand2=="9"){
            ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)
        }
        if(brand1=="22"||brand2=="22"){
            ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)
        }
        if(brand1=="14"||brand2=="14"){
            ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)
        }

        ibtn_check_brand_man_back.setOnClickListener(){
            finish()
        }


        ibtn_check_brand_preference_man_thisisneverthat.setOnClickListener() {

            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 17 선택

                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "17"
                ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "17"){
                    //cnt ==1 인 놈이 brand1 에 17일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand1 == "" && brand2 =="17"){
                    //cnt ==1 인데 brand2 에 17이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand1 != "17" && brand2 != "17" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++

                        ibtn_check_brand_man_back.isEnabled=true
                        brand1 = "17"
                        ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "17"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "17"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand2 =="17"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }

        }





        ibtn_check_brand_preference_man_Andersson_Bell.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 9 선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "9"
                ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "9"){
                    //cnt ==1 인 놈이 brand1 에 9일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand1 == "" && brand2 =="9"){
                    //cnt ==1 인데 brand2 에 9이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand1 != "9" && brand2 != "9" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "9"
                        ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "9"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "9"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand2 =="9"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else {
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }


        }


        ibtn_check_brand_preference_man_Critic.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 14선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "14"
                ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "14"){
                    //cnt ==1 인 놈이 brand1 에 14일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand1 == "" && brand2 =="14"){
                    //cnt ==1 인데 brand2 에 14이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand1 != "14" && brand2 != "14" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "14"
                        ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "14"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "14"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand2 =="14"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }



        }

        ibtn_check_brand_preference_man_Ist_Kunst.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 18선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "18"
                ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "18"){
                    //cnt ==1 인 놈이 brand1 에 18일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand1 == "" && brand2 =="18"){
                    //cnt ==1 인데 brand2 에 18이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand1 != "18" && brand2 != "18" ){
                    //cnt = 1인데 이놈이 18이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "18"
                        ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "18"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "18"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand2 =="18"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }

        }

        ibtn_check_brand_preference_man_Covernat.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 7선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "7"
                ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "7"){
                    //cnt ==1 인 놈이 brand1 에 7일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand1 == "" && brand2 =="7"){
                    //cnt ==1 인데 brand2 에 7이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand1 != "7" && brand2 != "7" ){
                    //cnt = 1인데 이놈이 18이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++

                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "7"
                        ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "7"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "7"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand2 =="7"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                     toast("두개 까지만 선택할 수 있습니다.")
                }


            }
        }


        ibtn_check_brand_preference_man_Insilence.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 22선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "22"
                ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "22"){
                    //cnt ==1 인 놈이 brand1 에 22일 때
                    //브랜드 해제
                     cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand1 == "" && brand2 =="22"){
                    //cnt ==1 인데 brand2 에 22이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand1 != "22" && brand2 != "22" ){
                    //cnt = 1인데 이놈이 22이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "22"
                        ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "22"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "22"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand2 =="22"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }
        }

        ibtn_check_brand_preference_man_Liberteng.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 10선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "10"
                ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "10"){
                    //cnt ==1 인 놈이 brand1 에 10일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand1 == "" && brand2 =="10"){
                    //cnt ==1 인데 brand2 에 10이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand1 != "10" && brand2 != "10" ){
                    //cnt = 1인데 이놈이 10이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "10"
                        ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "10"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "10"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand2 =="10"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }
        }

        ibtn_check_brand_preference_man_Romantic_crown.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 12선택
                cnt++
                ibtn_check_brand_man_back.isEnabled=false

                brand1 = "12"
                ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "12"){
                    //cnt ==1 인 놈이 brand1 에 12일 때
                    //브랜드 해제
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand1 == "" && brand2 =="12"){
                    //cnt ==1 인데 brand2 에 10이 들어가 있을 때
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand1 != "12" && brand2 != "12" ){
                    //cnt = 1인데 이놈이 10이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        brand1 = "12"
                        ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "12"
                        cnt++
                        ibtn_check_brand_man_back.isEnabled=true

                        ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "12"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand1 = ""
                    ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand2 =="12"){
                    cnt--
                    ibtn_check_brand_man_back.isEnabled=false

                    brand2 = ""
                    ibtn_check_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.")
                }


            }
        }
    }
    fun putBrandModify(brand1 : String,brand2 : String){
        //modifypwdata에 값 넣기
        modifyBrandData = ModifyBrandData(brand1.toInt(),brand2.toInt())

        Log.d("Modify Brand",modifyBrandData.toString())

        val token = SharedPreferenceController.getAuthorization(this)

        networkservice = ApplicationController.instance.networkService
        var modifyBrandResponse= networkservice.putModifyBrandResponse(token,modifyBrandData)
        modifyBrandResponse!!.enqueue(object : Callback<PutModifyBrandResponse> {
            override fun onFailure(call: Call<PutModifyBrandResponse>, t: Throwable) {
                Log.v("Error ModifyActivity",t.message)
            }
            override fun onResponse(call: Call<PutModifyBrandResponse>, response: Response<PutModifyBrandResponse>) {
                if (response.isSuccessful) {
                    Log.v("aaaa",response.body()!!.status.toString())
                    when (response.body()!!.status) {
                        200 -> {
                            Log.v("success", response.body().toString())
                            toast("성공적으로 수정되었습니다.")
                            finish()


                        }
                        204 -> {
                            Log.v("회원 수정 정보가 잘못되었습니다.", response.body().toString())
                            toast("회원 수정 정보가 잘못되었습니다.")
                        }
                        500 -> {

                            Log.v("서버 내부 에러", response.body().toString())
                            toast("서버 에러 .")
                        }
                        600 -> {


                            Log.v("DB 에러", response.body().toString())
                            toast("디비에러 .")
                        }
                        else -> {
                            Log.v("zzz","zz")
                            toast("Error")
                        }
                    }
                }


            }
        })
    }
}
