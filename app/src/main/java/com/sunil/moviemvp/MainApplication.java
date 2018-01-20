package com.sunil.moviemvp;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sunil.moviemvp.di.AppComponent;
import com.sunil.moviemvp.di.DaggerAppComponent;

/**
 * Created by sunil on 20-01-2018.
 */

public class MainApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        component = DaggerAppComponent.builder().build();
    }

    public static AppComponent getComponent(Context context) {
        return ((MainApplication)context.getApplicationContext()).component;
    }
}
