package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.sopt.befit.R
import com.sopt.befit.data.JjimProductData

class JjimProductRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<JjimProductData>) : RecyclerView.Adapter<JjimProductRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_each_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].b_name
        holder.p_name.text = dataList[position].p_name
        holder.p_price.text = dataList[position].p_price

        if (dataList[position].p_like) {
            holder.heart.setChecked(true)
        } else {

        }

        holder.item_btn.setOnClickListener{
            Toast.makeText(ctx, "상세 상품 정보로 넘어가기", Toast.LENGTH_SHORT).show();
        }

        holder.heart.setOnClickListener{
            dataList[position].p_like=!dataList[position].p_like
            if (dataList[position].p_like) {
                dataList[position].p_like=false
                //서버에 전달
            } else {
                dataList[position].p_like=true
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_my_size_lookup_main) as ImageView

        val heart: ToggleButton = itemView.findViewById(R.id.img_rv_item_jjim_product_heart) as ToggleButton

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_my_size_lookup_b_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_my_size_lookup_p_name) as TextView
        val p_price: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_product_p_price) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_jjim_product) as RelativeLayout
    }
}