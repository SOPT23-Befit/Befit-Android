package com.sopt.befit.fragment

import android.R
import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser

import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.adapter.CompareSizeAdapter
import com.sopt.befit.animation.ProgressAnimation
import com.sopt.befit.data.ProductData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.get.ClosetDetail
import com.sopt.befit.get.Compare
import com.sopt.befit.get.GetCompareSizeResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import kotlinx.android.synthetic.main.bottom_navigation_tab.*
import kotlinx.android.synthetic.main.dl_compare_size.*
import kotlinx.android.synthetic.main.dl_compare_size.view.*
import kotlinx.android.synthetic.main.fragment_compare_size.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompareSizeFragment1 : Fragment() {

    var token: String = ""

    lateinit var sizeList: ArrayList<String>
    lateinit var product: ProductData
    val COMPARE_DIALOG_REQUEST_CODE = 1000
    var position: Int = -1
    var closetIdx : Int = -1
    lateinit var CompareSizeAdapter: CompareSizeAdapter
    lateinit var closetList: ArrayList<ClosetDetail>
    var compareData : Compare? = null
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(com.sopt.befit.R.layout.fragment_compare_size, container, false)

        return view
    }

    private fun setSpinner(dataList: ArrayList<ClosetDetail>) {
        val closetNameList: ArrayList<String> = ArrayList()

        for (i in 0 until dataList.size) {
            closetNameList.add(dataList.get(i).name)
        }

        Log.d("dddddd", position.toString())
        if (sp_compare_size != null) {
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
        }else{}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        position = arguments!!.getInt("position")
        closetIdx = arguments!!.getInt("closetIdx")

        Log.d("fragment position", position.toString())
//
        product = ProductContentViewActivity.instance.getCurrentProductData()


        var jsonString = product.measure.toString()
        jsonString = jsonString.replace(" ","")
        var parser = JsonParser()
        var json = parser.parse(jsonString).asJsonObject
        sizeList = ArrayList<String>()

        for ((index, measure) in json.entrySet().withIndex()) {
            sizeList.add(measure.key)
        }

        getCompareSizeResponse(closetIdx)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setSpinner(closetList)
    }

    private fun getCompareSizeResponse(closetIdx: Int) {
        Log.d("zzzzzzz",position.toString())
        token = SharedPreferenceController.getAuthorization(activity!!)
        val getCompareSizeResponse = networkService.getCompareSizeResponse(token, closetIdx, product.idx, product_size = sizeList.get(position))
        Log.d("aaaaaaa", "aaaaaa")
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        getCompareSizeResponse.enqueue(object : Callback<GetCompareSizeResponse> {
            override fun onFailure(call: Call<GetCompareSizeResponse>, t: Throwable) {
                Log.e("compare size fail", t.toString())
            }

            override fun onResponse(call: Call<GetCompareSizeResponse>, response: Response<GetCompareSizeResponse>) {
                    if(response.isSuccessful){
                        when (response.body()!!.status) {
                            200 -> {
                                Log.v("success", response.body()!!.toString())

                                if(response.body()!!.data != null){
                                    compareData = response.body()!!.data

                                    Glide.with(context!!)
                                            .load(compareData!!.compare_url)
                                            .thumbnail(0.5f)
                                            .into(iv_fragment_compare_size_goods_size)
                                    if(position == 0){
                                        var dialog = parentFragment as CompareSizeDialog
                                        Glide.with(activity!!)
                                                .load(compareData!!.my_url)
                                                .into(dialog.iv_fragment_compare_size_my_size)

                                        dialog.setClosetComapreData(compareData,0)

                                    }

                                    if(lifecycle.currentState == Lifecycle.State.RESUMED){
                                        Log.d("Spinner Life",""+position+"first")
                                    } else {
                                        Log.d("Spinner Life",""+position+"other")
                                    }

                                } else {
                                    toast("옷 사이즈 비교 불가능.")
                                    var dialog = parentFragment as CompareSizeDialog
                                    dialog.dismiss()
                                }

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
                    } else {
                        Log.d("ooooooooooo",response.code().toString())
                        Toast.makeText(activity,"비교할 수 없는 상품입니다.",Toast.LENGTH_LONG).show()
                        var dialog = parentFragment as CompareSizeDialog
                        dialog.dismiss()
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

    fun getCompareSizeData() : Compare?{
        return this.compareData
    }
}