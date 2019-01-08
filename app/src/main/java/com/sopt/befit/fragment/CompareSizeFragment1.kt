package com.sopt.befit.fragment

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser
import com.sopt.befit.Adapter.CompareSizeAdapter

import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.animation.ProgressAnimation
import com.sopt.befit.data.ProductData
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.get.GetCompareSizeResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import kotlinx.android.synthetic.main.dl_compare_size.*
import kotlinx.android.synthetic.main.fragment_compare_size.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompareSizeFragment1 : Fragment() {

    lateinit var sizeList : ArrayList<String>
    lateinit var product : ProductData
    val COMPARE_DIALOG_REQUEST_CODE = 1000
    var position : Int = -1
    lateinit var CompareSizeAdapter: CompareSizeAdapter
    lateinit var closetList : ArrayList<ClosetDetail>
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(com.sopt.befit.R.layout.fragment_compare_size, container, false)
        return view
    }

    private fun setSpinner(dataList : ArrayList<ClosetDetail>) {
        val closetNameList: ArrayList<String> = ArrayList()

        for(i in 0 until dataList.size){
            closetNameList.add(dataList.get(i).name)
        }

        Log.d("dddddd",position.toString())
        sp_compare_size.adapter = ArrayAdapter<String>(activity!!, R.layout.simple_list_item_single_choice, closetNameList)
        //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
        sp_compare_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //toast("선택된 아이템 : " + sp_compare_size.getItemAtPosition((position)))
                //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌
                var closetIdx = dataList.get(position).closet_idx
                closetIdx = 8
                getCompareSizeResponse(closetIdx)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val product_idx = 1
        val closet_idx = 1


        position = arguments!!.getInt("position")
        closetList = arguments!!.getSerializable("ClosetList") as ArrayList<ClosetDetail>
        Log.d("fragment position",position.toString())
//
        product = ProductContentViewActivity.instance.getCurrentProductData()

        var jsonString = product.measure.toString()
        var parser = JsonParser()
        var json = parser.parse(jsonString).asJsonObject
        sizeList = ArrayList<String>()

        for((index,measure) in json.entrySet().withIndex()){
            sizeList.add(measure.key)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinner(closetList)
    }

    private fun getCompareSizeResponse(closetIdx : Int) {
        val getCompareSizeResponse = networkService.getCompareSizeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", closetIdx, product.idx, product_size = sizeList.get(position))
        Log.d("aaaaaaa", "aaaaaa")
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        getCompareSizeResponse.enqueue(object : Callback<GetCompareSizeResponse> {
            override fun onFailure(call: Call<GetCompareSizeResponse>, t: Throwable) {
                Log.e("compare size fail", t.toString())
            }

            override fun onResponse(call: Call<GetCompareSizeResponse>, response: Response<GetCompareSizeResponse>) {
                response?.let {
                    when (it.body()!!.status) {
                        200 -> {
                            Log.v("success", response.body()!!.toString())
                            var animation = ProgressAnimation(progress,2000)//2000은 2초
                            animation.setProgress(response.body()!!.data.percent.toInt())

                            tv_fragment_compare_size_percent.text = response.body()!!.data.percent+"%"


                            val requestOptions = RequestOptions()
//            //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
//            //        requestOptions.error(R.drawable.에러시 띄울 이미지)
                            requestOptions.override(150)
                            Glide.with(context!!)
                                    .setDefaultRequestOptions(requestOptions)
                                    .load(response.body()!!.data.compare_url)
                                    .thumbnail(0.5f)
                                    .into(iv_fragment_compare_size_goods_size)

                            requestOptions.override(150)
                            Glide.with(context!!)
                                    .setDefaultRequestOptions(requestOptions)
                                    .load(response.body()!!.data.my_url)
                                    .thumbnail(0.5f)
                                    .into(iv_fragment_compare_size_my_size)

                            var key = ArrayList<String>()
                            var data = ArrayList<Double>()

                            var jsonString = response.body()!!.data.measure.toString()
                            var parser = JsonParser()
                            var json = parser.parse(jsonString).asJsonObject

                            for((index,closet) in json.entrySet().withIndex()){
                                key.add(closet.key)
                                if(closet.value == null){
                                    data.add(100.0)
                                } else {
                                    data.add(closet.value.asDouble)
                                }
                            }

                            tv_fragment_compare_size_goods_name.text = product.name
                            tv_fragment_compare_size_Size.text = sizeList.get(position)

                            Log.v("===============tag===============", data.toString())

                        }

                        404 -> {
                            Log.v("fail", response.message())
                            Log.v("fail", response.errorBody().toString())
                            toast("해당 정보의 상품은 존재하지 않습니다.")
                        }

                        500 -> {

                            Log.v("409 error", response.message())
                            Log.v("server error", response.errorBody().toString())
                            toast("서버 내부 에러")
                        }
                        600 -> {
                            Log.v("600 error", response.message())
                            Log.v("database error", response.errorBody().toString())
                            toast("데이터베이스 에러")
                        }
                        else -> {
                            toast("Error")
                        }
                    }
                }
            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COMPARE_DIALOG_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
            }
        }
    }
}
