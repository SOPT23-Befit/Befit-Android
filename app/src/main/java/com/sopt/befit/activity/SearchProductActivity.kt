package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.sopt.befit.R
import com.sopt.befit.adapter.SearchProductImageRecyclerViewAdapter
import com.sopt.befit.fragment.SBrandFragment
import com.sopt.befit.fragment.SProductFragment
import kotlinx.android.synthetic.main.activity_search_product.*

class SearchProductActivity : AppCompatActivity() {

    lateinit var searchProductImageRecyclerViewAdapter: SearchProductImageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        setRecyclerView()

        addFragment(SProductFragment())

        setViewClickListener()

    }

    private fun setViewClickListener() {
        et_search_product_write.setOnClickListener{
            et_search_product_write.setText("")
            rv_search_product_2.setVisibility(View.VISIBLE)
            layout_search_product_bottom.setVisibility(View.GONE)
        }
        img_search_product_p.setOnClickListener {
            replaceFragment(SProductFragment())
            if(img_search_product_b.isChecked){
                img_search_product_b.setChecked(false)
            }else{
                img_search_product_p.setChecked(true)
            }
        }
        img_search_product_b.setOnClickListener {
            replaceFragment(SBrandFragment())
            if(img_search_product_p.isChecked){
                img_search_product_p.setChecked(false)
            }else{
                img_search_product_b.setChecked(true)
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_search_product_fragment_block, fragment)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_search_product_fragment_block, fragment)
        transaction.commit()
    }

    private fun setRecyclerView() {

        var dataList: ArrayList<String> = ArrayList()

        searchProductImageRecyclerViewAdapter = SearchProductImageRecyclerViewAdapter(this, dataList)
        rv_search_product_2.adapter = searchProductImageRecyclerViewAdapter
        rv_search_product_2.layoutManager = LinearLayoutManager(this)
    }
}
