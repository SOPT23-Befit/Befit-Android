package com.sopt.befit.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.activity.MainActivity
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.activity.SignUpActivity
import com.sopt.befit.data.ClosetData
import com.sopt.befit.data.LoginData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostLoginResponse
import kotlinx.android.synthetic.main.dl_size_check_login_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class SizeCheckLoginDialogFragment() : DialogFragment(),View.OnClickListener {
    val networkServie: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    val datalist : ArrayList<ClosetData> by lazy {
        ArrayList<ClosetData>()
    }
    var email : String? = null
    var password : String? = null
    lateinit var loginUser : LoginData
    private var overlapNetwork: String = ""
    private  var loginstatus : Int = 0 // 0 이면 비로그인 1이면 로그인

    override  fun  onClick(v : View){
        when(v!!){
            btn_dl_size_check_login -> postLogin()

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?. let {
            email= it.getString("email")
            password =it.getString("password")
            init()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.dl_size_check_login_fragment ,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }




    fun init(){
        btn_dl_size_check_login.setOnClickListener(this)
        btn_dl_size_check_login_sign_up.setOnClickListener{
            val nextIntent = Intent(activity!!,SignUpActivity::class.java)
            startActivity(nextIntent)

        }
        btn_dl_size_check_login_search_email.setOnClickListener {
        }

    }

    private fun postLogin(){
        if(et_dl_size_check_login_email.text.toString().isNotEmpty() && et_dl_size_check_login_pw.text.toString().isNotEmpty()) {
            loginUser = LoginData(et_dl_size_check_login_email.text.toString(),et_dl_size_check_login_pw.text.toString())

            if (overlapNetwork == "") {

                overlapNetwork = "networking"
                val postLoginResponse = networkServie.postLoginResponse(loginUser)
                postLoginResponse.enqueue(object : retrofit2.Callback<PostLoginResponse> {
                    override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                        Log.e("Login 실패", t.toString())
                        overlapNetwork = ""
                    }

                    override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                        response?.let {
                            when (it.code()) {
                                200 -> {
                                    /*
                               * response의 header에 토큰이 담겨서 올 경우 header를 바로 사용할 수 없고,
                               * header를 보고 그 안에서 jwt를 뽑아내야 한다.
                               * 지금의 경우에는 0번째 인덱스에 jwt라는 키값이 있는 걸 확인했고
                               * 그래서 0번째 인덱스의 value를 뽑아내어 jwt 값을 가지고 왔다.
                               * */

                                    Log.v("Login Success", response!!.headers().value(0))
                                    SharedPreferenceController.instance!!.setPrefData("jwt", response!!.headers().value(0))
                                    loginstatus = 1
                                    startActivity<ProductContentViewActivity>("token" to response!!.headers().value(0),"loginstatus" to loginstatus )


                                }
                                500 -> {
                                    toast("정보가 정확하지 않습니다. ")
                                }
                                else -> {
                                    toast("Error")
                                }

                            }


                        }?.also {
                            overlapNetwork = ""
                        }
                    }

                })
            }
        }
        }
    }
