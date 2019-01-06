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
import android.hardware.camera2.TotalCaptureResult
import android.telecom.Call
import android.util.Log
import android.widget.Toast
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

    var name = intent.getStringExtra("name")
    var birth = intent.getStringExtra()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_total_user_info_manage)


        btn_activity_total_user_back.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }
        //완료 버튼 클릭 못하게
        btn_activity_total_user_complete.isClickable = false
        //상세 주소 못쓰게
        et_activity_total_user_detail_address.isFocusableInTouchMode = false

        btn_activity_total_user_search_address.setOnClickListener {
            //우편번호 찾기 버튼 누르면
            val calIntent = Intent(this, KakaoAddressActivity::class.java)
            startActivityForResult(calIntent, 3000)

            btn_activity_total_user_search_address.visibility = View.INVISIBLE
            rl_activity_total_user_address.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                 3000-> {
                     //detail address 칠 수 있게 만들기

                     if(postnum.length > 0 ){
                         et_activity_total_user_detail_address.isFocusableInTouchMode = true
                         btn_activity_total_user_search_address.visibility = View.INVISIBLE
                         rl_activity_total_user_address.visibility =  View.VISIBLE

                     tv_activity_total_user_post_num.setText(data!!.getStringExtra("post_num"))
                     tv_activity_total_user_sub_post_num.setText(data!!.getStringExtra("sub_post_num"))
                     tv_activity_total_user_home_address.setText(data!!.getStringExtra("home_address"))
                     et_activity_total_user_detail_address.setText(data!!.getStringExtra("detail_address"))
                     et_activity_user_phone.setText(data!!.getStringExtra("phone"))

                     postnum = tv_activity_total_user_post_num.text.toString()
                     sub_postnum = tv_activity_total_user_sub_post_num.text.toString()
                     home_address = tv_activity_total_user_home_address.text.toString()
                     detail_address = et_activity_total_user_detail_address.text.toString()
                     phone = et_activity_user_phone.text.toString()


                         if (home_address.length > 0) {
                             if (detail_address.length > 0) {
                                 if (!Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", phone)) {
                                     toast("올바른 핸드폰 번호가 아닙니다.")

                                 } else {
                                     //모든 정보가 올바를 때

                                     btn_activity_total_user_complete.isClickable = true
                                     btn_activity_total_user_complete.setOnClickListener {
                                         //서버 쪽에 통신
                                        TotalUserInfoDataModify(postnum,sub_postnum,home_address,sub_postnum,phone)

                                     }

                                 }


                             } else {
                                 toast("상세 주소를 입력해주시기 바랍니다.")
                             }
                         } else {

                         }
                 }  else {
                         //우편 번호에서 그냥 돌아올 때 버튼 그대로 남기기
                         btn_activity_total_user_search_address.visibility = View.VISIBLE
                         rl_activity_total_user_address.visibility = View.INVISIBLE
                     }
                 }


                else->{
                toast("call in Data error")
            }
                }
            }



        }


    fun TotalUserInfoDataModify(postnum : String?, sub_postnum : String?,home_address : String?,detail_address : String? ,phone : String? ){
       combineFormData = CombineFormData(postnum,home_address,detail_address,phone)
        var totaluserdataResponse = networkService.TotalUserDataResponse(combineFormData)
        totaluserdataResponse!!.enqueue(object : Callback<PostTotalUserDataResponse>{
            override fun onFailure(call: retrofit2.Call<PostTotalUserDataResponse>, t: Throwable) {
                Log.v("Error response",t.message)
            }

            override fun onResponse(call: retrofit2.Call<PostTotalUserDataResponse>, response: Response<PostTotalUserDataResponse>) {
                response?.let {
                    when(it.body()!!.status){
                        201 ->{ //api에는200으로 되어있어서 수정 바란다 말할것
                            Log.v("success",response.message().toString())
                            finish()
                        }
                        204 ->{
                            Log.v("Fail",response.message().toString())
                                toast("회원 수정 정보가 잘못되었습니다.")
                        }
                        401 ->{
                            Log.v("Authorization fail",response.message().toString())
                            toast("Authorization Fail")
                        }
                        500 ->{
                            Log.v("Server Error",response.message().toString())
                            toast("서버 에러")
                        }
                        600 ->{
                            Log.v("DB Error",response.message().toString())
                            toast("DB Error")
                        }
                        else ->{
                            toast("Error")
                        }
                    }
                }?.also {

                }
            }
        })

    }
}

