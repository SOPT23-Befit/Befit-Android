package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.sopt.befit.R
import com.sopt.befit.data.SearchProductData

class SearchProductImageRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<SearchProductData>) : RecyclerView.Adapter<SearchProductImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_image, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //holder.mainImage.setImageDrawable()

        // holder.mainImage1 = dataList[position].p_link1 그래들 써서 하나하나 이미지 달아주기

        //그래들써서 이미지 띄우기
        when (position % 8) {
            0 -> holder.mainImage1.setImageResource(R.drawable.button_fav)
            1 -> holder.mainImage2.setImageResource(R.drawable.button_fav)
            2 -> holder.mainImage3.setImageResource(R.drawable.button_fav)
            3 -> holder.mainImage4.setImageResource(R.drawable.button_fav)
            4 -> holder.mainImage5.setImageResource(R.drawable.button_fav)
            5 -> holder.mainImage6.setImageResource(R.drawable.button_fav)
            6 -> holder.mainImage7.setImageResource(R.drawable.button_fav)
            7 -> holder.mainImage8.setImageResource(R.drawable.button_fav)
            else -> holder.mainImage1.setImageResource(R.drawable.button_notfav)
        }

        holder.mainImage1.setOnClickListener {
            //이미지 프로덕트 아이디를 넘겨 상세페이지로 들어감
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainImage1: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main1) as ImageView
        val mainImage2: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main2) as ImageView
        val mainImage3: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main3) as ImageView
        val mainImage4: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main4) as ImageView
        val mainImage5: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main5) as ImageView
        val mainImage6: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main6) as ImageView
        val mainImage7: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main7) as ImageView
        val mainImage8: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main8) as ImageView
    }
}