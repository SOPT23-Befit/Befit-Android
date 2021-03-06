package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.sopt.befit.R
import com.sopt.befit.db.SharedPreferenceController
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.v("oncreate","aaaaa")

        val token =SharedPreferenceController.getAuthorization(this)
        Log.d("token_aa",token)

        Handler().apply {
            postDelayed({

                if(token.length == 0)
                {
                    Log.d("token_aa" , token)

                    startActivity<IntroActivity>()
                    finish()
                }
                else
                {
                    Log.d("token_aa" , token)

                    startActivity<AAAAMainActivity>("token" to token)
                    finish()
                }

                //피니쉬 안해주면 스플래쉬 액티비티 쌓임

            },2000)//ctr+p 들어갈 매개변수 알려줌 {람다식 함수}
        }
        }

    }

