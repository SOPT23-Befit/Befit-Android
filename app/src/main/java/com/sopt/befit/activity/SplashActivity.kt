package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import com.sopt.befit.db.SharedPreferenceController
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val guide_flag = SharedPreferenceController.getguideflag()
        val token =SharedPreferenceController.getAuthorization(this)
        if(guide_flag==true)
        {
            if(token==null)
            {
                startActivity<LogInActivity>()
            }
            else if(token!=null)
            {
                startActivity<AAAAMainActivity>("token" to token)
            }
        }

        else if(guide_flag==false)
        {
            startActivity<IntroActivity>()
        }
    }
}
