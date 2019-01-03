package com.sopt.befit.activity

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.data.ForPwUserData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostForPwFindUserResponse
import kotlinx.android.synthetic.main.activity_search_password.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.networkStatsManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPasswordActivity : AppCompatActivity() {
        lateinit var ForPwuserData : ForPwUserData
        lateinit var networkservice : NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_password)
        btn_activity_search_pw_ok.setOnClickListener {
            val intent: Intent = Intent()
            tv_sign_up_overlap.visibility = View.INVISIBLE
            val name = et_activity_search_pw_name.text.toString()
            val email = et_activity_search_pw_email.text.toString()
            val birthday = tv_activity_search_pw_year.text.toString() + tv_activity_search_pw_month.text.toString()+tv_activity_search_pw_day.text.toString()
            if(name.length> 0&& email.length>0 && birthday.length == 8){
                    //회원 정보 있는지 통신하기
                    ForPwuserData =  ForPwUserData(email, name,birthday)
                var searchUserResponse = networkservice.ForPwUserDataResponse(ForPwuserData)
                searchUserResponse!!.enqueue(object : Callback<PostForPwFindUserResponse>{
                    override fun onFailure(call: Call<PostForPwFindUserResponse>, t: Throwable) {
                        Log.v("Error SearchPasswordActivity",t.message)
                    }

                    override fun onResponse(call: Call<PostForPwFindUserResponse>, response: Response<PostForPwFindUserResponse>) {
                        response?.let {
                                when(it.body()!!.status){
                                    201->{
                                        // 이 아래 줄 수정 필요
                                        SharedPreferenceController.instance!!.setPrefData("user_idx",response.body().toString())
                                        Log.v("success",response.headers().toString())
                                        Log.v("exist user",response.message().toString())
                                        startActivity(Intent(this@SearchPasswordActivity,ResetPasswordActivity::class.java))
                                    }
                                    400->{
                                        Log.v("400 error ",response.message())
                                        Log.v("400 error",response.errorBody().toString())
                                        toast("서버에러")
                                    }

                                    404->{
                                        tv_sign_up_overlap.visibility = View.VISIBLE
                                        Log.v("Not Exist User about information",response.message())
                                        Log.v("error",response.errorBody().toString())
                                        toast("Not Exist User about this info")
                                    }

                                    500 ->{
                                        Log.v("server error",response.message())
                                        Log.v("Server error",response.errorBody().toString())
                                        toast("Server error")
                                    }
                                    600 ->{
                                        Log.v("Database Error",response.message())
                                        Log.v("Database Error",response.errorBody().toString())
                                    }
                                    else->{
                                        toast("Error")
                                    }
                                }
                        }
                    }
                })

            }else{
                toast("정보를 정확하게 입력해주시기 바랍니다.")
            }
        }
    }


    fun postUserData(useremail: String, username:String,  userbirth : String){

    }
}

