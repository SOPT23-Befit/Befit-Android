package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.activity.MySizeLookupActivity
import com.sopt.befit.adapter.Utilities
import kotlinx.android.synthetic.main.dl_size_check_no_compare_product.*
import org.jetbrains.anko.support.v4.startActivity


class SizeCheckAddClothDialog(): DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.dl_size_check_no_compare_product,container,false)
        Utilities.setGlobalFont(view, activity!!);
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_dl_size_check_add_cloth.setOnClickListener {
            //내옷추가 액티비티로
            startActivity<MySizeLookupActivity>()
        }
        dl_size_check_add_cloth_cancel.setOnClickListener {
            dismiss()
        }

    }
}