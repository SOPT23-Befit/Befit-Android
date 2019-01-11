package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.data.SearchProductData

class SearchProductImageRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<SearchProductData>) : RecyclerView.Adapter<SearchProductImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_image, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val requestOptions = RequestOptions()

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick1)
                .thumbnail(0.5f)
                .into(holder.mainImage1)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick2)
                .thumbnail(0.5f)
                .into(holder.mainImage2)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick3)
                .thumbnail(0.5f)
                .into(holder.mainImage3)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick4)
                .thumbnail(0.5f)
                .into(holder.mainImage4)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick5)
                .thumbnail(0.5f)
                .into(holder.mainImage5)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick6)
                .thumbnail(0.5f)
                .into(holder.mainImage6)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick7)
                .thumbnail(0.5f)
                .into(holder.mainImage7)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].p_lick8)
                .thumbnail(0.5f)
                .into(holder.mainImage8)

        holder.mainImage1.setOnClickListener {
            Toast.makeText(ctx, dataList[position].p_idx1.toString(), Toast.LENGTH_SHORT).show();
        }
        holder.mainImage2.setOnClickListener {
            Toast.makeText(ctx, "2", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage3.setOnClickListener {
            Toast.makeText(ctx, "3", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage4.setOnClickListener {
            Toast.makeText(ctx, "4", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage5.setOnClickListener {
            Toast.makeText(ctx, "5", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage6.setOnClickListener {
            Toast.makeText(ctx, "6", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage7.setOnClickListener {
            Toast.makeText(ctx, "7", Toast.LENGTH_SHORT).show();
        }
        holder.mainImage8.setOnClickListener {
            Toast.makeText(ctx, "8", Toast.LENGTH_SHORT).show();
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