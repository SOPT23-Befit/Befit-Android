package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import com.sopt.befit.activity.AAAAMainActivity
import com.sopt.befit.activity.LogInActivity
import com.sopt.befit.db.SharedPreferenceController
import kotlinx.android.synthetic.main.intro4.*
import org.jetbrains.anko.support.v4.startActivity

class Intro4Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.intro4, container, false)
        return brandFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val token =SharedPreferenceController.getAuthorization(activity!!)


        ibtn_intro_ok.setOnClickListener(){
            if(token!=null)
            {
                startActivity<AAAAMainActivity>("token" to token)
            }

            else
            {
                startActivity<LogInActivity>()

            }
        }

    }

}