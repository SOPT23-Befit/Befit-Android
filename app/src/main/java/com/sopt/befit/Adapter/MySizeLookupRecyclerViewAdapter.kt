package com.sopt.befit.adapter

import android.content.Context
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
import com.sopt.befit.Adapter.Utilities
import com.sopt.befit.R
import com.sopt.befit.R.id.tv_my_size_add_edit
import com.sopt.befit.activity.MySizeLookupActivity
import com.sopt.befit.data.MySizeLookupData
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.DeleteClosetResponse
import kotlinx.android.synthetic.main.activity_my_size_lookup.*
import org.jetbrains.anko.image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySizeLookupRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ClosetDetail>) : RecyclerView.Adapter<MySizeLookupRecyclerViewAdapter.Holder>() {

    //flag 0 추가하는 어뎁터 1 나의 옷 정보들 삭제하는 어뎁터

    var flag: Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var token: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_my_size_lookup, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"

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
                    Toast.makeText(ctx, "상세페이지로 넘어가기", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "나의 옷 정보 추가하기 창으로 넘어가기", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (holder.b_name.text != "") {
                    deleteClosetResponse(dataList[position].closet_idx)
                    MySizeLookupActivity.instance.dataList.removeAt(position)
                    MySizeLookupActivity.instance.mySizeLookupRecyclerViewAdapter.notifyItemRemoved(position)
                } else {
                    Toast.makeText(ctx, "아무일도 안 일어나", Toast.LENGTH_SHORT).show();
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