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

class KakaoAddressActivity : AppCompatActivity() {


    private var webView: WebView? = null
    private var postnum: String? = null
    private var homeaddress : String? = null
    private var handler: Handler? = null

    internal var url = "file:///android_asset/daum.js"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_address)

        init_webView()
        handler = Handler()
    }


    fun init_webView(){
        //web view 설정
        webView = findViewById(R.id.wv_activity_kakao_address)

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

    internal inner class AndroidBridge{
        @JavascriptInterface
        // arg1 : postnum 09952 arg2 : homeaddress : 서울시 서대문구 대현동
        // arg3 : Building Name : 쌍용아파트 1지구
        public fun setAddress( arg1 : String, arg2 : String ,arg3 : String){
            handler!!.post{
                val intent : Intent = Intent()
                postnum = arg1!!.toString()
                homeaddress=arg2!!.toString() + " ("+arg3!!.toString() + ")"
                intent.putExtra("postnum",postnum)
                intent.putExtra("home_address",homeaddress)
                setResult(Activity.RESULT_OK,intent)
                init_webView()
                finish()
            }
        }

    }


}