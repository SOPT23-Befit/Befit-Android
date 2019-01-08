package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.data.BrandData

class EachBrandRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BrandData>) : RecyclerView.Adapter<EachBrandRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_each_brand, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].name_english

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
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.img_rv_item_each_brand_logo) as ImageView

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_each_brand_b_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_each_brand) as RelativeLayout
    }
}