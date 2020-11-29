package com.reactnativetwa;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.content.Intent;
import android.net.Uri;
import com.google.androidbrowserhelper.trusted.LauncherActivity;

public class TrustedWebActivityModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public TrustedWebActivityModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "TrustedWebActivity";
    }

    @ReactMethod
    public void goToPWA(String url) {
        Intent intent = new Intent(getReactApplicationContext(), LauncherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(url));
        getReactApplicationContext().startActivity(intent);
    }
}
