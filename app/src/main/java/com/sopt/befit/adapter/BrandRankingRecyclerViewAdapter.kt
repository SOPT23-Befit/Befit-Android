package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.data.BrandRankingData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService

class BrandRankingRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BrandRankingData>) : RecyclerView.Adapter<BrandRankingRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_ranking_item, parent, false)
        return Holder(view)
    }
    var flag = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    var token : String = SharedPreferenceController.getAuthorization(ctx)

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        Log.d("bbbb",position.toString())
        holder.b_name.text = dataList[position].name_english
        holder.brandrankingcnt.text = (position+1).toString()
        holder.item_btn.setOnClickListener {
            val intent : Intent = Intent(ctx, BrandMainActivity::class.java)
            intent.putExtra("idx", dataList[position].idx)
            intent.putExtra("flag",flag)
            intent.putExtra("token",token)


            ctx.startActivity(intent)
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