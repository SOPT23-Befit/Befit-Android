package com.sopt.befit.Fragment

import android.app.AlertDialog
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
import com.sopt.befit.data.ClosetData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostLoginResponse
import kotlinx.android.synthetic.main.dl_size_check_login_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class SizeCheckLoginDialogFragment() : DialogFragment() {
    val networkServie: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    val datalist : ArrayList<ClosetData> by lazy {
        ArrayList<ClosetData>()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.dl_size_check_login_fragment ,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun getLoginResponse(){
        if(et_dl_size_check_login_email.text.toString().isNotEmpty() && et_dl_size_check_login_pw.text.toString().isNotEmpty()){
            val dl_input_email = et_dl_size_check_login_email.text.toString()
            val dl_input_pw = et_dl_size_check_login_pw.text.toString()
            val jsonObject: JSONObject = JSONObject()

            jsonObject.put("email",dl_input_email)
            jsonObject.put("password",dl_input_pw)
            val gsonObject : JsonObject= JsonParser().parse(jsonObject.toString()) as JsonObject

            val postLoginResponse = networkServie.postLoginResponse("application/json",gsonObject)
            postLoginResponse.enqueue(object:retrofit2.Callback<PostLoginResponse>{
                override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                    Log.e("Login 실패",t.toString())
                }

                override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                    if(response.isSuccessful){
                        val token = response.body()!!.data.token
                        SharedPreferenceController.setAuthorization(activity!!, token)
                        toast(SharedPreferenceController.getAuthorization(activity!!))
                        //1.등록 옷 정보가 있으면 옷 비교 dialog로
                        //2.등록 옷 정보가 없으면 옷 추가 dialog로
                        if(datalist.size == 0) { //등록 옷 정보가 없다면
                            //startActivity<>()
                        }
                    }
                }
            })
        }
    }
}