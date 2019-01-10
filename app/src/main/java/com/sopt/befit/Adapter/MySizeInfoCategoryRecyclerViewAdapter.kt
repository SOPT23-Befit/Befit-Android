package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.sopt.befit.R
import com.sopt.befit.activity.MySizeLookupActivity

class MySizeInfoCategoryRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<Int>) : RecyclerView.Adapter<MySizeInfoCategoryRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_each_category, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        when (dataList[position]) {
            0 -> {
                holder.oneImage.setImageResource(R.drawable.outer)
                holder.oneText.text = "Outer"
            }
            1 -> {
                holder.oneImage.setImageResource(R.drawable.jacket)
                holder.oneText.text = "Jacket"
            }
            2 -> {
                holder.oneImage.setImageResource(R.drawable.coat)
                holder.oneText.text = "Coat"
            }
            3 -> {
                holder.oneImage.setImageResource(R.drawable.shirts)
                holder.oneText.text = "Shirts"
            }
            4 -> {
                holder.oneImage.setImageResource(R.drawable.knit)
                holder.oneText.text = "Knits"
            }
            5 -> {
                holder.oneImage.setImageResource(R.drawable.hoody)
                holder.oneText.text = "Hoody"
            }
            6 -> {
                holder.oneImage.setImageResource(R.drawable.sweatshirts)
                holder.oneText.text = "Sweat Shirts"
            }
            7 -> {
                holder.oneImage.setImageResource(R.drawable.tshirts)
                holder.oneText.text = "T-Shirts"
            }
            8 -> {
                holder.oneImage.setImageResource(R.drawable.onepiece)
                holder.oneText.text = "Onepiece"
            }
            9 -> {
                holder.oneImage.setImageResource(R.drawable.jeans)
                holder.oneText.text = "Jeans"
            }
            10 -> {
                holder.oneImage.setImageResource(R.drawable.pants)
                holder.oneText.text = "Pants"
            }
            11 -> {
                holder.oneImage.setImageResource(R.drawable.slacks)
                holder.oneText.text = "Slacks"
            }
            12 -> {
                holder.oneImage.setImageResource(R.drawable.shortpants)
                holder.oneText.text = "Short-Pants"
            }
            13 -> {
                holder.oneImage.setImageResource(R.drawable.skirts)
                holder.oneText.text = "Skirts"
            }
        }

        holder.item_btn.setOnClickListener {
            val intent: Intent = Intent(ctx, MySizeLookupActivity::class.java)
            intent.putExtra("c_idx", dataList[position])
            ctx.startActivity(intent)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val oneImage: ImageView = itemView.findViewById(R.id.img_rv_item_each_category_img) as ImageView

        val oneText: TextView = itemView.findViewById(R.id.tv_rv_item_each_category_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_each_category) as RelativeLayout
    }
}