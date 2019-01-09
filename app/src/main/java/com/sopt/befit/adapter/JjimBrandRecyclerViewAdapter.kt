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
import com.sopt.befit.data.BrandData
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostBrandLikeResponse
import com.sopt.befit.post.PostBrandUnlikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JjimBrandRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BrandData>) : RecyclerView.Adapter<JjimBrandRecyclerViewAdapter.Holder>() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_jjim_brand, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //메인 사진 띄우기
        holder.b_name.text = dataList[position].name_english

        if (dataList[position].likeFlag==1) {
            holder.heart.setChecked(true)
        }

        val requestOptions = RequestOptions()

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].logo_url)
                .thumbnail(0.5f)
                .into(holder.logo)

        holder.item_btn.setOnClickListener {
            val intent: Intent = Intent(ctx, BrandMainActivity::class.java)
            intent.putExtra("idx", dataList[position].idx)
            ctx.startActivity(intent)
        }

        holder.heart.setOnClickListener {
            if (dataList[position].likeFlag == 1) {
                //좋아요 상태면 싫어요를 한다
                postJjimBrandUnlikeResponse(position)
                dataList[position].likeFlag = 0
            } else {
                postJjimBrandLikeResponse(position)
                dataList[position].likeFlag = 1
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_brand_logo) as ImageView

        val heart: ToggleButton = itemView.findViewById(R.id.img_rv_item_jjim_brand_heart) as ToggleButton

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_brand_b_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_jjim_brand) as RelativeLayout
    }

    private fun postJjimBrandLikeResponse(p: Int) {
        val postJjimBrandLikeResponse = networkService.postBrandLikeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80",
                dataList[p].idx)
        postJjimBrandLikeResponse.enqueue(object : Callback<PostBrandLikeResponse> {
            override fun onFailure(call: Call<PostBrandLikeResponse>, t: Throwable) {
                Log.e("jjim brand like fail", t.toString())
            }

            override fun onResponse(call: Call<PostBrandLikeResponse>, response: Response<PostBrandLikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }

    private fun postJjimBrandUnlikeResponse(p: Int) {
        val postJjimBrandUnlikeResponse = networkService.postBrandUnlikeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80",
                dataList[p].idx)
        postJjimBrandUnlikeResponse.enqueue(object : Callback<PostBrandUnlikeResponse> {
            override fun onFailure(call: Call<PostBrandUnlikeResponse>, t: Throwable) {
                Log.e("jjim brand like fail", t.toString())
            }

            override fun onResponse(call: Call<PostBrandUnlikeResponse>, response: Response<PostBrandUnlikeResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }
}