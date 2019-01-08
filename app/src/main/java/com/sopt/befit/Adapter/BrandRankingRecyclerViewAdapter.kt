package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.data.BrandRankingData

class BrandRankingRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BrandRankingData>) : RecyclerView.Adapter<BrandRankingRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_ranking_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //메인 사진 띄우기

        holder.b_name.text = dataList[position].name_korean
        holder.brandrankingcnt.text = (position+1).toString()
        holder.item_btn.setOnClickListener {
            //넘어간다
        }

        val requestOptions = RequestOptions()
        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].logo_url)
                .thumbnail(0.5f)
                .into(holder.logo)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_brand_ranking_b_name) as TextView
        val logo: ImageView = itemView.findViewById(R.id.iv_rv_brand_ranking_logo) as ImageView
        val brandrankingcnt : TextView = itemView.findViewById(R.id.tv_rv_item_brand_ranking_cnt) as TextView
        val item_btn: LinearLayout = itemView.findViewById(R.id.lo_rv_item_brand_ranking_total) as LinearLayout
    }
}