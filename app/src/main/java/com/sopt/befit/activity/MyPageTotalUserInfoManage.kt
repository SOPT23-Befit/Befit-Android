package com.sopt.befit.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sopt.befit.R
import com.sopt.befit.data.CombineFormData
import com.sopt.befit.fragment.MypageFragment
import kotlinx.android.synthetic.main.activity_my_page_total_user_info_manage.*
import org.jetbrains.anko.startActivity
import android.content.Intent
import android.graphics.Color
import android.hardware.camera2.TotalCaptureResult
import android.telecom.Call
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostTotalUserDataResponse
import org.jetbrains.anko.networkStatsManager
import org.jetbrains.anko.toast
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class MyPageTotalUserInfoManage : AppCompatActivity() {
    lateinit var postnum : String
    lateinit var sub_postnum: String
    lateinit var home_address : String
    lateinit var detail_address : String
    lateinit var phone : String
    lateinit var networkService: NetworkService
    lateinit var combineFormData: CombineFormData
    lateinit var email : String
    lateinit var password : String
    lateinit var birth : String
    lateinit var name: String
    lateinit var gender : String

    var phoneWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", s)){
                if(et_activity_total_user_detail_address.text.isNotEmpty() && tv_activity_total_user_post_num.text.isNotEmpty()) {
                    btn_activity_total_user_complete.isClickable = true
                    btn_activity_total_user_complete.setTextColor(Color.parseColor("#000000"))
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_total_user_info_manage)

        //intent 하고 setting 하는 작업
//        email = intent!!.getStringExtra("email")
////        name = intent!!.getStringExtra("name")
////        birth = intent!!.getStringExtra("birth")
////        password = intent!!.getStringExtra("password")
////        gender = intent!!.getStringExtra("gender")
////
////
////        tv_activity_total_user_name.setText(name)
////        tv_activity_total_user_pw.setText(password)
////        tv_activity_total_user_email.setText(email)
////        tv_activity_total_user_birth.setText(birth)
////        tv_activity_total_user_gender.setText(gender)




        //뒤로가기 버튼
        btn_activity_total_user_back.setOnClickListener {
            finish()
        }
        ib_activity_post_cancel.visibility = View.GONE

        //완료 버튼 클릭 못하게
        btn_activity_total_user_complete.isClickable = false
        btn_activity_total_user_complete.setTextColor(Color.parseColor("#848484"))

        //상세 주소 못쓰게
        et_activity_total_user_detail_address.isFocusableInTouchMode = false



        //우편번호 찾기 버튼 누르면
        btn_activity_total_user_search_address.setOnClickListener {

            val calIntent = Intent(this, KakaoAddressActivity::class.java)
            startActivityForResult(calIntent, 3000)


        }

        et_activity_user_phone.addTextChangedListener(phoneWatcher)

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 3000){
            when (resultCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                Activity.RESULT_OK-> {

                    tv_activity_total_user_post_num.text = data!!.getStringExtra("postnum")
                    tv_activity_total_user_home_address.text = data!!.getStringExtra("home_address")
                    btn_activity_total_user_search_address.visibility = View.GONE
                    rl_activity_total_user_address.visibility = View.VISIBLE
                    ib_activity_post_cancel.visibility = View.VISIBLE

                     //detail address 칠 수 있게 만들기
                     et_activity_total_user_detail_address.isFocusableInTouchMode = true
                     et_activity_user_phone.addTextChangedListener(phoneWatcher)


                    //주소 검색후 주소창 x 버튼 눌렀을 때
                    ib_activity_post_cancel.setOnClickListener {
                        btn_activity_total_user_search_address.visibility = View.VISIBLE
                        rl_activity_total_user_address.visibility = View.GONE
                        tv_activity_total_user_post_num.text = ""
                        tv_activity_total_user_home_address.text = ""
                    }

                     btn_activity_total_user_complete.setOnClickListener {
                         home_address = tv_activity_total_user_home_address.text.toString()
                         postnum = tv_activity_total_user_post_num.text.toString()
                         sub_postnum = tv_activity_total_user_sub_post_num.text.toString()
                         detail_address = et_activity_total_user_detail_address.text.toString()
                         phone = et_activity_user_phone.text.toString()

                         if(home_address.isNotEmpty() && postnum.isNotEmpty() && detail_address.isNotEmpty() && phone.isNotEmpty()) {
                             TotalUserInfoDataModify(postnum, home_address, detail_address, phone)
                         }else{
                             toast("정보를 모두 입력해 주시기 바랍니다.").show()
                             Log.v("lack info","lack info")
                         }
                     }

                 }
                else->{
                toast("call in Data error")
            }
                }
            }else{
            //result code 가  RESULT_OK 가 아닐 때
            //우편번호 찾기에서 그냥 뒤로 가기 눌렀을 떄
            btn_activity_total_user_search_address.visibility = View.VISIBLE
            rl_activity_total_user_address.visibility = View.GONE

        }



        }

    //통신
    fun TotalUserInfoDataModify(postnum : String,home_address : String,detail_address : String ,phone : String ){
       combineFormData = CombineFormData(postnum,home_address,detail_address,phone)
        networkService = ApplicationController.instance.networkService
        var totaluserdataResponse = networkService.TotalUserDataResponse(combineFormData)
        totaluserdataResponse!!.enqueue(object : Callback<PostTotalUserDataResponse>{
            override fun onFailure(call: retrofit2.Call<PostTotalUserDataResponse>, t: Throwable) {
                Log.v("Error response","omg")
            }

            override fun onResponse(call: retrofit2.Call<PostTotalUserDataResponse>, response: Response<PostTotalUserDataResponse>) {
                response?.let {
                    when(it.body()!!.status){
                        200 ->{
                            Log.v("success",response.message().toString())
                            Log.v("success","ok")
                            finish()
                        }
                        401 ->{
                            Log.v("Authorization fail",response.message().toString())
                            toast("Authorization Fail").show()
                        }
                        500 ->{
                            Log.v("Server Error",response.message().toString())
                            toast("서버 에러").show()
                        }
                        600 ->{
                            Log.v("DB Error",response.message().toString())
                            toast("DB Error").show()
                        }
                        else ->{
                            toast("Error").show()
                        }
                    }
                }?.also {

                }
            }
        })

    }
}

