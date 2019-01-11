package com.sopt.befit.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_kakao_address.*

class KakaoAddressActivity : BaseActivity() {


    private var webView: WebView? = null
    private var postnum: String? = null
    private var zonecode : String? = null
    private var fulladdress : String? = null
    private var handler: Handler? = null

    internal var url = "https://han51361.github.io/postcode/lsw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_address)
        btn_activity_kakao.isEnabled  = false

        init_webView()
        handler = Handler()
        btn_activity_kakao_back.setOnClickListener {
            finish()
        }
        btn_activity_kakao.setOnClickListener {

            finish()
        }
    }


    fun init_webView(){
        //web view 설정
        webView = findViewById(R.id.wv_activity_kakao_address)
        Log.v("init_webview","sasd")
        //javascript 허용
        webView!!.settings.javaScriptEnabled = true
        //javascript window open 허용
        webView!!.settings.javaScriptCanOpenWindowsAutomatically = true


        //javascript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        //두번째 param은 사용될 php에도 동일하게 사용해줘야한다.
        webView!!.addJavascriptInterface(AndroidBridge(),"TestApp")
        webView!!.settings.domStorageEnabled = true
        webView!!.webChromeClient = WebChromeClient()
        // webview url load
        webView!!.loadUrl(url)




    }

    private inner class AndroidBridge{
        @JavascriptInterface
        // arg1 : postnum 100-771 arg2 : zonecode: 03933
        // arg3 : Building Name : 쌍용아파트 1지구
         public fun setAddress( arg1 : String, arg2 : String ,arg3 : String) {
            handler!!.post(object : Runnable {
                public override fun run() {
                    val intent: Intent = Intent()
                    postnum = arg1!!.toString()
                    zonecode = arg2!!.toString()
                    fulladdress=arg3!!.toString()
                    intent.putExtra("zone_code",zonecode)
                    intent.putExtra("post_num",postnum)
                    intent.putExtra("full_address",fulladdress)

                  if(postnum!!.isNotEmpty()){
                      btn_activity_kakao.isEnabled  = true
                      btn_activity_kakao.setBackgroundResource(R.drawable.blackbox)
                      setResult(Activity.RESULT_OK,intent)
                  }

                }

            })

            }
        }
        }




