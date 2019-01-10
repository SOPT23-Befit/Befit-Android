package com.sopt.befit.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View

import com.sopt.befit.R
import com.sopt.befit.adapter.GoodsRecyclerViewAdapter
import com.sopt.befit.data.BrandData
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.GetInitialGoodsResponse
import com.sopt.befit.get.InitialBrand
import com.sopt.befit.get.InitialGoods
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_add_my_size.*
import kotlinx.android.synthetic.main.activity_add_my_size_goods_page.*
import org.jetbrains.anko.toast
import retrofit2.Callback

class AddMySizeGoodsPageActivity : BaseActivity() {


    var brand_idx: Int = 2
    var category_idx: Int = 0


    val GOODS_INITAIL_REQUEST_CODE = 2000
    lateinit var GoodsRecyclerViewAdapter: GoodsRecyclerViewAdapter
    val dataList: ArrayList<InitialGoods> by lazy {
        ArrayList<InitialGoods>()
    }

    companion object {
        lateinit var instance: AddMySizeGoodsPageActivity
    }

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var initialData: InitialGoods

    fun getGoodsData(): InitialGoods {
        return initialData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_size_goods_page)
        rv_add_my_size_goods_list.visibility = View.GONE
        setBtnClickListener()
        setRecyclerView()
        brand_idx = intent.getIntExtra("brand_idx", 0)                       //브랜드 idx값 받기.
        category_idx = intent.getIntExtra("category_idx", 0)                 //카테고리 idx값 받기.
        getgoodsInitial()
        rv_add_my_size_goods_list.visibility = View.VISIBLE
        instance = this

    }

    fun setBtnClickListener() {
        btn_activity_add_my_size_goods_back.setOnClickListener {
            AddMySizeGoodsPageActivity.instance.finish()
        }
        et_add_my_size_act_search_goods.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //                // 입력이 끝났을 때
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력하기 전에
            }

            override fun onTextChanged(searchKeyWord: CharSequence?, start: Int, before: Int, count: Int) {
                // 입력되는 텍스트에 변화가 있을 때
                if (searchKeyWord!!.length > 0) {
                    getSelectedGoodsResponse(searchKeyWord.toString())
                }
            }
        })


    }

    private fun setRecyclerView() {

        var goodsList: ArrayList<InitialGoods> = ArrayList()

        GoodsRecyclerViewAdapter = GoodsRecyclerViewAdapter(this, goodsList)
        rv_add_my_size_goods_list.layoutManager = LinearLayoutManager(this)
        rv_add_my_size_goods_list.adapter = GoodsRecyclerViewAdapter
    }

    private fun getSelectedGoodsResponse(searchKeyWord: String) {
        //통신
        //onResponse(){}
        //통신 성공했을때, BrandGoodsRecyclerViewAdapter.datalist에 통신에 대한 데이터를 넣어준 다음 notify시켜주면 된다.
        //searchKeyWord 여기에 검색어가 담겨있으니까, 통신할때 이 값을 넘겨주면 됨!!!
    }

    public fun getgoodsInitial() {

        val getGoodsInitialResponse = networkService.getGoodsByInitialResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", brand_idx, category_idx)
        getGoodsInitialResponse.enqueue(object : Callback<GetInitialGoodsResponse> {
            override fun onFailure(call: retrofit2.Call<GetInitialGoodsResponse>, t: Throwable) {
                Log.e("goodsInitial error", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetInitialGoodsResponse>, response: retrofit2.Response<GetInitialGoodsResponse>) {

                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<InitialGoods> = response.body()!!.data
                        if (temp.size > 0) {
                            val position = GoodsRecyclerViewAdapter.itemCount
                            GoodsRecyclerViewAdapter.dataList.addAll(temp)
                            GoodsRecyclerViewAdapter.notifyItemInserted(position)

                        }
                    }
                }
            }
        })
    }


}
