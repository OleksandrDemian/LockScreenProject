package com.example.oleksandr.lockscreenproject;

import android.util.Log;

import org.opencv.android.OpenCVLoader;

/**
 * Created by User on 24/10/2016.
 */

public class OpenCVProva {

    public static final String TAG = "OpenCVProva";

    static{
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV Not started");
        }else{
            Log.d(TAG,"OpenCV Started");
        }
    }

}
