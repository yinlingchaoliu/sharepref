package com.caliburn.app;

import android.app.Application;

import com.caliburn.app.demo.SharePrefManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharePrefManager.getIns().init(this);
    }

}
