package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.sopt.befit.fragment.BrandFragment
import com.sopt.befit.fragment.ProductFragment
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_jjim.*

class JjimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jjim)

        addFragment(ProductFragment())

        setViewClickListener()
    }

    private fun setViewClickListener() {
        img_jjim_product.setOnClickListener {
            replaceFragment(ProductFragment())
        }
        img_jjim_brand.setOnClickListener {
            replaceFragment(BrandFragment())
        }
    }

    private fun addFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main_act_fragment_block, fragment)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main_act_fragment_block, fragment)
        transaction.commit()
    }
}
