package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.ToggleButton
import com.sopt.befit.R
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.DeleteAccountResponse
import com.sopt.befit.post.PostBrandLikeResponse
import kotlinx.android.synthetic.main.activity_my_page_account_setting.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Boolean.TRUE

class MyPageAccountSettingActivity : BaseActivity() {
    //val token =SharedPreferenceController.getAuthorization(this)

    var token: String = ""


    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_account_setting)

        token = SharedPreferenceController.getAuthorization(this)

        setSwitchClick()
        logOutOnClick()
        setBtnOnClick()

        btn_activity_my_page_account_setting_back.setOnClickListener() {
            finish()
        }


    }

    fun setSwitchClick() {
        sw_activity_my_page_account_setting_turn.setOnCheckedChangeListener { switch, isChecked ->
            if (isChecked) {

            } else {

                //OFF일 때
                SharedPreferenceController.clearUserSharedPreferences(this)
                startActivity<LogInActivity>()
                // Log.v("auto login off",token)


                //finishAffinity()
            }
        }
    }

    fun logOutOnClick() {
        btn_activity_my_page_account_setting_log_out.setOnClickListener {
            SharedPreferenceController.clearUserSharedPreferences(this)
            startActivity<LogInActivity>()
            finish()
        }
    }

    fun setBtnOnClick() {
        btn_activity_my_page_account_setting_back.setOnClickListener {
            finish()
        }

        btn_my_page_account_setting_delete.setOnClickListener {
            deleteAccountResponse()
            val intent: Intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            SharedPreferenceController.setAuthorization(this, "")
        }
    }

    /*

     */

    private fun deleteAccountResponse() {
        val deleteAccountResponse = networkService.deleteAccountResponse(token)
        deleteAccountResponse.enqueue(object : Callback<DeleteAccountResponse> {
            override fun onFailure(call: Call<DeleteAccountResponse>, t: Throwable) {
                Log.e("jjim brand like fail", t.toString())
            }

            override fun onResponse(call: Call<DeleteAccountResponse>, response: Response<DeleteAccountResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }


}
