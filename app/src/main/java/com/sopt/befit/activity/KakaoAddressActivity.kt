package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import com.sopt.befit.R

class KakaoAddressActivity : AppCompatActivity() {


    lateinit var webView : WebView
    lateinit var result : TextView
    lateinit var  handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_address)
        result = findViewById(R.id.tv_activity_kakao_address)

        init_webView()
        handler = Handler()
    }


    public fun init_webView(){
        //web view 설정
        webView = findViewById(R.id.wv_activity_kakao_address)

        //javascript 허용
        webView.settings.javaScriptEnabled = true
        //javascript window open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        //javascript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        //두번째 param은 사용될 php에도 동일하게 사용해줘야한다.
        webView.addJavascriptInterface(AndroidBridge(),"TestApp")
        webView.webChromeClient = WebChromeClient()
        // webview url load
        webView.loadUrl("http://codeman77.ivyro.net/getAddress.php")




    }

    inner class AndroidBridge{
        @JavascriptInterface
        public fun setAddress(arg1 : String,arg2 : String, arg3 : String ){
            handler.post {
                result.text = String.format("(%s) %s %s", arg1, arg2, arg3)
                // WebView를 초기화 하지않으면 재사용할 수 없음
                init_webView()
            }
        }

    }
}
