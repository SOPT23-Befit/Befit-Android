package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.ViewAnimator
import com.sopt.befit.R
import com.sopt.befit.data.ForPwUserData
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostForPwFindUserResponse
import kotlinx.android.synthetic.main.activity_search_password.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.networkStatsManager
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

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
            val birthday = tv_activity_search_pw_year.text.toString()
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

        btn_activity_search_pw_bithday.setOnClickListener(){
            lo_search_pw_total.visibility= View.INVISIBLE
            date_picker_search_pw.visibility=View.VISIBLE
            btn_search_pw_date_picker_ok.visibility=View.VISIBLE
        }
        btn_search_pw_date_picker_ok.setOnClickListener(){
            lo_search_pw_total.visibility= View.VISIBLE
            date_picker_search_pw.visibility=View.INVISIBLE
            btn_search_pw_date_picker_ok.visibility=View.INVISIBLE
        }



        val dateChangeListener = DatePicker.OnDateChangedListener{
            view, year, monthOfYear, dayOfMonth ->
            tv_activity_search_pw_year.text = String.format(
                    Locale.KOREA,"%d년 %d월 %d일",
                    year,monthOfYear+1,dayOfMonth
            )
        }

        date_picker.init(
                date_picker_search_pw.year,
                date_picker_search_pw.month,
                date_picker_search_pw.dayOfMonth,
                dateChangeListener)

    }


    fun postUserData(useremail: String, username:String,  userbirth : String){

    }
}

