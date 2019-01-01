package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.befit.R
import com.sopt.befit.data.ForPwUserData
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostForPwFindUserResponse
import kotlinx.android.synthetic.main.activity_search_password.*
import org.jetbrains.anko.networkStatsManager
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
                                //slack 에 서버 한테 post인데 status 200 이니까 물어보기 
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

