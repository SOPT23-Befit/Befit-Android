package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.data.LoginData
import com.sopt.befit.data.UserData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : BaseActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var tok :String
    lateinit var loginData: LoginData

    private fun getLoginResponse() {
        if (et_log_in_email.text.toString().isNotEmpty() && et_log_in_pw.text.toString().isNotEmpty()) {
            val input_id = et_log_in_email.text.toString()
            val input_pw = et_log_in_pw.text.toString()
            val jsonObject: JSONObject = JSONObject()
            jsonObject.put("email", input_id)
            jsonObject.put("password", input_pw)

            loginData = LoginData(input_id,input_pw)

            val postLogInResponse = networkService.postLoginResponse(loginData)

            postLogInResponse.enqueue(object : Callback<PostLoginResponse> {
                override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                    Log.e("Login fail", t.toString())
                }

                override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                    if (response.isSuccessful) {

                        Log.v("success communicate", response.body().toString())
                        when (response.body()!!.status) {
                            200 -> {

                                Log.v("success", response.message().toString())
                                val token = response.body()!!.data.token
//저번 시간에 배웠던 SharedPreference에 토큰을 저장!

                                SharedPreferenceController.setAuthorization(this@LogInActivity, token)
                               // toast(SharedPreferenceController.getAuthorization(this@LogInActivity))
                                startActivity<AAAAMainActivity>()
                                finish()


                            }



                            401 -> {
                                Log.v("401 fail", response.message())
                                Log.v("fail", response.errorBody().toString())
                                toast("인증 실패")
                            }

                            500 -> {

                                Log.v("409 error", response.message())
                                Log.v("server error", response.errorBody().toString())
                                toast("서버 내부 에러")
                            }
                            600 -> {
                                Log.v("600 error", response.message())
                                Log.v("database error", response.errorBody().toString())
                                toast("데이터베이스 에러")
                            }
                            else -> {
                                toast("Error")
                            }
                        }
                    }
                    else{
                        toast("로그인 실패")
                    }
                }
            })
        }
    }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_log_in)

            setOnBtnClickListener()

            tok = SharedPreferenceController.getAuthorization(this)

            if (tok.isNotEmpty()) {
                //저장된 아이디가 있으면 자동로그인 메인으로 바로 이동
                startActivity<AAAAMainActivity>("token" to "$tok")
                finish()
            }

            btn_log_in_join.setOnClickListener() {
                startActivity<SelectGenderActivity>()
            }


        }

        private fun setOnBtnClickListener() {                          //로그인 버튼
            ibtn_log_in_confirm.setOnClickListener {

                getLoginResponse()

            }
        }

    }

