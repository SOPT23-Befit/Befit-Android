package com.sopt.befit.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.get.*
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import okhttp3.Response
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Callback

//import javax.security.auth.callback.Callback

class SelectBrandGoodsWindowActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    var brand_idx : Int = 0
    var category_idx : Int = 3
    var image_url : String? = null
    var name_korean : String? = null

    val REQUEST_BRAND_ACTIVITY =777
    val REQUEST_GOODS_ACTIVITY =888

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

        GoodsSize=ArrayList<String>()
        //상품 정보 넘겨주기
//        product_idx = arguments!!.getInt("product_idx")
//        measure = arguments!!.getString("measure")

        category_idx = 3
        category_idx = intent.getIntExtra("category_idx", 5)

//        var data = AddMySizeGoodsPageActivity.instance.getGoodsData()
//        var jsonString = data.measure.toString()
//        var parser = JsonParser()
//        var json = parser.parse(jsonString).asJsonObject
//         closetSize = ArrayList<String>()

        GoodsSize.add("사이즈 선택")
//        for((index,measure) in json.entrySet().withIndex()){
////            GoodsSize.add(measure.key)
////        }
        GoodsSize.add("S")
        GoodsSize.add("M")
        GoodsSize.add("L")



        setSpinner(GoodsSize)
        setBrandBtnOnClick()
        setGoodsBtnOnClick()
        backGoodsBtnOnClick()
        addButtonOnClick()
        btn_activity_select_brand_goods_window_add.visibility = View.GONE               //제품상세 페이지
        activity_my_size_add.visibility = View.GONE                                     //추가버튼
        activity_select_brand_goods_window_size.visibility = View.GONE                  //사이즈상세 레이아웃
        btn_activity_select_brand_goods_window_goods.setTextColor(Color.parseColor("#848484"))
        btn_activity_select_brand_goods_window_arrow.setImageResource(R.drawable.ic_left_arrow_wh_notactivated)
        brandgoodsinstance = this

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {           //브랜드페이지에서 갔다온 후
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_BRAND_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                brand_idx=data!!.getIntExtra("brand_idx", 0)             //브랜드 idx 값 가져오기.
            }
        }
        else if(requestCode == REQUEST_GOODS_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                name_korean=data!!.getStringExtra("name_korean")
                val name : String = data!!.getStringExtra("name")
                image_url = data!!.getStringExtra("image_url")
                tv_select_brand_goods_window_goodsname.text = name
                tv_select_brand_goods_window_brandname.text= name_korean

                val requestOptions = RequestOptions()

                Glide.with(applicationContext)
                        .setDefaultRequestOptions(requestOptions)
                        .load(image_url)
                        .thumbnail(0.5f)
                        .into(img_my_size_add_p_img)
            }
        }
    }

        fun addButtonOnClick() {
        btn_activity_select_brand_goods_window_add.setOnClickListener {
            finish()
        }
    }

    fun setBrandBtnOnClick() {
        btn_selected_brand.setOnClickListener {
            val intent : Intent = Intent(this, AddMySizeBrandPageActivity::class. java)
            startActivityForResult(intent,REQUEST_BRAND_ACTIVITY)
        }
    }

    fun backGoodsBtnOnClick(){
        btn_activity_select_brand_goods_window_back.setOnClickListener{
            finish()
        }
    }


    fun setGoodsBtnOnClick() {
        btn_selected_goods.setOnClickListener {
            if (btn_selected_goods.isClickable == true && btn_activity_select_brand_goods_window_goods.isClickable == true) {
                val intent : Intent = Intent(this, AddMySizeGoodsPageActivity::class. java)
                intent.putExtra("brand_idx", brand_idx)
                intent.putExtra("category_idx",category_idx)
                startActivityForResult(intent,REQUEST_GOODS_ACTIVITY)
            }
        }
    }

    private fun setSpinner(dataList : ArrayList<String>) {


        sp_my_size_add_select_size.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataList)
        //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
        sp_my_size_add_select_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //toast("선택된 아이템 : " + sp_my_size_add_select_size.getItemAtPosition((position)))
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

    }

}