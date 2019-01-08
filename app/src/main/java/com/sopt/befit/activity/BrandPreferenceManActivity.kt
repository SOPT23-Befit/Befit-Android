package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class BrandPreferenceManActivity : AppCompatActivity() {

    var preferenceCnt = 0
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

        var gender : String = intent.getStringExtra("gender")


        ibtn_activity_brand_preference_man_thisisneverthat.setOnClickListener() {

            Log.v("ssss","$cnt,$brand1,$brand2")

            if(cnt==0){
                cnt++
                brand1="17"
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)


            }


            else if(cnt==1&&brand1!="17"){
                cnt++
                brand2="17"
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)

            }
            else if(cnt==1&&brand2!="17"){
                cnt++
                brand1="17"
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_select_thisisneverthat)

            }
            else if(cnt==1&&brand2.equals("17"))
            {
                cnt--
                brand2=""
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select)
            }
            else if(cnt==1&&brand1.equals("17"))
            {
                Log.v("aaaa",cnt.toString())
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("17")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select)

                }
                else if(brand2.equals("17"))
                {
                    cnt--
                    brand2=""
                    ibtn_activity_brand_preference_man_thisisneverthat.setImageResource(R.drawable.man_thisisneverthat_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }
        ibtn_activity_brand_preference_man_Andersson_Bell.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="9"
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)


            }
            else if(cnt==1&&brand1!="9"){
                cnt++
                brand2="9"
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)

            }
            else if(cnt==1&&brand2!="9"){
                cnt++
                brand1="9"
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_select_anderssonbell)

            }
            else if(cnt==1&&brand2.equals("9"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)
            }
            else if(cnt==1&&brand1.equals("9"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("9")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)
                }
                else if(brand2.equals("9"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_anderssonbell_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }

        }


        ibtn_activity_brand_preference_man_Critic.setOnClickListener(){


            if(cnt==0){
                cnt++
                brand1="14"
                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)


            }
            else if(cnt==1&&brand1!="14"){
                cnt++
                brand2="14"
                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)

            }
            else if(cnt==1&&brand2!="14"){
                cnt++
                brand1="14"
                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_select_critic)

            }
            else if(cnt==1&&brand2.equals("14"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select)
            }
            else if(cnt==1&&brand1.equals("14"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Andersson_Bell.setImageResource(R.drawable.man_critic_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("14")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select)
                }
                else if(brand2.equals("14"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Critic.setImageResource(R.drawable.man_critic_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }


        }

        ibtn_activity_brand_preference_man_Ist_Kunst.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="18"
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)


            }
            else if(cnt==1&&brand1!="18"){
                cnt++
                brand2="18"
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)

            }
            else if(cnt==1&&brand2!="18"){
                cnt++
                brand1="18"
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_select_istkunst)

            }
            else if(cnt==1&&brand2.equals("18"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet)
            }
            else if(cnt==1&&brand1.equals("18"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet)

            }
            else if(cnt==2){
                if(brand1.equals("18")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet)
                }
                else if(brand2.equals("18"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Ist_Kunst.setImageResource(R.drawable.man_istkunst_brand_secet)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }

        ibtn_activity_brand_preference_man_Covernat.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="7"
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)


            }
            else if(cnt==1&&brand1!="7"){
                cnt++
                brand2="7"
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)

            }
            else if(cnt==1&&brand2!="7"){
                cnt++
                brand1="7"
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_select_covernat)

            }
            else if(cnt==1&&brand2.equals("7"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select)
            }
            else if(cnt==1&&brand1.equals("7"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("7")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select)
                }
                else if(brand2.equals("7"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Covernat.setImageResource(R.drawable.man_covernat_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }


        ibtn_activity_brand_preference_man_Insilence.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="18"
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)


            }
            else if(cnt==1&&brand1!="18"){
                cnt++
                brand2="18"
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)

            }
            else if(cnt==1&&brand2!="18"){
                cnt++
                brand1="18"
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_select_insilence)

            }
            else if(cnt==1&&brand2.equals("18"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select)
            }
            else if(cnt==1&&brand1.equals("18"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("18")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select)
                }
                else if(brand2.equals("18"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Insilence.setImageResource(R.drawable.man_insilence_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }

        ibtn_activity_brand_preference_man_Liberteng.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="10"
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)


            }
            else if(cnt==1&&brand1!="10"){
                cnt++
                brand2="10"
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)

            }
            else if(cnt==1&&brand2!="10"){
                cnt++
                brand1="10"
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_select_liberteng)

            }
            else if(cnt==1&&brand2.equals("10"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select)
            }
            else if(cnt==1&&brand1.equals("10"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("10")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select)
                }
                else if(brand2.equals("10"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Liberteng.setImageResource(R.drawable.man_liberteng_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }

        ibtn_activity_brand_preference_man_Romantic_crown.setOnClickListener(){

            if(cnt==0){
                cnt++
                brand1="12"
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)


            }
            else if(cnt==1&&brand1!="12"){
                cnt++
                brand2="12"
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)

            }
            else if(cnt==1&&brand2!="12"){
                cnt++
                brand1="12"
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_select_romanticcrown)

            }
            else if(cnt==1&&brand2.equals("12"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)
            }
            else if(cnt==1&&brand1.equals("12"))
            {
                cnt--
                brand1=""
                ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)

            }
            else if(cnt==2){
                if(brand1.equals("12")){
                    cnt--
                    brand1=""
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)
                }
                else if(brand2.equals("12"))
                {
                    cnt--

                    brand2=""
                    ibtn_activity_brand_preference_man_Romantic_crown.setImageResource(R.drawable.man_romanticcrown_brand_select)

                }
                else
                {
                    toast("두개까지 선택 할 수 있습니다.")
                }
            }
        }





        ibtn_brand_prefer_back.setOnClickListener(){
            finish()
        }

        ibtn_brand_prefer_next.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
        }
    }


}
