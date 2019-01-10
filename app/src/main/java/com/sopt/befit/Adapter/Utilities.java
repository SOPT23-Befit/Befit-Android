package com.sopt.befit.Adapter;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Utilities {


    public static void setGlobalFont(View view, Context ctx) {
        AssetManager assetManager = ctx.getAssets();
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(Typeface.createFromAsset(assetManager,"fonts/kopubdotummedium.ttf"));
                    }
                    setGlobalFont(v, ctx);
                }
            }
        } else {
            Log.e("setGlobalFont", "This is null ");
        }
    }
}
