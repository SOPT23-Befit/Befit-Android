package com.sopt.befit.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.befit.R

import com.sopt.befit.activity.AddMySizeBrandPageActivity
import com.sopt.befit.activity.AddMySizeGoodsPageActivity
import com.sopt.befit.activity.SelectBrandGoodsWindowActivity

import com.sopt.befit.data.BrandData
import org.jetbrains.anko.startActivity

class BrandGoodsRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BrandData>) : RecyclerView.Adapter<BrandGoodsRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_data, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = dataList[position].name


        holder.brandbtn.setOnClickListener {

            (ctx as AddMySizeBrandPageActivity).brandBoxVisibleController(dataList[position].name)
        }
        holder.goodsbtn.setOnClickListener {

            (ctx as AddMySizeGoodsPageActivity).brandBoxVisibleController(dataList[position].name)
        }
        holder.brandbtn.setOnClickListener {
            (ctx as AddMySizeBrandPageActivity).startActivity<SelectBrandGoodsWindowActivity>()
        }
        //holder.goodsbtn.setOnClickListener {
        //    (ctx as AddMySizeGoodsPageActivity).startActivity<SelectBrandGoodsWindowActivity>()
        //}

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_rv_brand_data) as TextView
        val brandbtn: RelativeLayout = itemView.findViewById(R.id.btn_rv_brand_whole_box) as RelativeLayout
        val goodsbtn: RelativeLayout = itemView.findViewById(R.id.btn_rv_brand_whole_box) as RelativeLayout
    }

}

