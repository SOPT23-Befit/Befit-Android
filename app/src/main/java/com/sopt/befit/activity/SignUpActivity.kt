package com.sopt.befit.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.sopt.befit.R
import com.sopt.befit.data.UserData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_search_password.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.email
import org.jetbrains.anko.notificationManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    var datepickerStatus = 0//0안보임 1보임
    private lateinit var adapter: ArrayAdapter<String>
    lateinit var networkservice: NetworkService
    lateinit var userData: UserData
    private var overlapNetWorking: String = ""
    private var name : String = ""
    private var birth : String = ""
    private var email : String = ""
    private var password : String =""
    private var passwordcheck : String =""



    //완료 btn 누른 후 통신 로직
    fun postUserCreate(username: String, userpw: String, useremail: String, userbirth: String, usergender: String, userbrand1: Int, userbrand2: Int) {
        //userData에 값 넣기
        userData = UserData(useremail, userpw, usergender, username, userbrand1, userbrand2, userbirth)
        if (overlapNetWorking == "") {
            overlapNetWorking = "networking"
            networkservice = ApplicationController.instance.networkService
            var userCreateResponse = networkservice.postSignUpResponse(userData)
            userCreateResponse!!.enqueue(object : Callback<PostSignUpResponse> {
                override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
                    Log.v("Error LoginActivity : ", t.message)
                    overlapNetWorking = ""
                }

                override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {

                    response?.let {
                        when (it.body()!!.status) {
                            201 -> {
                                Log.v("success", response.message().toString())
                                Log.v("dddddd","aaaaaa")
                                startActivity<LogInActivity>()
                                finish()
                            }
                            400 -> {
                                Log.v("400 error", response.message())
                                Log.v("400 error", response.errorBody().toString())
                                toast("서버 에러")
                            }
                            409 ->{
                                Log.v("409 error",response.message())
                                Log.v("conflict",response.errorBody().toString())
                                toast("충돌 발생")
                            }
                            500 -> {

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


    //비밀번호 형식 실시간 체크
     var pwTextWatcher = object :  TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(et_sign_up_name.text.isNotEmpty() &&  et_sign_up_email.text.isNotEmpty() && et_sign_up_password.text.isNotEmpty() && et_sign_up_password_check.text.isNotEmpty()){
                Log.v("pw1","ssssss")
                btn_sign_up_next_page.setImageResource(R.drawable.ic_purplearrow)
                btn_sign_up_next_page.isClickable =  true
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", s)) {
                tv_sign_up_notice.setTextColor(Color.parseColor("#7a36e4"))
                tv_sign_up_notice.text = "특수문자,영문,숫자를 포함해주세요"
            }else{
                tv_sign_up_notice.text = "유효한 비밀번호 입니다."
            }
        }

    }

    //비번 과 재비번 이 같은지 실시간 체크
    var repwTextWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            if(et_sign_up_name.text.isNotEmpty() &&  et_sign_up_email.text.isNotEmpty() && et_sign_up_password.text.isNotEmpty() && et_sign_up_password_check.text.isNotEmpty()){
                Log.v("repw1","ssssss")
                btn_sign_up_next_page.setImageResource(R.drawable.ic_purplearrow)
                btn_sign_up_next_page.isClickable =  true
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            tv_sign_up_check.visibility = View.VISIBLE
            if(et_sign_up_password.text.toString().equals(et_sign_up_password_check.text.toString())){
                tv_sign_up_check.text = "비밀번호 확인이 일치합니다."
                if(et_sign_up_name.text.isNotEmpty() &&  et_sign_up_email.text.isNotEmpty() && et_sign_up_password.text.isNotEmpty() && et_sign_up_password_check.text.isNotEmpty()){
                    Log.v("repw1","ssssss")
                    btn_sign_up_next_page.setImageResource(R.drawable.ic_purplearrow)
                    btn_sign_up_next_page.isClickable =  true
                }
            }else{

            }
        }
    }

    var emailWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            if(et_sign_up_name.text.isNotEmpty() &&  et_sign_up_email.text.isNotEmpty() && et_sign_up_password.text.isNotEmpty() && et_sign_up_password_check.text.isNotEmpty()){
                Log.v("email1","ssss")
                btn_sign_up_next_page.setImageResource(R.drawable.ic_purplearrow)
                btn_sign_up_next_page.isClickable =  true
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            tv_activity_sign_up_check_email.visibility = View.VISIBLE
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                tv_activity_sign_up_check_email.text= "유효한 이메일 입니다."
            }
        }
    }

    var nameWatcher =  object  : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            if(et_sign_up_name.text.isNotEmpty() &&  et_sign_up_email.text.isNotEmpty() && et_sign_up_password.text.isNotEmpty() && et_sign_up_password_check.text.isNotEmpty()){
                    Log.v("name1","sssss")
                btn_sign_up_next_page.setImageResource(R.drawable.ic_purplearrow)
                btn_sign_up_next_page.isClickable =  true
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        tv_sign_up_check.visibility = View.INVISIBLE
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)

        btn_sign_up_select_bithday.setOnClickListener() {
            if (datepickerStatus == 0) {
                datepickerStatus = 1
                lo_sign_up_date_picker.visibility=View.VISIBLE
                iv_sign_up_background.visibility=View.VISIBLE

                lo_sign_up_total.visibility=View.GONE
            }


        }

        btn_sign_up_select_confirm.setOnClickListener() {
            if (datepickerStatus == 1) {
                datepickerStatus = 0
                lo_sign_up_date_picker.visibility=View.GONE
                iv_sign_up_background.visibility=View.GONE
                lo_sign_up_total.visibility = View.VISIBLE
            }
        }
        val dateChangeListener = DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            tv_sign_up_select_year.text = String.format(

                    Locale.KOREA, "%d",
                    year
            )
            tv_sign_up_select_month.text = String.format(
                    Locale.KOREA, "%d",
                    monthOfYear + 1
            )
            tv_sign_up_select_day.text = String.format(
                    Locale.KOREA, "%d",
                    dayOfMonth

            )
        }

        date_picker.init(
                date_picker.year,
                date_picker.month,
                date_picker.dayOfMonth,
                dateChangeListener)


        //실시간 비밀번호 유효성 검사
        et_sign_up_name.addTextChangedListener(nameWatcher)
        et_sign_up_password.addTextChangedListener(pwTextWatcher)
        et_sign_up_password_check.addTextChangedListener(repwTextWatcher)
        et_sign_up_email.addTextChangedListener(emailWatcher)


        btn_sign_up_next_page.setOnClickListener {
            possible_btn()
        }


        ibtn_sign_up_back.setOnClickListener{
            finish()
        }
    }



    fun possible_btn(){
        var name = et_sign_up_name.text.toString()
        var password = et_sign_up_password.text.toString()
        var passwordcheck = et_sign_up_password_check.text.toString()
        var email = et_sign_up_email.text.toString()
        var gender = "남성"
        var brand1 = "1"
        var brand2=  "2"
       // var gender = intent!!.getStringExtra("gender")
        //var brand1 = intent!!.getStringExtra("brand1")
        //var brand2 = intent!!.getStringExtra("brand2")
        var month: String = ""
        var day: String = ""
        month = tv_sign_up_select_month.text.toString()
        day = tv_sign_up_select_day.text.toString()
        if (tv_sign_up_select_month.text.length == 1) {
            Log.v("asasss",month)
            month = "0" + tv_sign_up_select_month.text
        }
        if (tv_sign_up_select_day.text.length == 1) {
            day = "0" + tv_sign_up_select_day.text
        }
        val birth = tv_sign_up_select_year.text.toString() + "/" + month + "/" + day

        //무조건 통신
        if (password.equals(passwordcheck)) {

            if (name.length>0 && birth.length >0 && email.length > 0 && password.length > 0 && passwordcheck.length > 0) { //서로 같은지

                Log.v("zzzz",gender)
                Log.v("zzzz",brand1)
                Log.v("zzzz",brand2)
                postUserCreate(name, password, email, birth, gender, brand1.toInt(), brand2.toInt())


            } else {
                toast("정보를 다 입력해주세요 ").show()
            }
            //유효성 검사
        }else{
            toast("비밀번호가 일치 하지 않습니다").show()
        }

    }
}


