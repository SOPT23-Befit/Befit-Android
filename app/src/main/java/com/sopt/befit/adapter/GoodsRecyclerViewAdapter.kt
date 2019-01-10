package com.sopt.befit.adapter

import android.app.Activity
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
import org.jetbrains.anko.ctx
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class GoodsRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<InitialGoods>) : RecyclerView.Adapter<GoodsRecyclerViewAdapter.Holder>() {

    var category_idx : Int = 0
    val brand_idx : Int = 0

    val GOODS_INITAIL_REQUEST_CODE = 1000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_brand_data, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = dataList[position].name

        holder.brandbtn.setOnClickListener {
            if (ctx is AddMySizeGoodsPageActivity) {

                val intent: Intent = Intent(ctx, SelectBrandGoodsWindowActivity::class.java)
                intent.putExtra("idx", dataList[position].idx)                         //상품idx 보내기.
                intent.putExtra("name",dataList[position].name)                        //상품이름 보내기.
                intent.putExtra("image_url",dataList[position].image_url)              //상품이미지 보내기.



                SelectBrandGoodsWindowActivity.brandgoodsinstance.goodsBoxVisibleController(dataList.get(position).name)

                ctx.setResult(Activity.RESULT_OK,intent)
                ctx.finish()

//
//                ctx.getgoodsInitial()
//                ctx.finish()


            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_rv_brand_data) as TextView
        val brandbtn: RelativeLayout = itemView.findViewById(R.id.btn_rv_brand_whole_box) as RelativeLayout
    }
}
