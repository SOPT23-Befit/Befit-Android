package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import com.sopt.befit.R
import com.sopt.befit.adapter.SearchProductImageRecyclerViewAdapter
import com.sopt.befit.adapter.SearchProductStringRecyclerViewAdapter
import com.sopt.befit.fragment.SBrandFragment
import com.sopt.befit.fragment.SProductFragment
import kotlinx.android.synthetic.main.activity_search_product.*

class SearchProductActivity : AppCompatActivity() {

    lateinit var searchProductImageRecyclerViewAdapter: SearchProductImageRecyclerViewAdapter
    lateinit var searchProductStringRecyclerViewAdapter: SearchProductStringRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        setRecyclerView()

        addFragment(SProductFragment())

        setViewClickListener()

    }

    private fun setViewClickListener() {
        img_search_product_p.setOnClickListener {
            replaceFragment(SProductFragment())
        }
        img_search_product_b.setOnClickListener {
            replaceFragment(SBrandFragment())
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

        //내가 선호하는 프로덕션만 출력

        var dataList: ArrayList<String> = ArrayList()
        dataList.add("유니클로")
        dataList.add("후아유")
        dataList.add("오아이오아이")
        dataList.add("휠라")
        dataList.add("아디다스")
        dataList.add("나이키")

        searchProductStringRecyclerViewAdapter = SearchProductStringRecyclerViewAdapter(this, dataList)
        rv_search_product_2.adapter = searchProductStringRecyclerViewAdapter
        rv_search_product_2.layoutManager = LinearLayoutManager(this)
    }
}
