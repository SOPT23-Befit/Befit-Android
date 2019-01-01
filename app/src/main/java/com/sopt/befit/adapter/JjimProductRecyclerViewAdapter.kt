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

class JjimProductRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<JjimProductData>) : RecyclerView.Adapter<JjimProductRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_jjim_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].b_name
        holder.p_name.text = dataList[position].p_name
        holder.p_price.text = dataList[position].p_price

        if (dataList[position].p_like) {
            //찬 하트 이미지 표시
        } else {
            //빈 하트 이미지 표시
        }

        holder.item_btn.setOnClickListener{
            //프로덕트의 상세페이지로 넘어간다
        }

        holder.heart.setOnClickListener{
            dataList[position].p_like=!dataList[position].p_like
            if (dataList[position].p_like) {
                //뷰의 하트 이미지 변경 & 서버에 전달
            } else {

            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_product_main) as ImageView
        val heart: ImageView = itemView.findViewById(R.id.img_rv_item_jjim_product_heart) as ImageView

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_product_b_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_product_p_name) as TextView
        val p_price: TextView = itemView.findViewById(R.id.tv_rv_item_jjim_product_p_price) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_jjim_product) as RelativeLayout
    }
}