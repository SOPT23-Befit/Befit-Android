package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_select_gender.*
import org.jetbrains.anko.startActivity

class BrandPreferenceManActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_preference)

        ibtn_select_gender_back.setOnClickListener(){
            startActivity<SelectGenderActivity>()
        }
    }
}
