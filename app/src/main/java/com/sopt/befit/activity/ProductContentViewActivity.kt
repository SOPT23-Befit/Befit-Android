package com.sopt.befit.activity


import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.*
import com.airbnb.lottie.LottieAnimationView
import com.sopt.befit.R
import com.sopt.befit.data.ClosetData
import com.sopt.befit.data.ProductData
import com.sopt.befit.data.UserTotalData
import com.sopt.befit.db.SharedPreferenceController
import com.sopt.befit.fragment.CompareSizeDialog
import com.sopt.befit.fragment.SizeCheckAddClothDialog
import com.sopt.befit.get.*
import com.sopt.befit.network.ApplicationController
import com.sopt.befit.network.NetworkService
import kotlinx.android.synthetic.main.activity_product_content_view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response






class ProductContentViewActivity : BaseActivity() {

    val MY_CLOSET_LIST_REQUEST_CODE = 1000

    companion object {
        lateinit var instance : ProductContentViewActivity
    }


    //웹뷰에서 회원가입 누를 때 넘겨주려면 가지고 있어야 한다.

    lateinit var closetlist:ArrayList<ClosetDetail>
//    val closetlist: ArrayList<Data> by lazy {
//        ArrayList<Data>()
//    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    //연결할 쇼핑몰 웹 url

    //intent로 바꿔주고 http 붙여주기

    private var webView: WebView? = null
    private var webSetting: WebSettings? = null
    private var webChromeClient: WebChromeClient? = null
    lateinit var usertotaldata : UserTotalData

    //상품 정보
    lateinit var productData : ProductData

    //회원 가입시 전달할 데이터를 위한 핸들러
    private var handler : Handler? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_content_view)
        handler = Handler()
        instance = this
       var intent_url = intent!!.getStringExtra("url")
        var url = "http://"+intent_url.toString()

        usertotaldata = intent.getSerializableExtra("UserTotalData") as UserTotalData


        webView = findViewById(R.id.wv_activity_product_content_view)
        val animation : LottieAnimationView = findViewById(R.id.lottie_loading_web_view)
       webView!!.webViewClient = WebViewClient()
        webSetting = webView!!.settings
        webSetting!!.javaScriptEnabled = true
        Log.v("onCreate","aaaaa")
        var token = intent!!.getStringExtra("token")
        var brand_name = intent!!.getStringExtra("name_english")

        webView!!.addJavascriptInterface(AndroidBridge(),"")
        tv_activity_product_contentview_brandname.text = brand_name.toString()
        webView!!.webViewClient = object : WebViewClient(){


            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                Log.v("webViewClient,shouldOverride","bbbbbb")
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.v("webViewonPagestarted","bbbbbb")
                animation.visibility = View.VISIBLE
                tv_anouncement.visibility = View.VISIBLE
                tv_anouncement2.visibility = View.VISIBLE
                tv_anouncement3.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                animation.visibility =View.GONE
                tv_anouncement.visibility = View.GONE
                tv_anouncement2.visibility = View.GONE
                tv_anouncement3.visibility = View.GONE
                if(url!!.contains("/member/join.html")){
                    webView!!.loadUrl("file:///android_asset/signup.js:test()")
                }
            }

        }
        webView!!.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }
       webView!!.settings.builtInZoomControls = true
        webView!!.settings.setSupportZoom(true)
        webView!!.loadUrl(url)

        btn_activity_product_contentview_cancel.setOnClickListener {
            finish()
        }
        val fm: FragmentManager = supportFragmentManager

        getProductResponse()

        btn_activity_product_contentview_size_check.setOnClickListener {
            //sizecheckDialog.show(fm, "Can't compare with anything")
            getMyClosetListResponse()
        }



    }

        //쇼핑몰에서 회원가입시 유저 data 전달
    private inner class AndroidBridge {
            @JavascriptInterface

            public fun PostUserNameData() {
                var name = usertotaldata.name
            }
                fun postUserEmailData() {
                    var email = usertotaldata.email
                }
                fun PostUserPhone(){
                    var phone = usertotaldata.phone
                }
                fun PostUserBirth(){
                    var birth = usertotaldata.birthday
                }
                fun PostUserHomeAddress(){
                    var home_address = usertotaldata.home_address
                }
                fun PostUserPostNum(){
                    var postnum = usertotaldata.post_number
                }
                fun PostUserDetailAddress(){
                    var detail_address = usertotaldata.detail_address
                }

        }

    fun getCurrentProductData() : ProductData{
        return productData
    }


    private fun getProductResponse(){
        val getMyClosetListResponse = networkService.getEachProductListResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6NSwiZXhwIjoxNTQ4OTg0MjMyfQ._IqFlm-FClS2Ur5MH9xeyt-SpURmqlbj47-vyUHrClI",10)
        getMyClosetListResponse.enqueue(object : Callback<GetEachProductResponse>{
            override fun onFailure(call: Call<GetEachProductResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<GetEachProductResponse>, response: Response<GetEachProductResponse>) {
                if(response.isSuccessful){
                    when(response.body()!!.status){
                        200->{
                            productData = response.body()!!.data
                        }
                    }
                } else {

                }
            }

        })
    }


    private fun getMyClosetListResponse() {
        val token = SharedPreferenceController.getAuthorization(this)

        val getMyClosetListResponse = networkService
                .getClosetListResponse(token, 0)
        Log.d("aaaaaaa", "aaaaaa")
        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKWUFNSSIsImlkeCI6MywiZXhwIjoxNTQ5MzcwMjAxfQ.10iSxgCGRU-d-DS9Tl_6-0DpKlf8SqKJZayLqNPYe80"
        getMyClosetListResponse.enqueue(object : Callback<GetClosetListResponse> {
            override fun onFailure(call: Call<GetClosetListResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GetClosetListResponse>, response: Response<GetClosetListResponse>) {
                response?.let {
                    if(it.isSuccessful){
                        Log.d("ABAB",it.body()!!.toString())
                        when (it.body()!!.status) {
                            200 -> {
                                Log.v("success", response.message().toString())

                                if (it.body()?.data != null){
                                    closetlist = it.body()!!.data

                                // 옷정보가 없다면

                                if (closetlist.isEmpty()) {
                                    val sizecheckDialog: DialogFragment = SizeCheckAddClothDialog()


                                        sizecheckDialog.show(supportFragmentManager, "closet list")
                                    } else {
                                        val compareSizeDialog: DialogFragment = CompareSizeDialog()
                                        var bundle = Bundle()
                                        bundle.putSerializable("ClosetList",closetlist)

                                        //상품 정보 넘기기
//                                    bundle.putInt("product_idx",productData.idx)
//                                    bundle.putString("measure",productData.mesure.toString())


                                        compareSizeDialog.arguments = bundle
                                        //옷정보가 있을 때 사이즈비교 다이얼로그 띄우기
                                        compareSizeDialog.show(supportFragmentManager, "compare size")
                                    }
                                } else{

                                }

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


                    } else {
                        Log.d("zzzzz",it.code().toString())
                    }
                }
            }
        })
    }
}





