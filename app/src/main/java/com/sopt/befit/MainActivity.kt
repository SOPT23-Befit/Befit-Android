package com.sopt.befit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService

class MainActivity : AppCompatActivity() {
    val networkServie:NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
