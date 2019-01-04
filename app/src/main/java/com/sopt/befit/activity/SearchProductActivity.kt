package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.sopt.befit.R
import com.sopt.befit.adapter.SearchProductImageRecyclerViewAdapter
import com.sopt.befit.fragment.SBrandFragment
import com.sopt.befit.fragment.SProductFragment
import kotlinx.android.synthetic.main.activity_search_product.*
import android.view.inputmethod.EditorInfo
import com.sopt.befit.data.SearchProductData


class SearchProductActivity : AppCompatActivity() , TextView.OnEditorActionListener{

    lateinit var searchProductImageRecyclerViewAdapter: SearchProductImageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        setRecyclerView()

        addFragment(SProductFragment())

        setViewClickListener()
    }

    private fun setViewClickListener() {

        tv_search_product_new.setOnClickListener {
            //리사이클러뷰 재통신
            if(tv_search_product_popular.isChecked){
                tv_search_product_popular.setChecked(false)
                tv_search_product_new.setChecked(true)
            }
        }
        tv_search_product_popular.setOnClickListener {
            //리사이클러 뷰 재통신
            if(tv_search_product_new.isChecked){
                tv_search_product_new.setChecked(false)
                tv_search_product_popular.setChecked(true)
            }
        }

        tv_search_product_cancle.setOnClickListener{
            rv_search_product_top.setVisibility(View.VISIBLE)
            tv_search_product_cancle.setVisibility(View.GONE)
            layout_search_product_bottom.setVisibility(View.GONE)
            et_search_product_write.setText("")
        }

        et_search_product_write.setOnEditorActionListener(this)

        et_search_product_write.setOnClickListener() {
            et_search_product_write.setSelection(et_search_product_write.getText().length);
            rv_search_product_top.setVisibility(View.GONE)
            //edittext 이미지를 바꿔
            tv_search_product_cancle.setVisibility(View.VISIBLE)
            //검색이 되면 이걸 띄워
        }

        img_search_product_p.setOnClickListener {
            layout_search_product_new_popular.setVisibility(View.VISIBLE)
            replaceFragment(SProductFragment())
            if (img_search_product_b.isChecked) {
                img_search_product_b.setChecked(false)
            } else {
                img_search_product_p.setChecked(true)
            }
        }
        img_search_product_b.setOnClickListener {
            layout_search_product_new_popular.setVisibility(View.GONE)
            replaceFragment(SBrandFragment())
            if (img_search_product_p.isChecked) {
                img_search_product_p.setChecked(false)
            } else {
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

        var dataList: ArrayList<SearchProductData> = ArrayList()
        dataList.add(SearchProductData("여기안에다가 통신 후 하나를 만들어서", "","","","","","",""))
        dataList.add(SearchProductData("각 이미지 링크를 주고 그래들을 사용해서", "","","","","","",""))
        dataList.add(SearchProductData("각 이미지들을 띄우자", "","","","","","",""))

        searchProductImageRecyclerViewAdapter = SearchProductImageRecyclerViewAdapter(this, dataList)
        rv_search_product_top.adapter = searchProductImageRecyclerViewAdapter
        rv_search_product_top.layoutManager = LinearLayoutManager(this)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            layout_search_product_bottom.setVisibility(View.VISIBLE)
            //통신 맞는 결과 띄우기
        }

        return false
    }
}
