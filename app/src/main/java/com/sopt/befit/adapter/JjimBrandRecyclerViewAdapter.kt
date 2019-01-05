package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
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

        if (dataList[position].b_like) {
            holder.heart.setChecked(true)
        } else {

        }

        holder.item_btn.setOnClickListener {
            val intent: Intent = Intent(ctx, BrandMainActivity::class.java)
            intent.putExtra("b_name", dataList[position].b_name)
            ctx.startActivity(intent)
        }

        holder.heart.setOnClickListener {
            dataList[position].b_like = !dataList[position].b_like
            if (dataList[position].b_like) {
                dataList[position].b_like = false
                //서버에 전달
            } else {
                dataList[position].b_like = true
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_brand_logo) as ImageView

        val heart: ToggleButton = itemView.findViewById(R.id.img_rv_item_jjim_brand_heart) as ToggleButton

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_brand_b_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_jjim_brand) as RelativeLayout
    }
}