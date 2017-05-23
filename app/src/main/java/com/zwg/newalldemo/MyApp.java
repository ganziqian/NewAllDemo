package com.zwg.newalldemo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("=======ss===","aa");
        Toast.makeText(getApplicationContext(),"777777",Toast.LENGTH_SHORT).show();

    }
}
