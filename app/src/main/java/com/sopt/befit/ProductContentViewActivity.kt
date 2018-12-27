package com.sopt.befit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.befit.Fragment.SizeCheckLoginDialogFragment
import com.sopt.befit.R.layout.dl_size_check_login_fragment
import kotlinx.android.synthetic.main.dl_size_check_login_fragment.*
import org.json.JSONObject

class ProductContentViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_content_view)

        val sizecheckDialog : DialogFragment = SizeCheckLoginDialogFragment()
        sizecheckDialog.show(supportFragmentManager,"loginDialog")
        btn_dl_size_check_login.setOnClickListener {
            getLoginResponse()
        }
    }

    private fun getLoginResponse(){
        if(et_dl_size_check_login_email.text.toString().isNotEmpty()&&et_dl_size_check_login_pw.text.toString().isNotEmpty()){
            val dl_input_email = et_dl_size_check_login_email.text.toString()
            val dl_input_pw = et_dl_size_check_login_pw.text.toString()
            val jsonObject: JSONObject = JSONObject()

            jsonObject.put("email",dl_input_email)
            jsonObject.put("password",dl_input_pw)
            val gsonObject: JsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        }

    }

}
