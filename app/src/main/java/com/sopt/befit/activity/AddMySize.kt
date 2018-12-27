package com.sopt.befit.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.sopt.befit.Adapter.BrandGoodsRecyclerViewAdapter
import com.sopt.befit.R
import com.sopt.befit.data.BrandData
import kotlinx.android.synthetic.main.activity_add_my_size.*

class AddMySize : AppCompatActivity() {
    lateinit var  BrandGoodsRecyclerViewAdapter : BrandGoodsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_size)
        setRecyclerView()
        setBtnClickListener()
    }

    fun setBtnClickListener(){
        et_add_my_size_act_search_goods.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                // 입력이 끝났을 때
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력하기 전에
            }

            override fun onTextChanged(searchKeyWord: CharSequence?, start: Int, before: Int, count: Int) {
                // 입력되는 텍스트에 변화가 있을 때
                if (searchKeyWord!!.length > 0){
                    getSelectedGoodsResponse(searchKeyWord.toString())
                }
            }
        })
    }

    private fun setRecyclerView() {
        var brandList : ArrayList<BrandData> = ArrayList()
        brandList.add(BrandData("유니클로"))
        brandList.add(BrandData("스파오"))
        brandList.add(BrandData("지오다노"))

        var goodsList : ArrayList<BrandData> = ArrayList()
        goodsList.add(BrandData("유니클로 가디건"))
        goodsList.add(BrandData("스파오 패딩"))

        BrandGoodsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(this,brandList)
        rv_add_my_size_brand_list.adapter = BrandGoodsRecyclerViewAdapter
        rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(this)

        BrandGoodsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(this,goodsList)
        rv_add_my_size_goods_list.adapter = BrandGoodsRecyclerViewAdapter
        rv_add_my_size_goods_list.layoutManager = LinearLayoutManager(this)
    }

    private fun getSelectedGoodsResponse(searchKeyWord : String){
        //통신
        //onResponse(){}
        //통신 성공했을때, BrandGoodsRecyclerViewAdapter.datalist에 통신에 대한 데이터를 넣어준 다음 notify시켜주면 된다.
        //searchKeyWord 여기에 검색어가 담겨있으니까, 통신할때 이 값을 넘겨주면 됨!!!
    }

    fun brandBoxVisibleController(brandName : String){
        activity_add_my_size_brand_selected.visibility = View.GONE
        activity_add_my_size_selected_brand_name.text = brandName
    }
}