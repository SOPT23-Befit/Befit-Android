package com.sopt.befit.activity

import android.app.Activity
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
import com.sopt.befit.R
import com.sopt.befit.data.ModifyPWData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.put.PutModifyPwResponse
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.jetbrains.anko.startActivity
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

            networkservice = ApplicationController.instance.networkService
            var modifypwResponse= networkservice.putModifyPWResponse(modifypwData)
            modifypwResponse!!.enqueue(object : Callback<PutModifyPwResponse>{
                override fun onFailure(call: Call<PutModifyPwResponse>, t: Throwable) {
                    Log.v("Error ModifyActivity",t.message)
                    overlapNetWorking= ""
                }
                override fun onResponse(call: Call<PutModifyPwResponse>, response: Response<PutModifyPwResponse>) {
                        if (response.isSuccessful) {
                            Log.v("aaaa",response.body()!!.status.toString())
                            when (response.body()!!.status) {
                                200 -> {
                                    Log.v("success", response.body().toString())
                                    toast("성공적으로 수정되었습니다.").show()
                                    startActivity<LogInActivity>()
                                }
                                204 -> {
                                    Log.v("회원 수정 정보가 잘못되었습니다.", response.body().toString())
                                    toast("회원 수정 정보가 잘못되었습니다.").show()
                                }
                                500 -> {

                                    Log.v("서버 내부 에러", response.body().toString())
                                    toast("서버 에러 .").show()
                                }
                                600 -> {


                                    Log.v("DB 에러", response.body().toString())
                                    toast("디비에러 .").show()
                                }
                                else -> {
                                    Log.v("zzz","zz")
                                    toast("Error").show()
                                }
                            }
                        }


                }
            })
        }
    }
//비밀번호 랑 확인이랑 일치하는지 확인하기
    var repwTextWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            tv_activity_reset_pw_compare_check.visibility = View.VISIBLE
            if(et_activity_reset_pw_input_new_pw_1.text.toString().equals(et_activity_reset_pw_input_new_pw_2.text.toString())){
                tv_activity_reset_pw_compare_check.text = "일치합니다"
            }else{

            }
        }
    }

    //비밀번호 형식 일치하는지 확인
    var pwTextWatcher = object :  TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", s)) {
                tv_reset_pw_notice.setTextColor(Color.parseColor("#7a36e4"))
                tv_reset_pw_notice.text = "특수문자,영문,숫자를 포함해주세요"
            }else{
                tv_reset_pw_notice.text = "유효한 비밀번호 입니다."
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reset_password)
        tv_activity_reset_pw_compare_check.visibility = View.INVISIBLE

        et_activity_reset_pw_input_new_pw_1.addTextChangedListener(pwTextWatcher)
        et_activity_reset_pw_input_new_pw_2.addTextChangedListener(repwTextWatcher)
        userIdx = SharedPreferenceController.getUserIDX(this)
        btn_activity_reset_pw_ok.setOnClickListener {
            //버튼을 눌렀을 때
            val rePW = et_activity_reset_pw_input_new_pw_1.text.toString()
            val rePWcheck = et_activity_reset_pw_input_new_pw_2.text.toString()

            if(rePW.length > 0 &&rePWcheck.length> 0){
                if(Pattern.matches("^(?=.*\\d)(?=.*[.!@#$%])(?=.*[a-zA-Z]).{8,20}$", rePW)){
                    if(rePW.equals(rePWcheck)){
                        SharedPreferenceController.setUserPW(this,rePW)
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
            startActivityForResult<SearchPasswordActivity>(BACK_CODE_LOGIN_ACTIVITY,"message" to "return to searchpw")
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
