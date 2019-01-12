package com.sopt.befit.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sopt.befit.R
import com.sopt.befit.activity.BrandMainActivity
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.GetEachProductResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_brand_main.*
import kotlinx.android.synthetic.main.fragment_main_banner1.*
import kotlinx.android.synthetic.main.fragment_main_banner2.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainBannerFragment2: Fragment(){

    lateinit var token : String

    val dataList: ArrayList<ProductData> by lazy {
        ArrayList<ProductData>()
    }

    lateinit var productData: ProductData



    //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
    lateinit var networkService: NetworkService
    lateinit var temp : UserTotalData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val brandFragmentView: View = inflater!!.inflate(R.layout.fragment_main_banner2, container, false)
        return brandFragmentView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(activity!!).load(resources.getDrawable(R.drawable.banner2)).into(iv_home_fragment_banner_2)

        getUserDataResponse()
        getOneProductResponse()
    }

    private fun getOneProductResponse() {
        val getOneProductResponse = networkService.getEachProductListResponse(token, 427)
        getOneProductResponse.enqueue(object : Callback<GetEachProductResponse> {
            override fun onFailure(call: Call<GetEachProductResponse>, t: Throwable) {
                Log.e("brand fail", t.toString())
            }

            override fun onResponse(call: Call<GetEachProductResponse>, response: Response<GetEachProductResponse>) {
                if (response.isSuccessful) {

                    iv_home_fragment_banner_2.setOnClickListener(){
                        productData = response.body()!!.data

//                        startActivity<BrandMainActivity>(
//                                "flag" to 1, "idx" to 24)

                        val intent: Intent = Intent(activity!!, ProductContentViewActivity::class.java)
                        intent.putExtra("idx", productData.idx)
                        intent.putExtra("url", productData.link)
                        intent.putExtra("name_english", productData.name_english)
                        intent.putExtra("UserTotalData", temp)
                        intent.putExtra("product", productData)

                        startActivity(intent)

                        //두번째 배너를 누르면 웹뷰로 가는거 원하는거지? 그럼 이렇게 하면대!
                    }

                }
            }
        })
    }

    private fun getUserDataResponse(){
        Log.d("aaaaaaa","aaaaaa")

        token =SharedPreferenceController.getAuthorization(activity!!)
        networkService = ApplicationController.instance!!.networkService
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





                        }

                        400 -> {
                            Log.v("fail",response.message())
                            Log.v("fail",response.errorBody().toString())
                            toast("로그인 실패")
                        }

                        500 -> {

                            Log.v("409 error",response.message())
                            Log.v("server error",response.errorBody().toString())
                            toast("서버 내부 에러")
                        }
                        600->{
                            Log.v("600 error",response.message())
                            Log.v("database error",response.errorBody().toString())
                            toast("데이터베이스 에러")
                        }
                        else -> {
                            toast("Error")
                        }
                    }
                }
            } })
    }

}