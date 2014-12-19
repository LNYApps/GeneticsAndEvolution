package com.lnyapps.geneticsandevolution;

import android.app.Application;
import android.content.Context;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class App extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
