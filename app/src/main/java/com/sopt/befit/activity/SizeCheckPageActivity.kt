package com.sopt.befit.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.DetailSize
import com.sopt.befit.get.GetCheckMySizeResponse
import com.sopt.befit.get.GetEachProductResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_size_check_page_activiy.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SizeCheckPageActivity : AppCompatActivity() {

    lateinit var size : ArrayList<String>


    var closet_idx : Int = 0

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size_check_page_activiy)
        btnBackOnClick()
      
        closet_idx = intent.getIntExtra("closet_idx",0)
        getCheckMySizeResponse()
    }

    fun btnBackOnClick() {
        btn_activity_size_check_page_back.setOnClickListener {
            finish()
        }
    }

    private fun getCheckMySizeResponse() {
        val getCheckMySizeResponse = networkService.getCheckMySizeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6NSwiZXhwIjoxNTQ4OTg0MjMyfQ._IqFlm-FClS2Ur5MH9xeyt-SpURmqlbj47-vyUHrClI", closet_idx)
        getCheckMySizeResponse.enqueue(object : Callback<GetCheckMySizeResponse> {
            override fun onFailure(call: Call<GetCheckMySizeResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<GetCheckMySizeResponse>, response: Response<GetCheckMySizeResponse>) {

                val requestOptions = RequestOptions()
                //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
                //        requestOptions.error(R.drawable.에러시 띄울 이미지)
                //        requestOptions.override(150)
                Glide.with(this@SizeCheckPageActivity)
                        .setDefaultRequestOptions(requestOptions)
                        .load(response.body()!!.data.image_url)
                        .thumbnail(0.5f)
                        .into(iv_activity_size_check_page_image)

                response?.let {
                    when (response.body()!!.status) {
                        200 -> {
                            Log.v("success", response.message().toString())

                            text_brand.setText(response.body()!!.data.name_korean)
                            text_goods_name.setText(response.body()!!.data.name)
                            btn_activity_size_check_page_size.setText(response.body()!!.data.product_size)


                        }
                        400 -> {
                            Log.v("400 error", response.message())
                            toast("나의 옷장 특정 아이템 조회 실패")
                        }
                        401 -> {
                            Log.v("409 error", response.message())
                            toast("인증 실패")
                        }
                        500 -> {
                            Log.v("500 error", response.message())
                            toast("서버 내부 에러")
                        }
                        600 -> {
                            Log.v("600 error", response.message())
                            toast("데이터 베이스 에러")
                        }
                        else -> {
                            toast("error")
                        }
                    }
                }
            }
        })
    }


}
