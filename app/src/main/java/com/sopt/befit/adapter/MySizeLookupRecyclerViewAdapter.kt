package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.MySizeLookupActivity
import com.sopt.befit.activity.SelectBrandGoodsWindowActivity
import com.sopt.befit.activity.SizeCheckPageActivity
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.DeleteClosetResponse
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySizeLookupRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ClosetDetail>, val c_idx : Int) : RecyclerView.Adapter<MySizeLookupRecyclerViewAdapter.Holder>() {

    //flag 0 추가하는 어뎁터 1 나의 옷 정보들 삭제하는 어뎁터

    var flag: Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var token: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_my_size_lookup, parent, false)
        Utilities.setGlobalFont(view, ctx);
        token = SharedPreferenceController.getAuthorization(ctx)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.b_name.text = dataList[position].name_korean
        holder.p_name.text = dataList[position].name

        if (dataList[position].name_korean != "") {
            val requestOptions = RequestOptions()

            Glide.with(ctx)
                    .setDefaultRequestOptions(requestOptions)
                    .load(dataList[position].image_url)
                    .thumbnail(0.5f)
                    .into(holder.main)
        } else {
            holder.main.setImageResource(R.drawable.add)
        }

        if (dataList[position].name_korean != "" && flag == 1) {
            holder.delete.visibility = View.VISIBLE
            holder.gray.visibility = View.VISIBLE
        } else {
            holder.delete.visibility = View.INVISIBLE
            holder.gray.visibility = View.INVISIBLE
        }

        holder.item_btn.setOnClickListener {
            if (flag == 0) {
                if (holder.b_name.text != "") {
                    val intent: Intent = Intent(ctx, SizeCheckPageActivity::class.java)
                    intent.putExtra("closet_idx", dataList[position].closet_idx)
                    ctx.startActivity(intent)
                } else {
                    val intent: Intent = Intent(ctx, SelectBrandGoodsWindowActivity::class.java)
                    intent.putExtra("category_idx", c_idx)
                    ctx.startActivity(intent)
                }
            } else {
                if (holder.b_name.text != "") {
                    deleteClosetResponse(dataList[position].closet_idx)
                    MySizeLookupActivity.instance.dataList.removeAt(position)
                    MySizeLookupActivity.instance.mySizeLookupRecyclerViewAdapter.notifyItemRemoved(position)
                    MySizeLookupActivity.instance.mySizeLookupRecyclerViewAdapter.notifyItemRangeChanged(position, getItemCount())

                } else {

                }
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val main: ImageView = itemView.findViewById(R.id.img_rv_item_my_size_lookup_main) as ImageView
        val delete: ImageView = itemView.findViewById(R.id.img_rv_item_my_size_lookup_delete) as ImageView

        val gray: TextView = itemView.findViewById(R.id.back_rv_item_my_size_lookup_gray) as TextView
        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_my_size_lookup_b_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_my_size_lookup_p_name) as TextView

        val item_btn: RelativeLayout = itemView.findViewById(R.id.btn_rv_item_my_size_lookup) as RelativeLayout
    }

    private fun deleteClosetResponse(pc_idx: Int) {
        val deleteClosetResponse = networkService.deleteClosetResponse(token, pc_idx)
        deleteClosetResponse.enqueue(object : Callback<DeleteClosetResponse> {
            override fun onFailure(call: Call<DeleteClosetResponse>, t: Throwable) {
                Log.e("delete closet fail", t.toString())
            }

            override fun onResponse(call: Call<DeleteClosetResponse>, response: Response<DeleteClosetResponse>) {
                if (response.isSuccessful) {

                }
            }
        })
    }
}