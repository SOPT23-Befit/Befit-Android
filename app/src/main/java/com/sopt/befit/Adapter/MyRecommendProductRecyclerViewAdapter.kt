package com.sopt.befit.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import com.sopt.befit.post.PostProductLikeResponse
import com.sopt.befit.post.PostProductUnlikeResponse
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRecommendProductRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ProductData>) : RecyclerView.Adapter<MyRecommendProductRecyclerViewAdapter.Holder>() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"


    lateinit var temp : UserTotalData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_home_fragment_rec_product, parent, false)
        Utilities.setGlobalFont(view, ctx);
        return Holder(view)
    }

    override fun getItemCount(): Int {
        if (dataList != null) {
            return dataList.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b_name.text = dataList[position].name_korean
        holder.p_name.text = dataList[position].name

        if (ctx is BrandMainActivity) {
            //holder.count.text = "PRODUCT (" + getItemCount() + ")"
        } else {

        }

        val requestOptions = RequestOptions()

        //var token : String = SharedPreferenceController.getAuthorization(ctx)

        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].image_url)
                .thumbnail(0.5f)
                .into(holder.main)

        holder.item_btn.setOnClickListener {
            val intent: Intent = Intent(ctx, BrandMainActivity::class.java)
            intent.putExtra("idx", dataList[position].idx)
            intent.putExtra("token", token)
            ctx.startActivity(intent)

            getUserDataResponse(position)
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main: ImageView = itemView.findViewById(R.id.img_rv_item_each_product_main) as ImageView

        val b_name: TextView = itemView.findViewById(R.id.tv_rv_item_each_product_b_name) as TextView
        val p_name: TextView = itemView.findViewById(R.id.tv_rv_item_each_product_p_name) as TextView

        val item_btn: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_each_product) as ConstraintLayout
    }

    private fun getUserDataResponse(position: Int){
        Log.d("aaaaaaa","aaaaaa")
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
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

                            val intent: Intent = Intent(ctx, ProductContentViewActivity::class.java)
                            intent.putExtra("idx", dataList[position].idx)
                            intent.putExtra("url", dataList[position].link)
                            intent.putExtra("name_english", dataList[position].name_english)
                            intent.putExtra("token", token)
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