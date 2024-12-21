package br.com.edu.jet.foodfusion.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

public class ResourceUtils {

    private static Context appContext;

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    private static Resources getResources() {
        if (appContext == null) {
            throw new IllegalStateException("ResourceUtils not initialized. Call ResourceUtils.init(context) first.");
        }
        return appContext.getResources();
    }

    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    public static int getColor(@ColorRes int resId) {
        return getResources().getColor(resId, null);
    }

    public static int getColorFromHex(String hexColor) {
        return Color.parseColor(hexColor);
    }
}

