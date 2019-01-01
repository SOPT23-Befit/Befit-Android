package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.sopt.befit.R

class SearchProductImageRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ImageView>) : RecyclerView.Adapter<SearchProductImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_image, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //holder.mainImage.setImageDrawable()

        holder.item_btn.setOnClickListener{
            //이미지 프로덕트 아이디를 넘겨 상세페이지로 들어감
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainImage: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main) as ImageView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_search_image) as RelativeLayout
    }
}