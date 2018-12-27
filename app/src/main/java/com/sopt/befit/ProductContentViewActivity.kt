package com.sopt.befit

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.befit.Fragment.SizeCheckLoginDialogFragment
import com.sopt.befit.R.layout.dl_size_check_login_fragment
import com.sopt.befit.data.ClosetData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostLoginResponse
import kotlinx.android.synthetic.main.activity_product_content_view.*
import kotlinx.android.synthetic.main.dl_size_check_login_fragment.*
import kotlinx.android.synthetic.main.dl_size_check_no_compare_product.*
import org.jetbrains.anko.db.NULL
import org.jetbrains.anko.networkStatsManager
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class ProductContentViewActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_content_view)

        setViewClickListener()
        //윤환이형 답
//        val sizecheckloginDialog : DialogFragment = SizeCheckLoginDialogFragment()
//        sizecheckloginDialog.show(supportFragmentManager,"loginDialog")
//        btn_dl_size_check_login.setOnClickListener {
        }
        private fun setViewClickListener(){

            //login dialog 창 띄우기
            btn_activity_size_check.setOnClickListener {
                val builder = SizeCheckLoginDialogFragment()
                var fm = supportFragmentManager
                builder.show(fm,"SizeCheckLogin")

            }

        }

    }





