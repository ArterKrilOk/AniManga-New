package com.pixelsg.animanga;

import android.app.Application;

import com.google.android.material.color.DynamicColors;
import com.pixelsg.animanga.data.models.Config;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);
    }

    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
