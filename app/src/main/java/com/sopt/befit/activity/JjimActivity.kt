package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.CheckBox
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_jjim.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.sopt.befit.fragment.BrandFragment
import com.sopt.befit.fragment.ProductFragment




class JjimActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jjim)

        addFragment(ProductFragment())

        setViewClickListener()
    }

    private fun setViewClickListener() {
        img_jjim_product.setOnClickListener {
            replaceFragment(ProductFragment())
            if(img_jjim_brand.isChecked){
                img_jjim_brand.setChecked(false)
            }else{
                img_jjim_product.setChecked(true)
            }
        }
        img_jjim_brand.setOnClickListener {
            replaceFragment(BrandFragment())
            img_jjim_product.setChecked(false)
            if(img_jjim_product.isChecked){
                img_jjim_product.setChecked(false)
            }else{
                img_jjim_brand.setChecked(true)
            }
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

    override fun onClick(v: View?) {
        v!!.isSelected()
    }
}
