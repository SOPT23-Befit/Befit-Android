package com.sopt.befit.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.get.*
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import okhttp3.Response
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Callback

//import javax.security.auth.callback.Callback

class SelectBrandGoodsWindowActivity : BaseActivity(), AdapterView.OnItemSelectedListener {


    var category_idx : Int = 0

    lateinit var GoodsSize : ArrayList<String>

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    companion object {
        lateinit var brandgoodsinstance: SelectBrandGoodsWindowActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_brand_goods_window)
      
        //상품 정보 넘겨주기
//        product_idx = arguments!!.getInt("product_idx")
//        measure = arguments!!.getString("measure")

        var data = AddMySizeGoodsPageActivity.instance.getGoodsData()
        var jsonString = data.measure.toString()
        var parser = JsonParser()
        var json = parser.parse(jsonString).asJsonObject
        // closetSize = ArrayList<String>()

        GoodsSize.add("사이즈 선택")
        for((index,measure) in json.entrySet().withIndex()){
            GoodsSize.add(measure.key)
        }

        //intent.getIntExtra("brand_idx",0)             //브랜드 idx값 가져오기.
        intent.getIntExtra("categpry_idx",0)          //카테고리 idx 값 가져오기.

        setSpinner(GoodsSize)
        setBrandBtnOnClick()
        setGoodsBtnOnClick()
        addButtonOnClick()
        btn_activity_select_brand_goods_window_add.visibility = View.GONE               //제품상세 페이지
        activity_my_size_add.visibility = View.GONE                                     //추가버튼
        activity_select_brand_goods_window_size.visibility = View.GONE                  //사이즈상세 레이아웃
        btn_activity_select_brand_goods_window_goods.setTextColor(Color.parseColor("#848484"))
        btn_activity_select_brand_goods_window_arrow.setImageResource(R.drawable.ic_left_arrow_wh_notactivated)
        brandgoodsinstance = this

    }

    fun addButtonOnClick() {
        btn_activity_select_brand_goods_window_add.setOnClickListener {
            startActivity<MySizeLookupActivity>()
        }
    }

    fun setBrandBtnOnClick() {
        btn_selected_brand.setOnClickListener {
            startActivity<AddMySizeBrandPageActivity>()
        }
    }

    fun setGoodsBtnOnClick() {
        btn_selected_goods.setOnClickListener {
            if (btn_selected_goods.isClickable == true && btn_activity_select_brand_goods_window_goods.isClickable == true) {
                startActivity<AddMySizeGoodsPageActivity>()
            }
        }
    }

    private fun setSpinner(dataList : ArrayList<String>) {


        sp_my_size_add_select_size.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataList)
        //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
        sp_my_size_add_select_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                toast("선택된 아이템 : " + sp_my_size_add_select_size.getItemAtPosition((position)))
                //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌
                btn_activity_select_brand_goods_window_add.visibility = View.VISIBLE
                activity_select_brand_goods_window_size.visibility = View.VISIBLE



            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    fun brandBoxVisibleController(brandName: String) {
        activity_add_my_size_brand_name.text = brandName
        btn_selected_goods.isClickable = true
        btn_activity_select_brand_goods_window_goods.isClickable = true
        btn_activity_select_brand_goods_window_arrow.setImageResource(R.drawable.ic_right_arrow_wh)
        btn_activity_select_brand_goods_window_goods.setTextColor(Color.parseColor("#000000"))

    }

    fun goodsBoxVisibleController(brandName: String) {
        activity_my_size_add.visibility = View.VISIBLE
        activity_add_my_size_goods_name.text = brandName

        intent.getIntExtra("name",0)
    }

}