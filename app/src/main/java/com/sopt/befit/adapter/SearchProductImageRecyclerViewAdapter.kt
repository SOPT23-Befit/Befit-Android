package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.data.SearchProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchProductImageRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<SearchProductData>) : RecyclerView.Adapter<SearchProductImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_image, parent, false)
        Utilities.setGlobalFont(view, ctx);
        token = SharedPreferenceController.getAuthorization(ctx)
        return Holder(view)
    }
    lateinit var temp : UserTotalData

    lateinit var intent: Intent

    var token : String = ""

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }



    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val requestOptions = RequestOptions()

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd0.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage1)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd1.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage2)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd2.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage3)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd3.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage4)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd4.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage5)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd5.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage6)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd6.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage7)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].pd7.image_url)
                .thumbnail(0.5f)
                .into(holder.mainImage8)

        holder.mainImage1.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd0.idx)
            intent.putExtra("url", dataList[position].pd0.link)
            intent.putExtra("name_english", dataList[position].pd0.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage2.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd1.idx)
            intent.putExtra("url", dataList[position].pd1.link)
            intent.putExtra("name_english", dataList[position].pd1.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage3.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd2.idx)
            intent.putExtra("url", dataList[position].pd2.link)
            intent.putExtra("name_english", dataList[position].pd2.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage4.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd3.idx)
            intent.putExtra("url", dataList[position].pd3.link)
            intent.putExtra("name_english", dataList[position].pd3.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage5.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd4.idx)
            intent.putExtra("url", dataList[position].pd4.link)
            intent.putExtra("name_english", dataList[position].pd4.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage6.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd5.idx)
            intent.putExtra("url", dataList[position].pd5.link)
            intent.putExtra("name_english", dataList[position].pd5.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage7.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd6.idx)
            intent.putExtra("url", dataList[position].pd6.link)
            intent.putExtra("name_english", dataList[position].pd6.name_english)
            getUserDataResponse(ctx)
        }
        holder.mainImage8.setOnClickListener {
            intent = Intent(ctx, ProductContentViewActivity::class.java)
            intent.putExtra("idx", dataList[position].pd7.idx)
            intent.putExtra("url",dataList[position].pd7.link)
            intent.putExtra("name_english",dataList[position].pd7.name_english)
            getUserDataResponse(ctx)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainImage1: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main1) as ImageView
        val mainImage2: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main2) as ImageView
        val mainImage3: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main3) as ImageView
        val mainImage4: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main4) as ImageView
        val mainImage5: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main5) as ImageView
        val mainImage6: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main6) as ImageView
        val mainImage7: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main7) as ImageView
        val mainImage8: ImageView = itemView.findViewById(R.id.img_rv_item_search_image_main8) as ImageView
    }

    private fun getUserDataResponse(ctx : Context){
        Log.d("aaaaaaa","aaaaaa")
        val getUserDataResponse = networkService.getUserDataResponse(token)
        getUserDataResponse.enqueue(object : Callback<GetUserDataResponse> {
            override fun onFailure(call: Call<GetUserDataResponse>, t: Throwable) { Log.e("board list fail", t.toString())
            }
            override fun onResponse(call: Call<GetUserDataResponse>, response: Response<GetUserDataResponse>) {
                response?.let {
                    when (it.body()!!.status) {
                        200 -> {
                            Log.v("success", response.message().toString())
                            temp  = response.body()!!.data
                            intent.putExtra("UserTotalData",temp)
                            ctx.startActivity(intent)
                        }

                        400 -> {
                            Log.v("fail",response.message())
                            Log.v("fail",response.errorBody().toString())
                            ctx.toast("로그인 실패")
                        }

                        500 -> {

                            Log.v("409 error",response.message())
                            Log.v("server error",response.errorBody().toString())
                            ctx.toast("서버 내부 에러")
                        }
                        600->{
                            Log.v("600 error",response.message())
                            Log.v("database error",response.errorBody().toString())
                            ctx.toast("데이터베이스 에러")
                        }
                        else -> {
                            ctx.toast("Error")
                        }
                    }
                }
            } })

    }
}