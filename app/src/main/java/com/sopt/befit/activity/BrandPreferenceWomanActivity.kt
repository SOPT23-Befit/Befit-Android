package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import kotlinx.android.synthetic.main.activity_brand_preference_woman.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class BrandPreferenceWomanActivity : BaseActivity() {

     var brand1 = ""
     var brand2 = ""
//    MINAV	2 //
//    LAFUDGESTORE	3 //
//    MORE OR LESS	32
//    OiOi	37 //
//    ANDERSSON BELL	9 //
//    ROMANTIC CROWN	12 //
//    CRITIC	14 //
//    THISISNEVERTHAT	17 //
    var toggle : Int =0
    var cnt =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_woman)

        //var gender : String = intent.getStringExtra("gender")
        var gender = "여성"


        ibtn_brand_preference_woman_thisisneverthat.setOnClickListener() {

            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 17 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "17"
                ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "17"){
                    //cnt ==1 인 놈이 brand1 에 17일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_thisisneverthat) //해제
                }else if(brand1 == "" && brand2 =="17"){
                    //cnt ==1 인데 brand2 에 17이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_thisisneverthat) //해제
                }else if(brand1 != "17" && brand2 != "17" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "17"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)  // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "17"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_select_thisisneverthat)  // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "17"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_thisisneverthat) //해제
                }else if(brand2 =="17"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_brand_preference_woman_thisisneverthat.setImageResource(R.drawable.woman_thisisneverthat) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }

        }

        ibtn_activity_brand_preference_woman_Romantic_crown.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 12 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "12"
                ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "12"){
                    //cnt ==1 인 놈이 brand1 에 12일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_romantic_crwon) //해제
                }else if(brand1 == "" && brand2 =="12"){
                    //cnt ==1 인데 brand2 에 12이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_romantic_crwon) //해제
                }else if(brand1 != "12" && brand2 != "12" ){
                    //cnt = 1인데 이놈이 12이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "12"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown)  // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "12"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_select_romantic_crown) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "12"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_romantic_crwon) //해제
                }else if(brand2 =="12"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_Romantic_crown.setImageResource(R.drawable.woman_romantic_crwon) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_woman_Lafudgestore.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 3 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "3"
                ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "3"){
                    //cnt ==1 인 놈이 brand1 에 3일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else if(brand1 == "" && brand2 =="3"){
                    //cnt ==1 인데 brand2 에 3이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else if(brand1 != "3" && brand2 != "3" ){
                    //cnt = 1인데 이놈이 3이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "3"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "3"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_select_lafudgestore) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "3"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else if(brand2 =="3"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_Lafudgestore.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_woman_Anderson_Bell.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 9 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "9"
                ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "9"){
                    //cnt ==1 인 놈이 brand1 에 9일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_andersson_bell) //해제
                }else if(brand1 == "" && brand2 =="9"){
                    //cnt ==1 인데 brand2 에 9이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_andersson_bell) //해제
                }else if(brand1 != "9" && brand2 != "9" ){
                    //cnt = 1인데 이놈이 3이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "9"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "9"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_select_andersson_bell) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "9"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else if(brand2 =="9"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_Anderson_Bell.setImageResource(R.drawable.woman_lafudgestore) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_woman_Critic.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 14 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "14"
                ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "14"){
                    //cnt ==1 인 놈이 brand1 에 14일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_critic) //해제
                }else if(brand1 == "" && brand2 =="14"){
                    //cnt ==1 인데 brand2 에 14이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_critic) //해제
                }else if(brand1 != "14" && brand2 != "14" ){
                    //cnt = 1인데 이놈이 3이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "14"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "14"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_select_critic) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "14"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_critic) //해제
                }else if(brand2 =="14"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_Critic.setImageResource(R.drawable.woman_critic) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_woman_OIOI.setOnClickListener {
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 37 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "37"
                ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "37"){
                    //cnt ==1 인 놈이 brand1 에 37일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_oioi) //해제
                }else if(brand1 == "" && brand2 =="37"){
                    //cnt ==1 인데 brand2 에 37이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_oioi) //해제
                }else if(brand1 != "37" && brand2 != "37" ){
                    //cnt = 1인데 이놈이 3이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "37"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "37"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_select_oioi) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "37"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_oioi) //해제
                }else if(brand2 =="37"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_OIOI.setImageResource(R.drawable.woman_oioi) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }

        }

        ibtn_activity_brand_preference_woman_Minav.setOnClickListener {
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 2 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "2"
                ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "2"){
                    //cnt ==1 인 놈이 brand1 에 2일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_minav) //해제
                }else if(brand1 == "" && brand2 =="2"){
                    //cnt ==1 인데 brand2 에 2이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_minav) //해제
                }else if(brand1 != "2" && brand2 != "2" ){
                    //cnt = 1인데 이놈이 2이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "2"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "2"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_select_minav) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "2"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_minav) //해제
                }else if(brand2 =="2"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_Minav.setImageResource(R.drawable.woman_minav) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_woman_More_or_Less.setOnClickListener {
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 32 선택
                cnt++
                ibtn_brand_prefer_next_wm.isClickable = false
                ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "32"
                ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "32"){
                    //cnt ==1 인 놈이 brand1 에 32일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_more_or_less) //해제
                }else if(brand1 == "" && brand2 =="2"){
                    //cnt ==1 인데 brand2 에 2이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_more_or_less) //해제
                }else if(brand1 != "32" && brand2 != "32" ){
                    //cnt = 1인데 이놈이 32이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "32"
                        ibtn_brand_prefer_next_wm.isClickable = false
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                        ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "32"
                        cnt++
                        ibtn_brand_prefer_next_wm.isClickable  = true
                        ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_select_more_or_less) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "32"){
                    cnt--
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_more_or_less) //해제
                }else if(brand2 =="32"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next_wm.isClickable = false
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_woman_More_or_Less.setImageResource(R.drawable.woman_more_or_less) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next_wm.isClickable  = true
                    ibtn_brand_prefer_next_wm.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


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
