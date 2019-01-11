package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.adapter.BrandGoodsRecyclerViewAdapter
import com.sopt.befit.adapter.ProductListRecyclerViewAdapter
import com.sopt.befit.adapter.Utilities
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.GetBrandResponse
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostBrandLikeResponse
import com.sopt.befit.post.PostBrandUnlikeResponse
import com.sopt.befit.post.PostProductLikeResponse
import com.sopt.befit.post.PostProductUnlikeResponse
import kotlinx.android.synthetic.main.activity_brand_main.*
import kotlinx.android.synthetic.main.activity_brand_main.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandMainActivity : BaseActivity() {

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    var token: String = ""
    lateinit var productData: ProductData
    var b_idx: Int = 0
    var flag: Int = 0 //1 일때 상품 하나 레이아웃 띄우기

    lateinit var brandProductListRecyclerViewAdapter: ProductListRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_main)

        token = intent.getStringExtra("token")
        flag = intent.getIntExtra("flag", 0)

        if (flag == 0) {
            layout_brand_main_one_product.visibility = View.GONE
            b_idx = intent.getIntExtra("idx", 0)
        } else {
            toast("프로덕트 하나")
            layout_brand_main_one_product.visibility = View.VISIBLE
            productData = intent.getSerializableExtra("ProductData") as ProductData
            setOneProductView()
            b_idx=productData.brand_idx
        }

        setViewClickListener()

        setRecyclerView()

        getBrandResponse()

        getBrandNewProductListResponse()
    }

    private fun setOneProductView() {
        tv_brand_main_p_b_name_kor.text = productData.name_korean
        tv_brand_main_p_name.text = productData.name
        tv_brand_main_b_price.text = productData.price

        val requestOption = RequestOptions()

        Glide.with(this@BrandMainActivity)
                .setDefaultRequestOptions(requestOption)
                .load(productData.image_url)
                .thumbnail(0.5f)
                .into(layout_brand_main_one_product.img_brand_main_p_image)
    }

    private fun setViewClickListener() {

        tv_brand_main_new.setOnClickListener {
            if (tv_brand_main_popular.isChecked) {
                tv_brand_main_popular.setChecked(false)
                tv_brand_main_new.setChecked(true)
                tv_brand_main_new.setTypeface(Utilities.boldTypeface);
                tv_brand_main_popular.setTypeface(Utilities.mediumTypeface);
                dataList.clear()
                getBrandNewProductListResponse()
            }
        }
        tv_brand_main_popular.setOnClickListener {
            //리사이클러 뷰 재통신
            if (tv_brand_main_new.isChecked) {
                tv_brand_main_new.setChecked(false)
                tv_brand_main_popular.setChecked(true)
                tv_brand_main_popular.setTypeface(Utilities.boldTypeface);
                tv_brand_main_new.setTypeface(Utilities.mediumTypeface);
                dataList.clear()
                getBrandPopularProductListResponse()
            }
        }

        img_brand_main_b_heart.setOnClickListener {
            if (productData.product_like == 1) {
                //좋아요 상태면 싫어요를 한다
                postBrandOneProductUnlikeResponse()
                productData.product_like = 0
            } else {
                postBrandOneProductLikeResponse()
                productData.product_like = 1
            }
        }

        img_brand_main_back.setOnClickListener {
            finish()
        }

        img_brand_main_item_box.setOnClickListener{
            val intent: Intent = Intent(this, ProductContentViewActivity::class.java)
            intent.putExtra("brand_idx", productData.brand_idx)
            intent.putExtra("link", productData.link)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        brandProductListRecyclerViewAdapter = ProductListRecyclerViewAdapter(this, dataList)
        rv_brand_main_product_list.adapter = brandProductListRecyclerViewAdapter
        rv_brand_main_product_list.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getBrandResponse() {
        val getBrandResponse = networkService.getBrandResponse(token, b_idx)
        getBrandResponse.enqueue(object : Callback<GetBrandResponse> {
            override fun onFailure(call: Call<GetBrandResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetBrandResponse>, response: Response<GetBrandResponse>) {
                if (response.isSuccessful) {
                    tv_brand_main_b_name_eng.setText(response.body()!!.data.name_english)
                    tv_brand_main_b_name_kor.setText(response.body()!!.data.name_korean)
                    if (response.body()!!.data.likeFlag == 1) {
                        img_brand_main_b_heart.setChecked(true)
                    }

                    val requestOption = RequestOptions()

                    Glide.with(this@BrandMainActivity)
                            .setDefaultRequestOptions(requestOption)
                            .load(response.body()!!.data.logo_url)
                            .thumbnail(0.5f)
                            .into(img_brand_main_logo)

                    Glide.with(this@BrandMainActivity)
                            .setDefaultRequestOptions(requestOption)
                            .load(response.body()!!.data.mainpage_url)
                            .thumbnail(0.5f)
                            .into(img_brand_main_page)

                    img_brand_main_b_heart.setOnClickListener {
                        if (response.body()!!.data.likeFlag == 1) {
                            postBrandUnlikeResponse()
                            response.body()!!.data.likeFlag = 0
                        } else {
                            postBrandLikeResponse()
                            response.body()!!.data.likeFlag = 1
                        }
                    }
                }
            }
        })
    }

    private fun getBrandNewProductListResponse() {
        val getBrandNewProductListResponse = networkService.getBrandNewProductListResponse(token, b_idx)
        getBrandNewProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            tv_brand_main_product_count.text = "PRODUCT (" + temp.size + ")"
                            brandProductListRecyclerViewAdapter.dataList.addAll(temp)
                            brandProductListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun getBrandPopularProductListResponse() {
        val getBrandPopularProductListResponse = networkService.getBrandPopularProductListResponse(token, b_idx)
        getBrandPopularProductListResponse.enqueue(object : Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        val temp: ArrayList<ProductData> = response.body()!!.data
                        if (temp.size > 0) {
                            brandProductListRecyclerViewAdapter.dataList.addAll(temp)
                            brandProductListRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun postBrandLikeResponse() {
        val postBrandLikeResponse = networkService.postBrandLikeResponse(token,
                b_idx)
        postBrandLikeResponse.enqueue(object : Callback<PostBrandLikeResponse> {
            override fun onFailure(call: Call<PostBrandLikeResponse>, t: Throwable) {
                Log.e("brand like fail", t.toString())
            }

            override fun onResponse(call: Call<PostBrandLikeResponse>, response: Response<PostBrandLikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }

    private fun postBrandUnlikeResponse() {
        val postJjimBrandUnlikeResponse = networkService.postBrandUnlikeResponse(token,
                b_idx)
        postJjimBrandUnlikeResponse.enqueue(object : Callback<PostBrandUnlikeResponse> {
            override fun onFailure(call: Call<PostBrandUnlikeResponse>, t: Throwable) {
                Log.e("brand like fail", t.toString())
            }

            override fun onResponse(call: Call<PostBrandUnlikeResponse>, response: Response<PostBrandUnlikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }

    private fun postBrandOneProductLikeResponse() {
        val postBrandOneProductLikeResponse = networkService.postProductLikeResponse(token,
                productData.idx)
        postBrandOneProductLikeResponse.enqueue(object : Callback<PostProductLikeResponse> {
            override fun onFailure(call: Call<PostProductLikeResponse>, t: Throwable) {
                Log.e("brand one product like fail", t.toString())
            }

            override fun onResponse(call: Call<PostProductLikeResponse>, response: Response<PostProductLikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }

    private fun postBrandOneProductUnlikeResponse() {
        val postBrandOneProductUnlikeResponse = networkService.postProductUnlikeResponse(token,
                productData.idx)
        postBrandOneProductUnlikeResponse.enqueue(object : Callback<PostProductUnlikeResponse> {
            override fun onFailure(call: Call<PostProductUnlikeResponse>, t: Throwable) {
                Log.e("jjim product like fail", t.toString())
            }

            override fun onResponse(call: Call<PostProductUnlikeResponse>, response: Response<PostProductUnlikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }
}