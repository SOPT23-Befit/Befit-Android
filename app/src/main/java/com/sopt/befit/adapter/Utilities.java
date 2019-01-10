package com.sopt.befit.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Utilities {

    static Typeface mediumTypeface;
    static Typeface lightTypeface;
    static Typeface boldTypeface;

    public static void setGlobalFont(View view, Context ctx) {
        AssetManager assetManager = ctx.getAssets();
        setMyTypeface(assetManager);
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView && v.getTag() != null) {
                        if (v.getTag().equals("bold")) {
                            ((TextView) v).setTypeface(boldTypeface);
                        } else if (v.getTag().equals("light")) {
                            ((TextView) v).setTypeface(lightTypeface);
                        }
                    } else if (v instanceof TextView) {
                        ((TextView) v).setTypeface(mediumTypeface);
                    }
                    setGlobalFont(v, ctx);
                }
            }
        } else {
            Log.e("setGlobalFont", "This is null ");
        }
    }

    private static void setMyTypeface(AssetManager assetManager) {
        if (mediumTypeface == null) {
            mediumTypeface = Typeface.createFromAsset(assetManager, "fonts/kopubdotummedium.ttf");
        }
        if (lightTypeface == null) {
            lightTypeface = Typeface.createFromAsset(assetManager, "fonts/kopubdotumlight.ttf");
        }
        if (boldTypeface == null) {
            boldTypeface = Typeface.createFromAsset(assetManager, "fonts/kopubdotumbold.ttf");
        }
    }
}
