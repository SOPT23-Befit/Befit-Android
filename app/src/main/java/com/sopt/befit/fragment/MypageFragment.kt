package com.sopt.befit.fragment


import android.content.Intent

import android.accounts.AccountAuthenticatorActivity
import android.app.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import com.sopt.befit.activity.*
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment :Fragment(){
    val header :MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()
    lateinit var networkService: NetworkService
    lateinit var temp : UserTotalData
    lateinit var name : String
    lateinit var email: String
    lateinit var gender : String
    lateinit var birth : String
    lateinit var brand1 : String
    lateinit var brand2 : String

    var flag =0

   // val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"


   // val token = SharedPreferenceController.getAuthorization(activity!!)




    //   var birth = AAAAMainActivity.instance.usertotalData.birthday




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val eachBrandFragmentView: View = inflater!!.inflate(R.layout.fragment_mypage, container, false)
        Utilities.setGlobalFont(eachBrandFragmentView, activity!!);
        return eachBrandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getUserDataResponse()

        tv_mypage_fragment_FnQ.setOnClickListener(){
            if(flag==0)
            {
                flag=1
                iv_mypage_arrow.setImageResource(R.drawable.arrow_up)
                lo_mypage_cus_center.visibility=View.VISIBLE
            }
           else if(flag==1)
            {
                flag = 0
                iv_mypage_arrow.setImageResource(R.drawable.arrow_down)
                lo_mypage_cus_center.visibility=View.GONE
            }
        }


    }



    fun setlistview(data : UserTotalData){



        val cus : MutableList<String> = ArrayList()

         name = data.name
         email = data.email
         gender = data.gender
        brand1 = data.brand1_idx.toString()
        brand2 = data.brand2_idx.toString()
        birth = data.birthday

//        cus.add("1:1 문의내역")
//        cus.add("상품 QnA 내역")
//        cus.add("공지사항")
//        cus.add("QnA")
//
//
//
//
//        header.add("고객센터")
//
//
//
//        body.add(cus)
//
//
//        elv_my_page_list.setAdapter(Expandable(activity!!,header,body))

        tv_mypage_fragment_preference.setOnClickListener(){

            if(gender.equals("남성")) {
                startActivity<CheckMyBrandPreferenceManActivity>("brand1" to "$brand1", "brand2" to "$brand2","gender" to gender)
            }
            else if(gender.equals("여성")){
                startActivity<CheckMyBrandPreferenceActivity>("brand1" to "$brand1", "brand2" to "$brand2","gender" to gender)

            }
        }
        tv_mypage_fragment_size.setOnClickListener(){

            startActivity<MySizeInfoCategoryActivity>("gender" to gender)
        }


        iv_mypage_setting.setOnClickListener(){
            startActivity<MyPageAccountSettingActivity>()
        }


//        elv_my_page_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
//
//
//
//            Log.e("child click", "groupPosition $groupPosition, childPosition $childPosition, id $id")
//
//
//
//
//            var gpos = groupPosition
//            var cpos = childPosition
//
//            Log.d("ssss","$gpos,$cpos")
//
//
//
//
//            if(gpos==0&&cpos==0)
//            {
//                toast("1:1 문의 내역")
//            }
//            return@setOnChildClickListener false
//
//
//        }


    }
    private fun getUserDataResponse(){
        Log.d("aaaaaaa","aaaaaa")
        networkService = ApplicationController.instance!!.networkService

        val token = SharedPreferenceController.getAuthorization(activity!!)

        val getUserDataResponse = networkService.getUserDataResponse(token)
        getUserDataResponse.enqueue(object : Callback<GetUserDataResponse> {
            override fun onFailure(call: Call<GetUserDataResponse>, t: Throwable) { Log.e("board list fail", t.toString())
            }
            override fun onResponse(call: Call<GetUserDataResponse>, response: Response<GetUserDataResponse>) {
                response?.let {
                    if (it.isSuccessful){
                        when (it.body()!!.status) {
                            200 -> {
                                Log.v("success", response.message().toString())
                                temp  = response.body()!!.data


                                tv_my_page_email.text=temp.email
                                tv_my_page_name.text=temp.name

                                setlistview(response.body()!!.data)

                                tv_mypage_fragment_total.setOnClickListener(){
                                    //tv_mypage_fragment_preference.setTextColor(Color.parseColor("#7a36e4"))

                                    var intent = Intent(activity,MyPageTotalUserInfoManage::class.java)

                                    intent.putExtra("UserTotalData",temp)
                                    startActivity(intent)


                                }



                            }

                            400 -> {
                                Log.v("fail",response.message())
                                Log.v("fail",response.errorBody().toString())
                                toast("로그인 실패")
                            }

                            500 -> {

                                Log.v("409 error",response.message())
                                Log.v("server error",response.errorBody().toString())
                                toast("서버 내부 에러")
                            }
                            600->{
                                Log.v("600 error",response.message())
                                Log.v("database error",response.errorBody().toString())
                                toast("데이터베이스 에러")
                            }
                            else -> {
                                toast("Error")
                            }
                        }
                    } else{
                        Log.d("MypageFragment Response", it.code().toString())
                    }

                }
            } })
    }
}