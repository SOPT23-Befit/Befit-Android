package com.sopt.befit.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.*
import com.sopt.befit.R
import com.sopt.befit.data.UserData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.activity_search_password.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var adapter : ArrayAdapter<String>
    lateinit var networkservice : NetworkService
    lateinit var userData: UserData
    private var overlapNetWorking : String = ""


    override fun onClick(v: View?) {
        when(v!!){
            btn_activity_search_pw_bithday->{
                //DatePicker.OnDateChangedListener{
                   // txtDate.text = String.format(
                           // Locale.K
                    //)
              //  }

            }
            btn_sign_up_next_page ->{
                val intent = Intent(this,SelectBrandActivity::class.java)

                var name = et_sign_up_name.text.toString()
                var password = et_sign_up_password.text.toString()
                var passwordcheck  = et_sign_up_password_check.text.toString()
                var email = et_sign_up_email.text.toString()
                var gender : String = intent.getStringExtra("gender")
                var brand1 : Int = intent.getIntExtra("brand1",0)
                var brand2 : Int = intent.getIntExtra("brand2",1)

                val year = spinner_sign_up_select_year.selectedItem as String
                val month = spinner_sign_up_select_month.selectedItem as String
                val day = spinner_sign_up_select_day.selectedItem as String
                val birth = year + month + day


                if(email.length > 0 && password.length > 0 && passwordcheck.length > 0 && name.length > 0){
                   if(Pattern.matches("^(?=.*\\d)(?=.*[.!@#$%])(?=.*[a-zA-Z]).{8,20}$", password)){ //pw 유효성 검사
                            if(password.equals(passwordcheck)){ //서로 같은지
                                postUserCreate(name,password,email,birth,gender,brand1,brand2)

                            }else{
                                toast("비밀번호 확인과 비밀번호가 일치하지 않습니다.")
                                tv_sign_up_overlap.visibility = View.VISIBLE
                            }
                   }else{
                       toast("비밀번호 형식이 유효하지 않습니다.")
                   }
                    //유효성 검사
                }else{
                    toast("정보를 정확히 입력해주세요")
                }
            }
            ibtn_sign_up_back->{
                //그 전 activity 로 돌아가기
                finish()
            }
        }
    }


    var mDateSetListener = DatePickerDialog.OnDateSetListener {
        view, year, month, dayOfMonth ->

    }


    fun init(){
        btn_sign_up_next_page.setOnClickListener(this)
        networkservice = ApplicationController.instance.networkService
        SharedPreferenceController.instance!!.load(this)



    }
    fun postUserCreate(username: String, userpw: String, useremail:String, userbirth: String,usergender : String, userbrand1 : Int, userbrand2 : Int){
            //userData에 값 넣기
            userData = UserData(useremail, userpw, usergender,username, userbrand1,userbrand2,userbirth)
           if(overlapNetWorking == ""){
               overlapNetWorking = "networking"

               var userCreateResponse = networkservice.postSignUpResponse(userData)
               userCreateResponse!!.enqueue(object: Callback<PostSignUpResponse> {
                   override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable){
                       Log.v("Error LoginActivity : ", t.message)
                       overlapNetWorking = ""
                   }

                   override fun onResponse(call : Call<PostSignUpResponse>, response: Response<PostSignUpResponse>){

                       response?.let {
                           when(it.body()!!.status){
                               201 ->{
                                   SharedPreferenceController.instance!!.setPrefData("jwt",response.headers().value(0))
                                   Log.v("success",response.headers().toString())
                                   Log.v("success",response.message().toString())
                               startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                                   finish()
                               }
                               400 ->{
                                   Log.v("400 error",response.message())
                                   Log.v("400 error", response.errorBody().toString())
                                   toast("서버 에러")
                               }
                               401 ->{

                               }
                               500 ->{

                               }
                               else -> {
                                   toast("Error")
                               }
                           }
                       }?.also {
                           overlapNetWorking = " "
                       }
                   }
               })
           }
    }

    var pwTextWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            tv_sign_up_overlap.visibility = View.VISIBLE
            if(et_sign_up_password.equals(et_sign_up_password_check)){
                tv_check_new_pw.setTextColor((ContextCompat.getColor(this@SignUpActivity,R.color.colorAccent)))
                tv_check_new_pw.text = "일치"
            }else{
                tv_check_new_pw.setTextColor(Color.parseColor("#7a36e4"))
                tv_check_new_pw.text = "불일치"
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tv_sign_up_overlap.visibility = View.INVISIBLE
        et_sign_up_password_check.addTextChangedListener(pwTextWatcher)
        adapter = ArrayAdapter(this@SignUpActivity, android.R.layout.simple_spinner_item)

        init()

    }




}
