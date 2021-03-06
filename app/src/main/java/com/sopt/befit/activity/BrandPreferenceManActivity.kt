package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class BrandPreferenceManActivity : BaseActivity() {

    var brand1 = ""
    var brand2 =""
    //IST KUNST	18
    //INSILENCE	22
    //COVERNAT	7
    //LIBERTENG	10
    //ANDERSSON BELL	9
    //ROMANTIC CROWN	12
    //CRITIC	14
    //THISISNEVERTHAT	17

    var cnt=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_man)
        ibtn_brand_prefer_next.isEnabled = false
        var gender : String = intent.getStringExtra("gender")

        ibtn_activity_brand_preference_man_thisisneverthat.setOnClickListener() {

            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 17 선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "17"
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "17"){
                    //cnt ==1 인 놈이 brand1 에 17일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand1 == "" && brand2 =="17"){
                    //cnt ==1 인데 brand2 에 17이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand1 != "17" && brand2 != "17" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "17"
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "17"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat) // 선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "17"){
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else if(brand2 =="17"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }

        }





        ibtn_activity_brand_preference_man_Andersson_Bell.setOnClickListener(){
                Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 9 선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "9"
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "9"){
                    //cnt ==1 인 놈이 brand1 에 9일 때
                    //브랜드 해제
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand1 == "" && brand2 =="9"){
                    //cnt ==1 인데 brand2 에 9이 들어가 있을 때
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand1 != "9" && brand2 != "9" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "9"
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "9"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "9"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else if(brand2 =="9"){
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)  //해제
                }else {
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }


        }


        ibtn_activity_brand_preference_man_Critic.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)

            if(cnt == 0){
                //cnt =>1 brand 1 에 14선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "14"
                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "14"){
                    //cnt ==1 인 놈이 brand1 에 14일 때
                    //브랜드 해제
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand1 == "" && brand2 =="14"){
                    //cnt ==1 인데 brand2 에 14이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand1 != "14" && brand2 != "14" ){
                    //cnt = 1인데 이놈이 17이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        brand1 = "14"
                        ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "14"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "14"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else if(brand2 =="14"){
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select) //해제
                }else{
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }



        }

        ibtn_activity_brand_preference_man_Ist_Kunst.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 18선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "18"
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "18"){
                    //cnt ==1 인 놈이 brand1 에 18일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand1 == "" && brand2 =="18"){
                    //cnt ==1 인데 brand2 에 18이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand1 != "18" && brand2 != "18" ){
                    //cnt = 1인데 이놈이 18이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        brand1 = "18"
                        ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "18"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "18"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else if(brand2 =="18"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet) //해제
                }else{
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }

        }

        ibtn_activity_brand_preference_man_Covernat.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 7선택
                cnt++
                brand1 = "7"
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "7"){
                    //cnt ==1 인 놈이 brand1 에 7일 때
                    //브랜드 해제
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand1 == "" && brand2 =="7"){
                    //cnt ==1 인데 brand2 에 7이 들어가 있을 때
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand1 != "7" && brand2 != "7" ){
                    //cnt = 1인데 이놈이 18이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        brand1 = "7"
                        ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "7"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "7"){
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else if(brand2 =="7"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }


        ibtn_activity_brand_preference_man_Insilence.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 22선택
                cnt++
                brand1 = "22"
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "22"){
                    //cnt ==1 인 놈이 brand1 에 22일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand1 == "" && brand2 =="22"){
                    //cnt ==1 인데 brand2 에 22이 들어가 있을 때
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled= false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand1 != "22" && brand2 != "22" ){
                    //cnt = 1인데 이놈이 22이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        brand1 = "22"
                        ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "22"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "22"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else if(brand2 =="22"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_man_Liberteng.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 10선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "10"
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "10"){
                    //cnt ==1 인 놈이 brand1 에 10일 때
                    //브랜드 해제
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    cnt--
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand1 == "" && brand2 =="10"){
                    //cnt ==1 인데 brand2 에 10이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand1 != "10" && brand2 != "10" ){
                    //cnt = 1인데 이놈이 10이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        brand1 = "10"
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "10"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "10"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else if(brand2 =="10"){
                    cnt--
                    ibtn_brand_prefer_next.isEnabled= false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }

        ibtn_activity_brand_preference_man_Romantic_crown.setOnClickListener(){
            Log.v("aaaaaa",cnt.toString())
            Log.v("aaaaaa",brand1)
            Log.v("aaaaaa",brand2)
            if(cnt == 0){
                //cnt =>1 brand 1 에 12선택
                cnt++
                ibtn_brand_prefer_next.isEnabled = false
                ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                brand1 = "12"
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
            }else if(cnt == 1){
                //무엇인가 하나가 클릭 되어있을 때
                if(brand2 == "" && brand1 == "12"){
                    //cnt ==1 인 놈이 brand1 에 12일 때
                    //브랜드 해제
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand1 = ""
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand1 == "" && brand2 =="12"){
                    //cnt ==1 인데 brand2 에 10이 들어가 있을 때
                    cnt--
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    brand2 = ""
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand1 != "12" && brand2 != "12" ){
                    //cnt = 1인데 이놈이 10이 아닐 때
                    if(brand1 == ""){
                        //1. brand2 에 뭐가 들어가 있고 brand1에 비어있을 때
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        brand1 = "12"
                        ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
                    }else {
                        //cnt =1 인데 brand1에 들어가 있을 때
                        brand2 = "12"
                        cnt++
                        ibtn_brand_prefer_next.isEnabled = true
                        ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                        ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown) //선택
                    }
                }
            }else if(cnt == 2){
                if(brand1 == "12"){
                    cnt--
                    brand1 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else if(brand2 =="12"){
                    cnt--
                    brand2 = ""
                    ibtn_brand_prefer_next.isEnabled = false
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_grayarrow)
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select) //해제
                }else{
                    //이미 두개가 다 다른거로 선택 되어있을 때
                    ibtn_brand_prefer_next.isEnabled = true
                    ibtn_brand_prefer_next.setImageResource(R.drawable.ic_purplearrow)
                    toast("두개 까지만 선택할 수 있습니다.").show()
                }


            }
        }





        ibtn_brand_prefer_back.setOnClickListener(){
            finish()
        }

        ibtn_brand_prefer_next.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
            finish()
        }
    }


}
