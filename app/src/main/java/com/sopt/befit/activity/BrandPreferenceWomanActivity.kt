package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_brand_preference_man.*
import org.jetbrains.anko.startActivity


class BrandPreferenceWomanActivity : AppCompatActivity() {

    lateinit var brand1 : String
    lateinit var brand2 : String
    var toggle : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference_woman)

        var gender : String = intent.getStringExtra("gender")
        ibtn



        ibtn_brand_prefer_next.setOnClickListener(){
            startActivity<SignUpActivity>("gender" to "$gender","brand1" to "$brand1","brand2" to "$brand2")
        }

    }
}
