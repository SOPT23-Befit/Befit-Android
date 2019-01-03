package com.sopt.befit.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.data.ModifyPWData
import com.sopt.befit.network.NetworkService
import com.sopt.befit.put.PutModifyPwResponse
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ResetPasswordActivity : AppCompatActivity() {
    //변수 선언
    val REQUEST_CODE_SEARCH_PW_ACTIVITY = 5555
    lateinit var modifypwData : ModifyPWData
    lateinit var networkservice : NetworkService
    private var overlapNetWorking : String =""
    val BACK_CODE_LOGIN_ACTIVITY = 7777
    private  var userIdx: Int = -1



    fun putPasswordModify(userIdx : Int, userpw: String){
        //modifypwdata에 값 넣기
        modifypwData = ModifyPWData(userIdx,userpw)
        if(overlapNetWorking == ""){
            overlapNetWorking= "networking"

            var modifypwResponse= networkservice.putModifyPWResponse(modifypwData)
            modifypwResponse!!.enqueue(object : Callback<PutModifyPwResponse>{
                override fun onFailure(call: Call<PutModifyPwResponse>, t: Throwable) {
                    Log.v("Error ModifyActivity",t.message)
                    overlapNetWorking= ""
                }
                override fun onResponse(call: Call<PutModifyPwResponse>, response: Response<PutModifyPwResponse>) {
                    response?.let {
                        when(it.body()!!.status){
                            201 ->{
                                Log.v("success",response.message().toString())
                                startActivity(Intent(this@ResetPasswordActivity,LogInActivity::class.java))
                                finish()
                            }
                            400 ->{
                                Log.v("Fail",response.message())

                            }
                            404 ->{
                                    Log.v("Not found user",response.message())
                            }
                            204 ->{
                                Log.v("회원 수정 정보가 잘못되었습니다.",response.message())

                            }
                            500 ->{
                                Log.v("서버 내부 에러",response.message())
                            }
                            600 -> {
                                    Log.v("DB 에러",response.message())
                            }else -> {
                            toast("Error")
                        }
                        }
                    }?.also {
                        overlapNetWorking = ""
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
            tv_check_new_pw.visibility = View.VISIBLE
            if(et_activity_reset_pw_input_new_pw_1.equals(et_activity_reset_pw_input_new_pw_2)){
                tv_check_new_pw.setTextColor((ContextCompat.getColor(this@ResetPasswordActivity,R.color.colorAccent)))
                tv_check_new_pw.text = "일치"
            }else{
                tv_check_new_pw.setTextColor(Color.parseColor("#7a36e4"))
                tv_check_new_pw.text = "불일치"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reset_password)
        tv_check_new_pw.visibility = View.INVISIBLE
        et_activity_reset_pw_input_new_pw_2.addTextChangedListener(pwTextWatcher)
        btn_activity_reset_pw_ok.setOnClickListener {
            //버튼을 눌렀을 때
            val rePW = et_activity_reset_pw_input_new_pw_1.text.toString()
            val rePWcheck = et_activity_reset_pw_input_new_pw_2.text.toString()

            if(rePW.length > 0 &&rePWcheck.length> 0){
                if(Pattern.matches("^(?=.*\\d)(?=.*[.!@#$%])(?=.*[a-zA-Z]).{8,20}$", rePW)){
                    if(rePW.equals(rePWcheck)){
                        putPasswordModify(userIdx ,rePW)
                    }else{
                        toast("두 입력이 일치하지 않습니다.")
                    }
                }else{
                    toast("비밀번호 형식이 유효하지 않습니다.")
                }
            }else{
                toast("정보를 정확히 입력해주세요")
            }

        }
        //x버튼 눌렀을 때
        btn_activity_reset_pw_back.setOnClickListener {
            startActivityForResult<LogInActivity>(BACK_CODE_LOGIN_ACTIVITY,"message" to "return to Login")
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SEARCH_PW_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                userIdx = data!!.getIntExtra("userIndex",-1)

            }else{
                toast("Not found user Idx")
            }
        }
    }
}
