package com.sopt.befit.activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


open class BaseActivity : AppCompatActivity() {
    private var mTypeface: Typeface? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(this.assets, "fonts/kopubworlddotummedium.ttf")
        }
        setGlobalFont(window.decorView)
    }

    private fun setGlobalFont(view: View) {
        if (view != null) {
            if (view is ViewGroup) {
                val vg = view
                val vgCnt = vg.childCount
                for (i in 0 until vgCnt) {
                    val v = vg.getChildAt(i)
                    if (v is TextView) {
                        v.typeface = mTypeface
                    }
                    setGlobalFont(v)
                }
            }
        }
    }
}