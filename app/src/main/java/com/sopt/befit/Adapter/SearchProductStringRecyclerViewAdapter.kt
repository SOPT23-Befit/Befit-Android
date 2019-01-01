package com.sopt.befit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.befit.R

class SearchProductStringRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<String>) : RecyclerView.Adapter<SearchProductStringRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_string, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.mainString.text = dataList[position]

        holder.item_btn.setOnClickListener{
            //선택한 문장으로 자동 완성
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainString: TextView = itemView.findViewById(R.id.tv_rv_item_search_string_main) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_search_string) as RelativeLayout
    }
}