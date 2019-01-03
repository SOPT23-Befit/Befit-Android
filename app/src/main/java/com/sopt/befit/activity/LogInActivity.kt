package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_log_in.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        btn_log_in_join.setOnClickListener(){
            startActivity<SelectGenderActivity>()
        }
        ibtn_log_in_confirm.setOnClickListener(){


            startActivity<MainActivity>()//
        }


    }
}
