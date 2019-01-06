package com.sopt.befit.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.sopt.befit.adapter.BrandGoodsRecyclerViewAdapter
import com.sopt.befit.R
import com.sopt.befit.data.BrandData
import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.InitialBrand
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_add_my_size.*
import okhttp3.Response
import retrofit2.Callback
import retrofit2.http.Header
import retrofit2.http.Path

class AddMySizeBrandPageActivity : AppCompatActivity() {

    val BRAND_INITAIL_REQUEST_CODE = 1000
    lateinit var BrandsRecyclerViewAdapter: BrandGoodsRecyclerViewAdapter
    val dataList: ArrayList<InitialBrand> by lazy {
        ArrayList<InitialBrand>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var alphabet: ArrayList<ImageView> = ArrayList()
    lateinit var select: ImageView



    //var clicked;

    companion object {
        lateinit var brandinstance: AddMySizeBrandPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_size)
        rv_add_my_size_brand_list.visibility = View.GONE
       // setRecyclerView()
        setBtnClickListener()
        brandinstance = this
    }

    fun setBtnClickListener() {
        btn_activity_add_my_size_brand_back.setOnClickListener {
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

        for (st in alphabet) {
            //   select = st
            st.setOnClickListener {
                initial_reset()
                //   select.setImageResource(R.mipmap.ic_launcher)
                // st.setImageResource(R.drawable.ic_launcher_background)
                if (st == btn_activity_search_for_brands_a) {
                    btn_activity_search_for_brands_a.setImageResource(R.drawable.button_brandsearched_a)
                    getBrandInitial('A')
                }
                else if (st == btn_activity_search_for_brands_b) {
                    btn_activity_search_for_brands_b.setImageResource(R.drawable.button_brandsearched_b)
                    getBrandInitial('B')
                }
                else if (st == btn_activity_search_for_brands_c){
                    btn_activity_search_for_brands_c.setImageResource(R.drawable.button_brandsearched_c)
                    getBrandInitial('C')
                }
                else if (st == btn_activity_search_for_brands_d){
                    btn_activity_search_for_brands_d.setImageResource(R.drawable.button_brandsearched_d)
                    getBrandInitial('D')
                }
                else if (st == btn_activity_search_for_brands_e){
                    btn_activity_search_for_brands_e.setImageResource(R.drawable.button_brandsearched_e)
                    getBrandInitial('E')
                }
                else if (st == btn_activity_search_for_brands_f){
                    btn_activity_search_for_brands_f.setImageResource(R.drawable.button_brandsearched_f)
                    getBrandInitial('F')
                }
                else if (st == btn_activity_search_for_brands_g){
                    btn_activity_search_for_brands_g.setImageResource(R.drawable.button_brandsearched_g)
                    getBrandInitial('G')
                }
                else if (st == btn_activity_search_for_brands_h){
                    btn_activity_search_for_brands_h.setImageResource(R.drawable.button_brandsearched_h)
                    getBrandInitial('H')
                }
                else if (st == btn_activity_search_for_brands_i){
                    btn_activity_search_for_brands_i.setImageResource(R.drawable.button_brandsearched_i)
                    getBrandInitial('I')
                }
                else if (st == btn_activity_search_for_brands_j){
                    btn_activity_search_for_brands_j.setImageResource(R.drawable.button_brandsearched_j)
                    getBrandInitial('J')
                }
                else if (st == btn_activity_search_for_brands_k){
                    btn_activity_search_for_brands_k.setImageResource(R.drawable.button_brandsearched_k)
                    getBrandInitial('K')
                }
                else if (st == btn_activity_search_for_brands_l){
                    btn_activity_search_for_brands_l.setImageResource(R.drawable.button_brandsearched_l)
                    getBrandInitial('L')
                }
                else if (st == btn_activity_search_for_brands_m){
                    btn_activity_search_for_brands_m.setImageResource(R.drawable.button_brandsearched_m)
                    getBrandInitial('M')
                }
                else if (st == btn_activity_search_for_brands_n){
                    btn_activity_search_for_brands_n.setImageResource(R.drawable.button_brandsearched_n)
                    getBrandInitial('N')
                }
                else if (st == btn_activity_search_for_brands_o){
                    btn_activity_search_for_brands_o.setImageResource(R.drawable.button_brandsearched_o)
                    getBrandInitial('O')
                }
                else if (st == btn_activity_search_for_brands_p){
                    btn_activity_search_for_brands_p.setImageResource(R.drawable.button_brandsearched_p)
                    getBrandInitial('P')
                }
                else if (st == btn_activity_search_for_brands_q){
                    btn_activity_search_for_brands_q.setImageResource(R.drawable.button_brandsearched_q)
                    getBrandInitial('Q')
                }
                else if (st == btn_activity_search_for_brands_r){
                    btn_activity_search_for_brands_r.setImageResource(R.drawable.button_brandsearched_r)
                    getBrandInitial('R')
                }
                else if (st == btn_activity_search_for_brands_s){
                    btn_activity_search_for_brands_s.setImageResource(R.drawable.button_brandsearched_s)
                    getBrandInitial('S')
                }
                else if (st == btn_activity_search_for_brands_t){
                    btn_activity_search_for_brands_t.setImageResource(R.drawable.button_brandsearched_t)
                    getBrandInitial('T')
                }
                else if (st == btn_activity_search_for_brands_u){
                    btn_activity_search_for_brands_u.setImageResource(R.drawable.button_brandsearched_u)
                    getBrandInitial('U')
                }
                else if (st == btn_activity_search_for_brands_v){
                    btn_activity_search_for_brands_v.setImageResource(R.drawable.button_brandsearched_v)
                    getBrandInitial('V')
                }
                else if (st == btn_activity_search_for_brands_w){
                    btn_activity_search_for_brands_w.setImageResource(R.drawable.button_brandsearched_w)
                    getBrandInitial('W')
                }
                else if (st == btn_activity_search_for_brands_x){
                    btn_activity_search_for_brands_x.setImageResource(R.drawable.button_brandsearched_x)
                    getBrandInitial('X')
                }
                else if (st == btn_activity_search_for_brands_y){
                    btn_activity_search_for_brands_y.setImageResource(R.drawable.button_brandsearched_y)
                    getBrandInitial('Y')
                }
                else if (st == btn_activity_search_for_brands_z){
                    btn_activity_search_for_brands_z.setImageResource(R.drawable.button_brandsearched_z)
                    getBrandInitial('Z')
                }
                else if (st == btn_activity_search_for_brands_etc){
                    btn_activity_search_for_brands_etc.setImageResource(R.drawable.button_brandsearched_etc)
                    getBrandInitial('*')
                }
                rv_add_my_size_brand_list.visibility = View.VISIBLE
            }
        }
    }

    fun initial_reset() {
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
        var brandList: ArrayList<InitialBrand> = ArrayList()
        
        /*
brandList.add(BrandData("유니클로"))
        brandList.add(BrandData("스파오"))
        brandList.add(BrandData("지오다노"))
         */


        BrandsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(this, brandList)
        rv_add_my_size_brand_list.adapter = BrandsRecyclerViewAdapter
        rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(this)

    }

    private fun getBrandInitial(initial : Char) {

        val getBrandInitialResponse = networkService.getBrandsByInitialResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", initial)
        getBrandInitialResponse.enqueue(object : Callback<GetInitialBrandResponse> {
            override fun onFailure(call: retrofit2.Call<GetInitialBrandResponse>, t: Throwable) {
                Log.e("brandInitial", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetInitialBrandResponse>, response: retrofit2.Response<GetInitialBrandResponse>) {
                if (response.isSuccessful) {
                    val temp: ArrayList<InitialBrand> = response.body()!!.data
                    if (temp.size > 0) {
    //                    val position = brandBrandsRecyclerViewAdapter.itemCount
  //                      brandBrandsRecyclerViewAdapter.dataList.addAll(temp)
//                        brandBrandsRecyclerViewAdapter.notifyItemInserted(position)


                        BrandsRecyclerViewAdapter = BrandGoodsRecyclerViewAdapter(applicationContext, temp)
                        rv_add_my_size_brand_list.layoutManager = LinearLayoutManager(applicationContext)
                        rv_add_my_size_brand_list.adapter = BrandsRecyclerViewAdapter

                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BRAND_INITAIL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
            }
        }
    }


}