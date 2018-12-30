package com.sopt.befit.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.sopt.befit.R
import com.sopt.befit.data.UserData
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    val c = GregorianCalendar.getInstance()
    var mYear: Int = c.get(Calendar.YEAR)
    var mMonth: Int = c.get(Calendar.MONTH)
    var mDay: Int = c.get(Calendar.DAY_OF_MONTH)

    lateinit var networkservice : NetworkService
    lateinit var userData: UserData

    override fun onClick(v: View?) {
        when(v!!){
            btn_sign_up_next_page ->{
                var name = et_sign_up_name.text.toString()
                var password = et_sign_up_password.text.toString()
                var passwordcheck  = et_sign_up_password_check.text.toString()
                var email = et_sign_up_email.text.toString()
                var birth = tv_sign_up_select_year.text.toString() + tv_sign_up_select_month.text.toString() + tv_sign_up_select_day.text.toString()
                if(email.length > 0 && password.length > 0 && passwordcheck.length > 0 && name.length > 0){
                   if(Pattern.matches("^(?=.*\\d)(?=.*[.!@#$%])(?=.*[a-zA-Z]).{8,20}$", password)){ //pw 유효성 검사
                            if(password.equals(passwordcheck)){ //서로 같은지
                                postUserCreate(name,password,email,birth)
                            }else{
                                toast("비밀번호 확인과 비밀번호가 일치하지 않습니다.")
                            }
                   }else{
                       toast("비밀번호 형식이 유효하지 않습니다.")
                   }
                    //유효성 검사
                }
            }
        }
    }
    fun init(){
        btn_sign_up_next_page.setOnClickListener(this)
        networkservice = ApplicationController.instance.networkService
    }
    fun postUserCreate(username: String, userpw: String, useremail:String, userbirth: String){
        
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //생년월일 선택 -> calender dialog 띄우기
        btn_sign_up_select_bithday.setOnClickListener {
            createDialog()!!.show()
        }

    }




    private fun createDialog(): Dialog?{
        return DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay)
    }
    var mDateSetListener = DatePickerDialog.OnDateSetListener{
        view, year, month, dayOfMonth ->  Toast.makeText(
            applicationContext,
            "날짜: $year-$month-$dayOfMonth",
            Toast.LENGTH_SHORT
    ).show()
    }
    private fun setIninText(){
        val name : String? = intent.getStringExtra("name")
        val email : String? = intent.getStringExtra("email")
        val comparevalue : Boolean
        if(name != null){
            et_sign_up_name.setText(name)
        }
        if(email != null && email.length>= 6 && email.length<=12){
            et_sign_up_email.setText(email)


        }
}
    private fun setOnClickListener(){
        btn_sign_up_next_page.setOnClickListener {
            val intent : Intent = Intent()
            intent.putExtra("email",et_sign_up_email.text.toString())
            intent.putExtra("password",et_sign_up_password.text.toString())
            intent.putExtra("name",et_sign_up_name.text.toString())
            intent.putExtra("passwordcheck",et_sign_up_password_check.text.toString())

        }
    }
}
