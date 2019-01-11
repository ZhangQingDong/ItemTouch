package com.example.zqd.itemtouch;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static BaseApplication getApp() {
        return app;
    }

}
