package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.befit.R
import com.sopt.befit.data.BrandRankingData
import com.sopt.befit.data.CheckMySizeData
import com.sopt.befit.data.JjimBrandData

class MySizeInfoRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CheckMySizeData>) : RecyclerView.Adapter<MySizeInfoRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_ranking_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //메인 사진 띄우기
        holder.category_name.text = dataList[position].category_name

        holder.item_btn.setOnClickListener {
            //넘어간다
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val category_name: TextView = itemView.findViewById(R.id.tv_rv_item_size_info_category) as TextView
        val logo: ImageView = itemView.findViewById(R.id.iv_rv_item_size_info_logo) as ImageView
        val item_btn: LinearLayout = itemView.findViewById(R.id.lo_rv_item_size_info_category_list) as LinearLayout
    }
}