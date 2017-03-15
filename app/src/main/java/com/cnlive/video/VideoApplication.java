package com.cnlive.video;

import android.app.Application;

import com.cnlive.libs.util.Config;

/**
 * Created by Mr.hou on 12/15/2016.
 */

public class VideoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Config.init(this, "60_irn9edco29", "69fe70bda67e059286ad509d9f5377f815955645b52f94");
    }
}
