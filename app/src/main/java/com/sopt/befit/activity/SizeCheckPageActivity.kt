package com.sopt.befit.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser
import com.sopt.befit.R
import com.sopt.befit.R.id.layout_my_size_add_display_tvs
import com.sopt.befit.data.ProductData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.*
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_size_check_page_activiy.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SizeCheckPageActivity : AppCompatActivity() {

    var token: String = ""

    lateinit var detailSize: DetailSize

    lateinit var size: ArrayList<String>

    var closet_idx: Int = 0


    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size_check_page_activiy)
        btnBackOnClick()
        closet_idx = intent.getIntExtra("closet_idx", 0)
        getCheckMySizeResponse()



    }

    fun btnBackOnClick() {
        btn_activity_size_check_page_back.setOnClickListener {
            finish()
        }
    }

    private fun getCheckMySizeResponse() {

        var token = SharedPreferenceController.getAuthorization(this)
        val getCheckMySizeResponse = networkService.getCheckMySizeResponse(token, closet_idx)
        getCheckMySizeResponse.enqueue(object : Callback<GetCheckMySizeResponse> {
            override fun onFailure(call: Call<GetCheckMySizeResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<GetCheckMySizeResponse>, response: Response<GetCheckMySizeResponse>) {

                if(response.isSuccessful){
                    when (response.body()!!.status) {
                        200 -> {

                            if(response.body()!!.data != null){
                                val requestOptions = RequestOptions()
                                //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
                                //        requestOptions.error(R.drawable.에러시 띄울 이미지)
                                //        requestOptions.override(150)
                                Glide.with(this@SizeCheckPageActivity)
                                        .setDefaultRequestOptions(requestOptions)
                                        .load(response.body()!!.data.image_url)
                                        .thumbnail(0.5f)
                                        .into(iv_activity_size_check_page_image)

                                Log.v("success", response.body().toString())
                                detailSize = response.body()!!.data
                                setCompareTable(detailSize!!)
                                text_brand.setText(response.body()!!.data.name_korean)
                                text_goods_name.setText(response.body()!!.data.name)
                                btn_activity_size_check_page_size.setText(response.body()!!.data.product_size)
                            } else {
                                toast(response.body()!!.message)
                            }

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
                } else {
                    Log.d("SizeCheckPageActivity", response.code().toString())
                }


            }
        })
    }

    fun setCompareTable(detailSize: DetailSize) {
        layout_my_size_display_detail.removeAllViews()
        Log.d("mmmmm", detailSize.toString())
        var jsonString = detailSize.measure.toString()
        Log.d("mmmmmm", jsonString)
        var parser = JsonParser()
        var measureObject = parser.parse(jsonString).asJsonObject
        var keyList = ArrayList<String>()
        var valueList = ArrayList<Double>()
        for ((index, result) in measureObject.entrySet().withIndex()) {
            keyList.add(result.key)
            if ((""+result.value).equals("null")) {
                valueList.add(-99.0)
            } else {
                valueList.add(result.value.asDouble)
            }
        }

        val row = arrayOfNulls<TableRow>(3)

        for (i in 0 until 2) {
            row.set(i, TableRow(this))
            var params = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
            row.get(i)!!.layoutParams = params
            var textList = arrayOfNulls<TextView>(keyList.size)
            for (j in 0 until keyList.size) {
                when (i) {
                    0 -> {
                        textList.set(j, TextView(this))
                        textList.get(j)!!.text = keyList.get(j)
                        textList.get(j)!!.textColor = Color.parseColor("#191919")
                        textList.get(j)!!.gravity = Gravity.CENTER
                        textList.get(j)!!.backgroundColor = Color.parseColor("#f3f3f3")
                    }
                    1 -> {
                        textList.set(j, TextView(this))
                        textList.get(j)!!.textColor = Color.parseColor("#000000")
                        textList.get(j)!!.gravity = Gravity.CENTER
                        textList.get(j)!!.text = valueList.get(j).toString()

                    }
                }
                row.get(i)!!.addView(textList.get(j))
            }
            layout_my_size_display_detail.addView(row.get(i))
            layout_my_size_display_detail.isStretchAllColumns = true
        }

    }
}