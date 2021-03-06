package com.lechneralexander.privatebrowser.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;

import com.lechneralexander.privatebrowser.activity.BrowserActivity;
import com.lechneralexander.privatebrowser.app.BrowserApp;

/**
 * Copyright 8/4/2015 Anthony Restaino
 */
public class WebUtils {

    public static void clearCookies(@NonNull Context context) {
        CookieManager c = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            c.removeAllCookies(null);
        } else {
            //noinspection deprecation
            CookieSyncManager.createInstance(context);
            //noinspection deprecation
            c.removeAllCookie();
        }
    }

    public static void clearWebStorage() {
        WebStorage.getInstance().deleteAllData();
    }

    public static void clearHistory(@NonNull Context context) {
        WebViewDatabase m = WebViewDatabase.getInstance(context);
        m.clearFormData();
        m.clearHttpAuthUsernamePassword();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //noinspection deprecation
            m.clearUsernamePassword();
            //noinspection deprecation
            WebIconDatabase.getInstance().removeAllIcons();
        }
        Utils.trimCache(context);
    }

    public static void clearCache(@Nullable Activity mActivity) {
        WebView webView = new WebView(mActivity);
        webView.clearCache(true);
        webView.destroy();
    }

    public static void clearFormData(@Nullable Activity mActivity) {
        WebView webView = new WebView(mActivity);
        webView.clearFormData();
        webView.destroy();
    }
}
