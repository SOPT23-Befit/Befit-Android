package com.sopt.befit.activity


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import com.sopt.befit.R
import com.sopt.befit.data.ClosetData
import com.sopt.befit.fragment.CompareSizeDialog
import com.sopt.befit.fragment.SizeCheckAddClothDialog
import com.sopt.befit.get.GetClosetListResponse
import com.sopt.befit.get.GetMyClosetListResponse
import com.sopt.befit.get.GetUserDataResponse
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_product_content_view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductContentViewActivity : AppCompatActivity() {

    val MY_CLOSET_LIST_REQUEST_CODE = 1000

    val closetlist: ArrayList<ClosetData> by lazy {
        ArrayList<ClosetData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var mywebview: WebView? = null

    lateinit var url: String
    private var webView: WebView? = null
    private var webSetting: WebSettings? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_content_view)
//        mywebview = findViewById(R.id.wv_activity_product_content_view)
//
//
//        mywebview!!.webViewClient = object : WebViewClient(){
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//        }
//        mywebview!!.loadUrl("")
        btn_activity_product_contentview_cancel.setOnClickListener {
            finish()
        }
        val fm: FragmentManager = supportFragmentManager




        btn_activity_product_contentview_size_check.setOnClickListener {
            //sizecheckDialog.show(fm, "Can't compare with anything")
            getMyClosetListResponse()
        }


//
//        btn_dl_size_check_login.setOnClickListener {
    }

    //    fun init_webView(){
//        webView =  findViewById(R.id.wv_activity_product_content_view)
//        webView!!.settings.javaScriptEnabled = true
//
//        webView!!.loadUrl(url)
//        webView!!.webChromeClient =WebChromeClient()
//
//
//    }
//    val requestOptions = RequestOptions()
////             requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
////             requestOptions.error(R.drawable.에러시 띄울 이미지)
////             requestOptions.override(150) Glide.with(ctx)
//            .setDefaultRequestOptions(requestOptions)
//            .load(dataList[position].image_url)
//            .thumbnail(0.5f)

    private fun getMyClosetListResponse() {
        val getMyClosetListResponse = networkService.getClosetListResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80", 1)
        Log.d("aaaaaaa", "aaaaaa")
        //val token = SharedPreferenceController.getAuthorization(activity!!)
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        getMyClosetListResponse.enqueue(object : Callback<GetClosetListResponse> {
            override fun onFailure(call: Call<GetClosetListResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GetClosetListResponse>, response: Response<GetClosetListResponse>) {
                response?.let {
                    if(it.isSuccessful){
                        Log.d("zzzzz",it.body()!!.toString())
                        when (it.body()!!.status) {
                            200 -> {
                                Log.v("success", response.message().toString())
                            }

                            400 -> {
                                Log.v("400 error", response.message())
                                Log.v("fail", response.errorBody().toString())
                                toast("옷 조회 실패")
                            }

                            401 -> {

                                Log.v("401 error", response.message())
                                Log.v("server error", response.errorBody().toString())
                                toast("인증 실패")
                            }
                            500 -> {
                                Log.v("500 error", response.message())
                                Log.v("database error", response.errorBody().toString())
                                toast("서버내부 에러")
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

                        // 옷정보가 없다면
                        val sizecheckDialog: DialogFragment = SizeCheckAddClothDialog()
                        //옷정보가 있다면
                        val compareSizeDialog: DialogFragment = CompareSizeDialog()

                        if (closetlist.isEmpty()) {
                            compareSizeDialog.show(supportFragmentManager, "compare size")
                        } else {
                            //옷정보가 있을 때 사이즈비교 다이얼로그 띄우기
                            compareSizeDialog.show(supportFragmentManager, "compare size")
                        }
                    } else {
                        Log.d("zzzzz",it.code().toString())
                    }
                }
            }
        })
    }
}





