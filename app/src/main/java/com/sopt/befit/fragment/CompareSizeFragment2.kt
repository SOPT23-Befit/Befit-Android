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
import com.sopt.befit.adapter.CompareSizeAdapter
import com.sopt.befit.get.GetCompareSizeResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import kotlinx.android.synthetic.main.dl_compare_size.*
import kotlinx.android.synthetic.main.fragment_compare_size.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompareSizeFragment2 : Fragment(){

    val COMPARE_DIALOG_REQUEST_CODE = 1000
    lateinit var CompareSizeAdapter: CompareSizeAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(com.sopt.befit.R.layout.fragment_compare_size_m,container,false)
        return view
    }

    private fun setSpinner() {
        val dataList: ArrayList<String> = ArrayList()

        sp_compare_size.adapter = ArrayAdapter<String>(activity!!, R.layout.simple_list_item_single_choice, dataList)
        //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
        sp_compare_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //toast("선택된 아이템 : " + sp_compare_size.getItemAtPosition((position)))
                //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌
                getCompareSizeResponse()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinner()
    }
    private fun getCompareSizeResponse() {
        val getCompareSizeResponse = networkService.getCompareSizeResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", 1, 1, product_size = "s")
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
                            Log.v("success", response.message().toString())

                        }

                        400 -> {
                            Log.v("fail", response.message())
                            Log.v("fail", response.errorBody().toString())
                            toast("로그인 실패")
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
