package com.sopt.befit.activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


open class BaseActivity : AppCompatActivity() {
    private var mediumTypeface: Typeface? = null
    private var lightTypeface: Typeface? = null
    private var boldTypeface: Typeface? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (mediumTypeface == null) {
            mediumTypeface = Typeface.createFromAsset(this.assets, "fonts/kopubdotummedium.ttf")
        }
        if (lightTypeface == null) {
            lightTypeface = Typeface.createFromAsset(this.assets, "fonts/kopubdotumlight.ttf")
        }
        if (boldTypeface == null) {
            boldTypeface = Typeface.createFromAsset(this.assets, "fonts/kopubdotumbold.ttf")
        }
        setGlobalFont(window.decorView)
    }

    private fun setGlobalFont(view: View) {
        if (view is ViewGroup) {
            val vg = view
            val vgCnt = vg.childCount
            for (i in 0 until vgCnt) {
                val v = vg.getChildAt(i)
                if (v is TextView && v.getTag() != null) {
                    if (v.getTag().equals("bold")) {
                        v.typeface = boldTypeface
                    } else if (v.getTag().equals("light")) {
                        v.typeface = lightTypeface
                    }
                } else if (v is TextView) {
                    v.typeface = mediumTypeface
                }
                setGlobalFont(v)
            }
        }
    }
}