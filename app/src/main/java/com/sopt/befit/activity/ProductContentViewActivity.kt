package com.sopt.befit.activity


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import com.sopt.befit.fragment.CompareSizeDialog

import kotlinx.android.synthetic.main.activity_product_content_view.*
import com.sopt.befit.R
import com.sopt.befit.data.ClosetData
import com.sopt.befit.fragment.SizeCheckAddClothDialog


class ProductContentViewActivity(val ctx: Context) : AppCompatActivity() {


    val BACK_CODE_PRODUCT_CONTENT_ACTIVITY = 7777
    var closetlist: ArrayList<ClosetData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_content_view)


        btn_activity_product_contentview_cancel.setOnClickListener {
            finish()
        }
        val fm: FragmentManager = supportFragmentManager


        // 옷정보가 없다면
        val sizecheckDialog: DialogFragment = SizeCheckAddClothDialog()
        //옷정보가 있다면
        val compareSizeDialog : DialogFragment = CompareSizeDialog()

        if (closetlist.isEmpty()) {

            btn_activity_product_contentview_size_check.setOnClickListener {
                sizecheckDialog.show(fm, "Can't compare with anything")
            }
        } else {
            btn_activity_product_contentview_size_check.setOnClickListener {
                //옷정보가 있을 때 사이즈비교 다이얼로그 띄우기
                compareSizeDialog.show(supportFragmentManager,"compare size")

            }
        }

//
//        btn_dl_size_check_login.setOnClickListener {
    }


}




