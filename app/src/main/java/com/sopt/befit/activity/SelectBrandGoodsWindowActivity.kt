package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SelectBrandGoodsWindowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_brand_goods_window)
        setBrandBtnOnClick()
        setGoodsBtnOnClick()

    }

    fun setBrandBtnOnClick(){
        btn_selected_brand.setOnClickListener {
            startActivity<AddMySizeBrandPageActivity>()
        }
    }
    fun setGoodsBtnOnClick(){
        btn_selected_goods.setOnClickListener {
            startActivity<AddMySizeGoodsPageActivity>()
        }
    }


}
