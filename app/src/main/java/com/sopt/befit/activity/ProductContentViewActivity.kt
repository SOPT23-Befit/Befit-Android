package com.sopt.befit.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.Fragment.SizeCheckLoginDialogFragment
import kotlinx.android.synthetic.main.activity_product_content_view.*
import com.sopt.befit.R


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

    private fun setViewClickListener() {

        //login dialog 창 띄우기
        btn_activity_size_check.setOnClickListener {
            val builder = SizeCheckLoginDialogFragment()
            var fm = supportFragmentManager
            builder.show(fm, "SizeCheckLogin")

        }

    }

}





