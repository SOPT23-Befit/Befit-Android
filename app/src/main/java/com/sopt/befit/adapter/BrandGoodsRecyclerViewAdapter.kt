package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.R.id.*

import com.sopt.befit.activity.AddMySizeBrandPageActivity
import com.sopt.befit.activity.AddMySizeGoodsPageActivity
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.activity.SelectBrandGoodsWindowActivity

import com.sopt.befit.data.BrandData
import com.sopt.befit.get.InitialBrand
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

class BrandGoodsRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<InitialBrand>) : RecyclerView.Adapter<BrandGoodsRecyclerViewAdapter.Holder>() {

    val BRAND_INITAIL_REQUEST_CODE = 1000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_data, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.name.text = dataList[position].name_korean


        holder.brandbtn.setOnClickListener {
            //Log.d("aaaa", dataList.get(position).name)

            if(ctx is AddMySizeBrandPageActivity){
                SelectBrandGoodsWindowActivity.brandgoodsinstance.brandBoxVisibleController(dataList.get(position).name_english)
                val intent: Intent = Intent(ctx, AddMySizeBrandPageActivity::class.java)                //brand_idx값 넘기기.
                intent.putExtra("brand_idx", dataList[position].idx)
                //ctx.startActivity(intent)
                ctx.finish()


            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_rv_brand_data) as TextView
        val brandbtn: RelativeLayout = itemView.findViewById(R.id.btn_rv_brand_whole_box) as RelativeLayout
    }
}
