package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.befit.R
import com.sopt.befit.data.JjimBrandData

class JjimBrandRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<JjimBrandData>) : RecyclerView.Adapter<JjimBrandRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_jjim_brand, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //메인 사진 띄우기
        holder.b_name.text = dataList[position].b_name
        holder.item_btn.setOnClickListener {
            //넘어간다
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_brand_main) as ImageView
        val heart: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_brand_heart) as ImageView
        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_brand_b_name) as TextView
        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_jjim_brand) as RelativeLayout
    }
}