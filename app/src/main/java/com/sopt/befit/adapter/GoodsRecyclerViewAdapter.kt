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
import com.sopt.befit.get.InitialGoods
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.startActivity

class GoodsRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<InitialGoods>) : RecyclerView.Adapter<GoodsRecyclerViewAdapter.Holder>() {

    var category_idx : Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_data, parent, false)

        return Holder(view)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = dataList[position].name

        holder.brandbtn.setOnClickListener {
            if (ctx is AddMySizeGoodsPageActivity) {
                val intent: Intent = Intent(ctx, AddMySizeGoodsPageActivity::class.java)
                intent.putExtra("name", dataList[position].idx)                         //상품명 보내기.
                val requestOptions = RequestOptions()
                //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
                //        requestOptions.error(R.drawable.에러시 띄울 이미지)
                //        requestOptions.override(150)

                Glide.with(ctx)
                        .setDefaultRequestOptions(requestOptions)
                        .load(dataList[position].image_url)
                        .thumbnail(0.5f)
                        .into(ctx.findViewById(img_my_size_add_p_img))
                //ctx.startActivity(intent)

                val brand_Idx=ctx.intent.getIntExtra("brand_idx",1)
                val category_Idx= ctx.intent.getIntExtra("catefory_idx",1)

                SelectBrandGoodsWindowActivity.brandgoodsinstance.goodsBoxVisibleController(dataList.get(position).name)

                ctx.getgoodsInitial(brand_Idx, category_Idx)
                ctx.finish()


            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_rv_brand_data) as TextView
        val brandbtn: RelativeLayout = itemView.findViewById(R.id.btn_rv_brand_whole_box) as RelativeLayout
    }
}
