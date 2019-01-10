package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import com.sopt.befit.adapter.HomeFragmentBannerPagerAdapter
import com.sopt.befit.adapter.IntroPagerAdapter
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_home.*

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        configureIntroNavigation()
    }

    private fun configureIntroNavigation() {
        vp_intro.adapter = IntroPagerAdapter(supportFragmentManager, 4)
        vp_intro.offscreenPageLimit = 4

        lo_tab_intro.setupWithViewPager(vp_intro)
    }

}
