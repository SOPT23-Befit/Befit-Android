package com.sopt.befit.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.sopt.befit.Adapter.BrandGoodsRecyclerViewAdapter
import com.sopt.befit.R
import com.sopt.befit.data.BrandData
import kotlinx.android.synthetic.main.activity_add_my_size.*
import kotlinx.android.synthetic.main.activity_add_my_size_goods_page.*
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import org.jetbrains.anko.startActivity

class AddMySizeBrandPageActivity : AppCompatActivity(){


    var alphabet : ArrayList<ImageView> = ArrayList()
    lateinit var  brandBrandsRecyclerViewAdapter : BrandGoodsRecyclerViewAdapter
    lateinit var select : ImageView

    //var clicked;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_size)
        rv_add_my_size_brand_list.visibility = View.GONE
        setRecyclerView()
        setBtnClickListener()
    }

    fun setBtnClickListener(){
        btn_activity_add_my_size_brand_back.setOnClickListener{
            startActivity<SelectBrandGoodsWindowActivity>()
        }
        alphabet.add(btn_activity_search_for_brands_a)
        alphabet.add(btn_activity_search_for_brands_b)
        alphabet.add(btn_activity_search_for_brands_c)
        alphabet.add(btn_activity_search_for_brands_d)
        alphabet.add(btn_activity_search_for_brands_e)
        alphabet.add(btn_activity_search_for_brands_f)
        alphabet.add(btn_activity_search_for_brands_g)
        alphabet.add(btn_activity_search_for_brands_h)
        alphabet.add(btn_activity_search_for_brands_i)
        alphabet.add(btn_activity_search_for_brands_j)
        alphabet.add(btn_activity_search_for_brands_k)
        alphabet.add(btn_activity_search_for_brands_l)
        alphabet.add(btn_activity_search_for_brands_m)
        alphabet.add(btn_activity_search_for_brands_n)
        alphabet.add(btn_activity_search_for_brands_o)
        alphabet.add(btn_activity_search_for_brands_p)
        alphabet.add(btn_activity_search_for_brands_q)
        alphabet.add(btn_activity_search_for_brands_r)
        alphabet.add(btn_activity_search_for_brands_s)
        alphabet.add(btn_activity_search_for_brands_t)
        alphabet.add(btn_activity_search_for_brands_u)
        alphabet.add(btn_activity_search_for_brands_v)
        alphabet.add(btn_activity_search_for_brands_w)
        alphabet.add(btn_activity_search_for_brands_x)
        alphabet.add(btn_activity_search_for_brands_y)
        alphabet.add(btn_activity_search_for_brands_z)
        alphabet.add(btn_activity_search_for_brands_etc)

        for(st in alphabet) {
            select = st
            st.setOnClickListener {
                select.setImageResource(R.mipmap.ic_launcher)
                st.setImageResource(R.drawable.ic_launcher_background)
                rv_add_my_size_brand_list.visibility = View.VISIBLE
            }
        }
    }

    private fun setRecyclerView() {
        var brandList : ArrayList<BrandData> = ArrayList()
        brandList.add(BrandData("유니클로"))
        brandList.add(BrandData("스파오"))
        brandList.add(BrandData("지오다노"))


        brandBrandsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(this,brandList)
        rv_add_my_size_brand_list.adapter = brandBrandsRecyclerViewAdapter
        rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(this)
    }
    fun brandBoxVisibleController(brandName : String){
        activity_add_my_size_brand_selected.visibility = View.GONE
        activity_add_my_size_brand_name.text = brandName
    }



}