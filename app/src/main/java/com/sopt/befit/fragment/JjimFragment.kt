package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.R
import kotlinx.android.synthetic.main.fragment_jjim.*

class JjimFragment : Fragment(), View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val jjimFragmentView: View = inflater!!.inflate(R.layout.fragment_jjim, container, false)
        return jjimFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main_act_fragment_block, fragment)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main_act_fragment_block, fragment)
        transaction.commit()
    }

    override fun onClick(v: View?) {
        v!!.isSelected()
    }
}