package com.rollonit.convert;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class Convert extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
        DynamicColors.applyToActivitiesIfAvailable(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
