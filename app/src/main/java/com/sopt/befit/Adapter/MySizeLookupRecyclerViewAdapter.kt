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
import com.sopt.befit.data.JjimProductData
import com.sopt.befit.data.MySizeLookupData

class MySizeLookupRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<MySizeLookupData>) : RecyclerView.Adapter<MySizeLookupRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_my_size_lookup, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].b_name
        holder.p_name.text = dataList[position].p_name

        //그래들 이미지 설정

        holder.item_btn.setOnClickListener {
            //프로덕트의 상세페이지로 넘어간다
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_my_size_lookup_main) as ImageView

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_my_size_lookup_b_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_product_p_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_my_size_lookup) as RelativeLayout
    }
}