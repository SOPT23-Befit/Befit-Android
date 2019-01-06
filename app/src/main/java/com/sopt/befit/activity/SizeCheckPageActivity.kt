package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_size_check_page_activiy.*
import org.jetbrains.anko.startActivity

class SizeCheckPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size_check_page_activiy)
        btnBackOnClick()
    }
    fun btnBackOnClick(){
        btn_activity_size_check_page_back.setOnClickListener{
            startActivity<MySizeLookupActivity>()
            finish()
        }
    }
}
