package com.sopt.befit.activity

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast

import com.sopt.befit.R
import com.sopt.befit.data.ForPwUserData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostForPwFindUserResponse
import com.sopt.befit.post.UserIdxData
import kotlinx.android.synthetic.main.activity_search_password.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.networkStatsManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchPasswordActivity : BaseActivity() {
        lateinit var ForPwuserData : ForPwUserData
        lateinit var networkservice : NetworkService






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_password)
        tv_activity_overlap_user.visibility = View.INVISIBLE

        btn_activity_search_pw_ok.setOnClickListener {
            val intent: Intent = Intent()
            var month: String = ""
            var day: String = ""
            val name = et_activity_search_pw_name.text.toString()
            val email = et_activity_search_pw_email.text.toString()
            month = tv_search_pw_select_month.text.toString()
            day = tv_search_pw_select_day.text.toString()
                if (tv_search_pw_select_month.text.toString().length == 1) {
                    month = "0" + tv_search_pw_select_month.text
                }
            if (tv_search_pw_select_day.text.toString().length == 1) {
                day = "0" + tv_search_pw_select_day.text
            }
            val birthday = tv_search_pw_select_year.text.toString() + "/" + month + "/" + day
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (name.length > 0 && email.length > 0 && birthday.length > 0) {
                    //회원 정보 있는지 통신하기

                    ForPwuserData = ForPwUserData(email, name, birthday)
                    Log.d("jonmin", ForPwuserData.toString())
                    networkservice = ApplicationController.instance.networkService
                    var searchUserResponse = networkservice.ForPwUserDataResponse("application/json", ForPwuserData)
                    searchUserResponse!!.enqueue(object : Callback<PostForPwFindUserResponse> {
                        override fun onFailure(call: Call<PostForPwFindUserResponse>, t: Throwable) {
                            Log.v("Error SearchPasswordActivity", t.message)
                        }

                        override fun onResponse(call: Call<PostForPwFindUserResponse>, response: Response<PostForPwFindUserResponse>) {
                            Log.d("aaa","ttt")
                                if (response.isSuccessful) {
                                    Log.v("ForPwuserData",ForPwuserData.toString())
                                    when (response.body()!!.status) {
                                        200 -> {

                                            var user_idx = response.body()!!.data.idx

                                            SharedPreferenceController.setUserIDX(this@SearchPasswordActivity, user_idx)
                                            Log.v("success", response.body().toString())
                                            startActivity(Intent(this@SearchPasswordActivity, ResetPasswordActivity::class.java))
                                        }
                                        400 -> {

                                            Log.v("400 error ", response.body().toString())
                                            Toast.makeText(this@SearchPasswordActivity,response.body()!!.message,Toast.LENGTH_LONG).show()
                                        }

                                        404 -> {
                                            tv_activity_overlap_user.visibility = View.VISIBLE
                                            Log.v("Not Exist User about information", response.body().toString())
                                            Log.v("communication success", response.body().toString())
                                            toast("Not Exist User about this info")
                                        }

                                        500 -> {
                                            Log.v("server error", response.body().toString())
                                            Log.v("Server error", response.body().toString())
                                            toast("Server error")
                                        }
                                        600 -> {
                                            Log.v("Database Error", response.body().toString())
                                            Log.v("Database Error", response.body().toString())
                                        }
                                        else -> {
                                            toast("Error")
                                        }
                                    }
                                } else {
                                    Log.d("aaa","bbbbhvjhvj")
                                }
                            }
                    })

                } else {
                    toast("정보를 정확하게 입력해주시기 바랍니다.")
                }
        }else{
                toast("유효한 이메일 형식을 입력해주시기 바랍니다.")
            }
        }

        btn_activity_search_pw_bithday.setOnClickListener(){
            lo_search_pw_date_picker_total.visibility=View.VISIBLE
            iv_search_background.visibility=View.VISIBLE

        }
        btn_search_pw_date_picker_ok.setOnClickListener(){
            lo_search_pw_date_picker_total.visibility=View.GONE

            iv_search_background.visibility=View.GONE

        }



        val dateChangeListener = DatePicker.OnDateChangedListener{
            view, year, monthOfYear, dayOfMonth ->
            tv_search_pw_select_year.text = String.format(
                    Locale.KOREA, "%d",
                    year
            )
            tv_search_pw_select_month.text = String.format(
                    Locale.KOREA, "%d",
                    monthOfYear + 1
            )
            tv_search_pw_select_day.text = String.format(
                    Locale.KOREA, "%d",
                    dayOfMonth
            )
        }

        date_picker_search_pw.init(
                date_picker_search_pw.year,
                date_picker_search_pw.month,
                date_picker_search_pw.dayOfMonth,
                dateChangeListener)

    }



}

