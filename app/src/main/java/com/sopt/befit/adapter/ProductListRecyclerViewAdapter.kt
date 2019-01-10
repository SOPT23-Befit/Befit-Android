package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.data.ProductData
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostProductLikeResponse
import com.sopt.befit.post.PostProductUnlikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ProductData>) : RecyclerView.Adapter<ProductListRecyclerViewAdapter.Holder>() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_each_product, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].name_korean
        holder.p_name.text = dataList[position].name
        holder.p_price.text = dataList[position].price

        if (dataList[position].product_like == 1) {
            holder.heart.setChecked(true)
        }

        val requestOptions = RequestOptions()

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].image_url)
                .thumbnail(0.5f)
                .into(holder.main)

        holder.item_btn.setOnClickListener {
            val intent: Intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("brand_idx ", dataList[position].brand_idx)
            intent.putExtra("link", dataList[position].link)
            ctx.startActivity(intent)
        }

        holder.heart.setOnClickListener {
            //dataList[position].p_like=!dataList[position].p_like 통신으로 좋아요 싫어요 해줍니다
            if (dataList[position].product_like == 1) {
                //좋아요 상태면 싫어요를 한다
                postJjimProductUnlikeResponse(position)
                dataList[position].product_like = 0
            } else {
                postJjimProductLikeResponse(position)
                dataList[position].product_like = 1
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_each_product_main) as ImageView

        val heart: ToggleButton = itemView.findViewById(R.id.img_rv_item_each_product_heart) as ToggleButton

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_each_product_p_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_each_product_b_name) as TextView
        val p_price: TextView = itemView.findViewById(R.id.tv_rv_item_each_product_p_price) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_each_product) as RelativeLayout
    }

    private fun postJjimProductLikeResponse(p: Int) {
        val postJjimProductLikeResponse = networkService.postProductLikeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80",
                dataList[p].idx)
        postJjimProductLikeResponse.enqueue(object : Callback<PostProductLikeResponse> {
            override fun onFailure(call: Call<PostProductLikeResponse>, t: Throwable) {
                Log.e("jjim product like fail", t.toString())
            }

            override fun onResponse(call: Call<PostProductLikeResponse>, response: Response<PostProductLikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }

    private fun postJjimProductUnlikeResponse(p: Int) {
        val postJjimProductUnlikeResponse = networkService.postProductUnlikeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80",
                dataList[p].idx)
        postJjimProductUnlikeResponse.enqueue(object : Callback<PostProductUnlikeResponse> {
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