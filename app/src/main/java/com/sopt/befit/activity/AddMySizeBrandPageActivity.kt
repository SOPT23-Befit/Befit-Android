package com.sopt.befit.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.sopt.befit.adapter.BrandGoodsRecyclerViewAdapter
import com.sopt.befit.R
import com.sopt.befit.data.BrandData
import kotlinx.android.synthetic.main.activity_add_my_size.*

class AddMySizeBrandPageActivity : AppCompatActivity(){


    var alphabet : ArrayList<ImageView> = ArrayList()
    lateinit var select : ImageView
    lateinit var  brandBrandsRecyclerViewAdapter : BrandGoodsRecyclerViewAdapter


    //var clicked;

    companion object {
        lateinit var brandinstance : AddMySizeBrandPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_size)
        rv_add_my_size_brand_list.visibility = View.GONE
        setRecyclerView()
        setBtnClickListener()
        brandinstance = this
    }

    fun setBtnClickListener(){
        btn_activity_add_my_size_brand_back.setOnClickListener{
            AddMySizeBrandPageActivity.brandinstance.finish()
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
         //   select = st
            st.setOnClickListener {
                initial_reset()
                //   select.setImageResource(R.mipmap.ic_launcher)
               // st.setImageResource(R.drawable.ic_launcher_background)
                if(st == btn_activity_search_for_brands_a)
                btn_activity_search_for_brands_a.setImageResource(R.drawable.button_brandsearched_a)
                else if(st == btn_activity_search_for_brands_b)
                btn_activity_search_for_brands_b.setImageResource(R.drawable.button_brandsearched_b)
                else if(st == btn_activity_search_for_brands_c)
                btn_activity_search_for_brands_c.setImageResource(R.drawable.button_brandsearched_c)
                else if(st == btn_activity_search_for_brands_d)
                btn_activity_search_for_brands_d.setImageResource(R.drawable.button_brandsearched_d)
                else if(st == btn_activity_search_for_brands_e)
                btn_activity_search_for_brands_e.setImageResource(R.drawable.button_brandsearched_e)
                else if(st == btn_activity_search_for_brands_f)
                btn_activity_search_for_brands_f.setImageResource(R.drawable.button_brandsearched_f)
                else if(st == btn_activity_search_for_brands_g)
                btn_activity_search_for_brands_g.setImageResource(R.drawable.button_brandsearched_g)
                else if(st == btn_activity_search_for_brands_h)
                btn_activity_search_for_brands_h.setImageResource(R.drawable.button_brandsearched_h)
                else if(st == btn_activity_search_for_brands_i)
                btn_activity_search_for_brands_i.setImageResource(R.drawable.button_brandsearched_i)
                else if(st == btn_activity_search_for_brands_j)
                btn_activity_search_for_brands_j.setImageResource(R.drawable.button_brandsearched_j)
                else if(st == btn_activity_search_for_brands_k)
                btn_activity_search_for_brands_k.setImageResource(R.drawable.button_brandsearched_k)
                else if(st == btn_activity_search_for_brands_l)
                btn_activity_search_for_brands_l.setImageResource(R.drawable.button_brandsearched_l)
                else if(st == btn_activity_search_for_brands_m)
                btn_activity_search_for_brands_m.setImageResource(R.drawable.button_brandsearched_m)
                else if(st == btn_activity_search_for_brands_n)
                btn_activity_search_for_brands_n.setImageResource(R.drawable.button_brandsearched_n)
                else if(st == btn_activity_search_for_brands_o)
                btn_activity_search_for_brands_o.setImageResource(R.drawable.button_brandsearched_o)
                else if(st == btn_activity_search_for_brands_p)
                btn_activity_search_for_brands_p.setImageResource(R.drawable.button_brandsearched_p)
                else if(st == btn_activity_search_for_brands_q)
                btn_activity_search_for_brands_q.setImageResource(R.drawable.button_brandsearched_q)
                else if(st == btn_activity_search_for_brands_r)
                btn_activity_search_for_brands_r.setImageResource(R.drawable.button_brandsearched_r)
                else if(st == btn_activity_search_for_brands_s)
                btn_activity_search_for_brands_s.setImageResource(R.drawable.button_brandsearched_s)
                else if(st == btn_activity_search_for_brands_t)
                btn_activity_search_for_brands_t.setImageResource(R.drawable.button_brandsearched_t)
                else if(st == btn_activity_search_for_brands_u)
                btn_activity_search_for_brands_u.setImageResource(R.drawable.button_brandsearched_u)
                else if(st == btn_activity_search_for_brands_v)
                btn_activity_search_for_brands_v.setImageResource(R.drawable.button_brandsearched_v)
                else if(st == btn_activity_search_for_brands_w)
                btn_activity_search_for_brands_w.setImageResource(R.drawable.button_brandsearched_w)
                else if(st == btn_activity_search_for_brands_x)
                btn_activity_search_for_brands_x.setImageResource(R.drawable.button_brandsearched_x)
                else if(st == btn_activity_search_for_brands_y)
                btn_activity_search_for_brands_y.setImageResource(R.drawable.button_brandsearched_y)
                else if(st == btn_activity_search_for_brands_z)
                btn_activity_search_for_brands_z.setImageResource(R.drawable.button_brandsearched_z)
                else if(st == btn_activity_search_for_brands_etc)
                btn_activity_search_for_brands_etc.setImageResource(R.drawable.button_brandsearched_etc)
                rv_add_my_size_brand_list.visibility = View.VISIBLE
            }
        }
    }

    fun initial_reset(){
        btn_activity_search_for_brands_a.setImageResource(R.drawable.button_brandsearch_a)
        btn_activity_search_for_brands_b.setImageResource(R.drawable.button_brandsearch_b)
        btn_activity_search_for_brands_c.setImageResource(R.drawable.button_brandsearch_c)
        btn_activity_search_for_brands_d.setImageResource(R.drawable.button_brandsearch_d)
        btn_activity_search_for_brands_e.setImageResource(R.drawable.button_brandsearch_e)
        btn_activity_search_for_brands_f.setImageResource(R.drawable.button_brandsearch_f)
        btn_activity_search_for_brands_g.setImageResource(R.drawable.button_brandsearch_g)
        btn_activity_search_for_brands_h.setImageResource(R.drawable.button_brandsearch_h)
        btn_activity_search_for_brands_i.setImageResource(R.drawable.button_brandsearch_i)
        btn_activity_search_for_brands_j.setImageResource(R.drawable.button_brandsearch_j)
        btn_activity_search_for_brands_k.setImageResource(R.drawable.button_brandsearch_k)
        btn_activity_search_for_brands_l.setImageResource(R.drawable.button_brandsearch_l)
        btn_activity_search_for_brands_m.setImageResource(R.drawable.button_brandsearch_m)
        btn_activity_search_for_brands_n.setImageResource(R.drawable.button_brandsearch_n)
        btn_activity_search_for_brands_o.setImageResource(R.drawable.button_brandsearch_o)
        btn_activity_search_for_brands_p.setImageResource(R.drawable.button_brandsearch_p)
        btn_activity_search_for_brands_q.setImageResource(R.drawable.button_brandsearch_q)
        btn_activity_search_for_brands_r.setImageResource(R.drawable.button_brandsearch_r)
        btn_activity_search_for_brands_s.setImageResource(R.drawable.button_brandsearch_s)
        btn_activity_search_for_brands_t.setImageResource(R.drawable.button_brandsearch_t)
        btn_activity_search_for_brands_u.setImageResource(R.drawable.button_brandsearch_u)
        btn_activity_search_for_brands_v.setImageResource(R.drawable.button_brandsearch_v)
        btn_activity_search_for_brands_w.setImageResource(R.drawable.button_brandsearch_w)
        btn_activity_search_for_brands_x.setImageResource(R.drawable.button_brandsearch_x)
        btn_activity_search_for_brands_y.setImageResource(R.drawable.button_brandsearch_y)
        btn_activity_search_for_brands_z.setImageResource(R.drawable.button_brandsearch_z)
        btn_activity_search_for_brands_etc.setImageResource(R.drawable.button_brandsearch_etc)


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




}